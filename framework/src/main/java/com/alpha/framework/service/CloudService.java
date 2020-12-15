package com.alpha.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * <p>Class: com.alpha.framework.service.CloudService</p >
 * <p>Description: </p >
 * <pre>
 *
 * </pre>
 *
 * @author zhu zhengwei
 * @date 2020/12/15/下午6:48
 */
public class CloudService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
