package com.alpha.framework.manager;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.alpha.framework.utils.LogUtils;

import java.io.IOException;

public class MediaPlayerManager {
    //播放
    public static final int MEDIA_STATUS_PLAY = 0;
    //暂停
    public static final int MEDIA_STATUS_PAUSE = 1;
    //停止
    public static final int MEDIA_STATUS_STOP = 2;

    public int MEDIA_STATUS = MEDIA_STATUS_STOP;

    private final MediaPlayer mMediaPlayer;
    private OnMusicProgressListener mOnMusicProgressListener;

    public static final int H_PROGRESS = 1000;

    public MediaPlayerManager() {
        mMediaPlayer = new MediaPlayer();
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case H_PROGRESS:
                    if (mOnMusicProgressListener != null) {
                        int currentPosition = getCurrentPosition();
                        int pos = (int) (((float) currentPosition) / ((float) getDuration()) * 100);
                        mOnMusicProgressListener.OnProgress(currentPosition, pos);
                        mHandler.sendEmptyMessageDelayed(H_PROGRESS, 1000);
                    }
            }
            return false;
        }
    });

    /**
     * 是否在播放
     *
     * @return
     */
    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }


    /**
     * 开始播放
     *
     * @param path
     */
    public void startPlay(AssetFileDescriptor path) {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(path.getFileDescriptor(), path.getStartOffset(), path.getLength());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            MEDIA_STATUS = MEDIA_STATUS_PLAY;
            mHandler.sendEmptyMessage(H_PROGRESS);
        } catch (IOException e) {
            LogUtils.i(e.toString());
            e.printStackTrace();
        }
    }

    /**
     * 开始播放
     *
     * @param path
     */
    public void startPlay(String path) {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            MEDIA_STATUS = MEDIA_STATUS_PLAY;
            mHandler.sendEmptyMessage(H_PROGRESS);
        } catch (IOException e) {
            LogUtils.i(e.toString());
            e.printStackTrace();
        }
    }

    /**
     * 暂停播放
     */
    public void pausePlay() {
        if (isPlaying()) {
            mMediaPlayer.pause();
            MEDIA_STATUS = MEDIA_STATUS_PAUSE;
            removeHandle();
        }
    }

    /**
     * 继续播放
     */
    public void continuePlay() {
        mMediaPlayer.start();
        MEDIA_STATUS = MEDIA_STATUS_PLAY;
        mHandler.sendEmptyMessage(H_PROGRESS);
    }

    /**
     * 停止播放
     */
    public void stopPlay() {
        mMediaPlayer.stop();
        MEDIA_STATUS = MEDIA_STATUS_STOP;
        removeHandle();
    }

    /**
     * 获取当前位置
     *
     * @return
     */
    public int getCurrentPosition() {
        return mMediaPlayer.getCurrentPosition();
    }

    /**
     * 获取总时长
     *
     * @return
     */
    public int getDuration() {
        return mMediaPlayer.getDuration();
    }

    /**
     * 是否循环
     *
     * @param isLooping
     */
    public void setLooping(boolean isLooping) {
        mMediaPlayer.setLooping(isLooping);
    }

    /*
     * 跳转位置
     *
     * @param ms
     */
    public void seekTo(int ms) {
        mMediaPlayer.seekTo(ms);
    }

    /**
     * 播放结束
     *
     * @param listener
     */
    public void setOnCompletionListener(MediaPlayer.OnCompletionListener listener) {
        mMediaPlayer.setOnCompletionListener(listener);
    }

    /**
     * 播放错误
     *
     * @param listener
     */
    public void setOnErrorListener(MediaPlayer.OnErrorListener listener) {
        mMediaPlayer.setOnErrorListener(listener);
    }

    /**
     * 播放进度
     *
     * @param listener
     */
    public void setOnProgressListener(OnMusicProgressListener listener) {
        mOnMusicProgressListener = listener;
    }

    public interface OnMusicProgressListener {
        void OnProgress(int progress, int pos);
    }

    /**
     * 无歌曲不需要监听进度
     */
    public void removeHandle() {
        if (mHandler != null) {
            mHandler.removeMessages(H_PROGRESS);
        }
    }

}