package com.alpha.meet.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alpha.framework.base.BaseUIActivity;
import com.alpha.framework.utils.LogUtils;
import com.alpha.meet.R;

public class LoginActivity extends BaseUIActivity implements View.OnClickListener {

    private Button mBtn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LogUtils.i("Login");

        initView();

    }

    private void initView() {
        mBtn_login = (Button) findViewById(R.id.btn_login);
        mBtn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(this, TestActivity.class));
        }
    }
}