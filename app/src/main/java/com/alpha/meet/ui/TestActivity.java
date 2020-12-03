package com.alpha.meet.ui;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alpha.framework.base.BaseUIActivity;
import com.alpha.meet.R;
import com.luozm.captcha.Captcha;

public class TestActivity extends BaseUIActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_code_view);

        Captcha captcha = (Captcha) findViewById(R.id.captcha);
        captcha.setCaptchaListener(new Captcha.CaptchaListener() {
            @Override
            public String onAccess(long time) {
                Toast.makeText(TestActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                return "验证通过,耗时" + time + "毫秒";
            }

            @Override
            public String onFailed(int failCount) {
                Toast.makeText(TestActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                return "验证失败,已失败" + failCount + "次";
            }

            @Override
            public String onMaxFailed() {
                Toast.makeText(TestActivity.this, "验证超过次数，你的帐号被封锁", Toast.LENGTH_SHORT).show();
                return "验证失败,帐号已封锁";
            }
        });
    }


}
