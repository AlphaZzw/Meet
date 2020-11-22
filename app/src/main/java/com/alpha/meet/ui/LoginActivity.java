package com.alpha.meet.ui;

import android.os.Bundle;

import com.alpha.framework.base.BaseUIActivity;
import com.alpha.framework.utils.LogUtils;
import com.alpha.meet.R;

public class LoginActivity extends BaseUIActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LogUtils.i("Login");
    }
}