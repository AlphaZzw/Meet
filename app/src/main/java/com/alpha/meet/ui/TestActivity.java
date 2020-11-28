package com.alpha.meet.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alpha.framework.base.BaseUIActivity;
import com.alpha.meet.R;

public class TestActivity extends BaseUIActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_code_view);
    }
}
