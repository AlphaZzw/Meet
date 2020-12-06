package com.alpha.meet.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alpha.framework.base.BaseUIActivity;
import com.alpha.framework.bmob.BmobManager;
import com.alpha.framework.bmob.IMUser;
import com.alpha.framework.entity.Constants;
import com.alpha.framework.manager.DialogManager;
import com.alpha.framework.utils.LogUtils;
import com.alpha.framework.utils.SpUtils;
import com.alpha.framework.view.DialogView;
import com.alpha.framework.view.LoadingView;
import com.alpha.meet.MainActivity;
import com.alpha.meet.R;
import com.luozm.captcha.Captcha;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * 1.点击发送的按钮，弹出一个提示框，图片验证码，验证通过之后
 * 2.!发送验证码，@同时按钮变成不可点击，@按钮开始倒计时，倒计时结束，@按钮可点击，@文字变成“发送”
 * 3.通过手机号码和验证码进行登录
 * 4.登录成功之后获取本地对象
 */
public class LoginActivity extends BaseUIActivity implements View.OnClickListener {

    private TextView tv_test_login;
    private EditText et_phone;
    private EditText et_code;
    private Button btn_send_code;
    private Button btn_login;
    private DialogView mCodeView;
    private Captcha mCaptcha;

    private static final int H_TIME = 1001;
    //60s倒计时
    private static int TIME = 60;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case H_TIME:
                    TIME--;
                    btn_send_code.setText(TIME + "s");
                    if (TIME > 0) {
                        mHandler.sendEmptyMessageDelayed(H_TIME, 1000);
                    } else {
                        btn_send_code.setEnabled(true);
                        btn_send_code.setText(getString(R.string.text_login_send));
                        TIME = 60;
                    }
            }
            return false;
        }
    });
    private LoadingView mLoadingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LogUtils.i("Login");

        initView();
        initDialogView();

    }

    private void initDialogView() {
        mLoadingView = new LoadingView(this);
        mCodeView = DialogManager.getInstance().initView(this, R.layout.dialog_code_view);
        mCaptcha = mCodeView.findViewById(R.id.captcha);
        mCaptcha.setCaptchaListener(new Captcha.CaptchaListener() {
            @Override
            public String onAccess(long time) {
                Toast.makeText(LoginActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                DialogManager.getInstance().hide(mCodeView);
                sendSMS();
                return "验证通过,耗时" + time + "毫秒";
            }

            @Override
            public String onFailed(int failCount) {
                Toast.makeText(LoginActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                return "验证失败,已失败" + failCount + "次";
            }

            @Override
            public String onMaxFailed() {
                Toast.makeText(LoginActivity.this, "验证超过次数，你的帐号被封锁", Toast.LENGTH_SHORT).show();
                return "验证失败,帐号已封锁";
            }
        });
    }

    private void initView() {
        tv_test_login = findViewById(R.id.tv_test_login);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_code = (EditText) findViewById(R.id.et_code);
        btn_send_code = (Button) findViewById(R.id.btn_send_code);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_send_code.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_send_code:
                DialogManager.getInstance().show(mCodeView);
                break;
        }
    }

    private void login() {
        //1.判断手机号码和验证码不为空
        final String phone = et_phone.getText().toString().trim();
        LogUtils.i("phone:" + phone);
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, getString(R.string.text_login_phone_null),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        String code = et_code.getText().toString().trim();
        LogUtils.i("code:" + code);
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, getString(R.string.text_login_code_null),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        mLoadingView.show("正在登录...");
        BmobManager.getInstance().signOrLoginByMobilePhone(phone, code, new LogInListener<IMUser>() {
            @Override
            public void done(IMUser imUser, BmobException e) {
                mLoadingView.hide();
                if (e == null) {
                    //登录成功
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    //保存手机号码
                    SpUtils.getInstance().getString(Constants.SP_PHONE, phone);
                    finish();
                } else {
                    if (e.getErrorCode() == 207) {
                        Toast.makeText(LoginActivity.this, getString(R.string.text_login_code_error), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "ERROR:" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void sendSMS() {
        //1.获取手机号码
        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, getString(R.string.text_login_phone_null), Toast.LENGTH_SHORT).show();
            return;
        }

        //2.请求验证码
        BmobManager.getInstance().requestSMS(phone, new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {
                    btn_send_code.setEnabled(false);
                    mHandler.sendEmptyMessage(H_TIME);
                    Toast.makeText(LoginActivity.this, getString(R.string.text_user_resuest_succeed),
                            Toast.LENGTH_SHORT).show();
                } else {
                    LogUtils.e("SMS:" + e.toString());
                    Toast.makeText(LoginActivity.this, getString(R.string.text_user_resuest_fail),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}