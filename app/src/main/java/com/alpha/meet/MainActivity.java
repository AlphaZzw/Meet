package com.alpha.meet;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alpha.framework.manager.MediaPlayerManager;
import com.alpha.framework.utils.LogUtils;
import com.alpha.framework.utils.TimeUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.e("test");
        LogUtils.i("test");
        LogUtils.i(TimeUtils.formatDuring(System.currentTimeMillis()));

        final MediaPlayerManager mediaPlayerManager = new MediaPlayerManager();
        AssetFileDescriptor fileDescriptor = getResources().openRawResourceFd(R.raw.guide);
        mediaPlayerManager.startPlay(fileDescriptor);

        mediaPlayerManager.setOnProgressListener(new MediaPlayerManager.OnMusicProgressListener() {
            @Override
            public void OnProgress(int progress, int pos) {
                LogUtils.i("process:" + pos + "%");
            }
        });


    }
}