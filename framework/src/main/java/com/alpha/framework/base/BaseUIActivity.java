package com.alpha.framework.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alpha.framework.utils.LogUtils;
import com.alpha.framework.utils.SystemUI;

/**
 *
 */

public class BaseUIActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemUI.fixSystemUI(this);
        LogUtils.i("baseUIActivity");
    }
}
