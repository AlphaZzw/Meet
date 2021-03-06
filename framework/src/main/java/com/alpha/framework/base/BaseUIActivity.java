package com.alpha.framework.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alpha.framework.utils.LogUtils;
import com.alpha.framework.utils.SystemUI;

/**
 *
 */

public class BaseUIActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemUI.fixSystemUI(this);
        LogUtils.i("baseUIActivity");
    }
}
