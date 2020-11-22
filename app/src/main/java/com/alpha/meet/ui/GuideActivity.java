package com.alpha.meet.ui;

import android.os.Bundle;

import com.alpha.framework.base.BaseUIActivity;
import com.alpha.framework.utils.LogUtils;
import com.alpha.meet.R;

public class GuideActivity extends BaseUIActivity {


    /**
     * 1.ViewPager : 适配器|帧动画播放
     * 2.小圆点的逻辑
     * 3.歌曲的播放
     * 4.属性动画旋转
     * 5.跳转
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
    }

}