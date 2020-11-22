package com.alpha.framework.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

public class SystemUI {
    public static void fixSystemUI(Activity mActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getDecorView()获取最顶层的View
            mActivity.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            mActivity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
