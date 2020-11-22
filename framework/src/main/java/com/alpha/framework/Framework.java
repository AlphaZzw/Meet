package com.alpha.framework;

import android.content.Context;

import com.alpha.framework.utils.LogUtils;
import com.alpha.framework.utils.SpUtils;

public class Framework {

    //如果一个字段被声明成volatile，Java线程内存模型确保所有线程看到这个变量的值是一致的，同时还会禁止指令重排序
    private volatile static Framework sFramework;

    private Framework() {
    }

    //双重校验锁
    public static Framework getFramework() {
        if (sFramework == null) {
            synchronized (Framework.class) {
                if (sFramework == null) {
                    sFramework = new Framework();
                }
            }
        }
        return sFramework;
    }

    public void initFramework(Context mContext) {
        LogUtils.i("initFramework");
        SpUtils.getInstance().initSp(mContext);

    }

}
