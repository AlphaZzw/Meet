package com.alpha.framework.bmob;

import android.content.Context;

import cn.bmob.v3.Bmob;

public class BmobManager {
    public static final String BMOB_SDK_ID = "18a690cf9061d3618f6ce8406493d40d";

    private static volatile BmobManager mInstance = null;

    public BmobManager() {
    }

    public static BmobManager getInstance() {
        if (mInstance == null) {
            synchronized (BmobManager.class) {
                if (mInstance == null) {
                    mInstance = new BmobManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化Bmob
     *
     * @param context
     */
    public void initBmob(Context context) {
        Bmob.initialize(context, BMOB_SDK_ID);
    }
}

