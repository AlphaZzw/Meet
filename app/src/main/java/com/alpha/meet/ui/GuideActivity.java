package com.alpha.meet.ui;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.alpha.framework.base.BasePagerAdapter;
import com.alpha.framework.base.BaseUIActivity;
import com.alpha.framework.manager.MediaPlayerManager;
import com.alpha.framework.utils.AnimUtils;
import com.alpha.framework.utils.LogUtils;
import com.alpha.meet.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseUIActivity implements View.OnClickListener {

    private List<View> mPageList = new ArrayList<>();

    private ImageView iv_music_switch;
    private TextView tv_guide_skip;
    private ImageView iv_guide_point_1;
    private ImageView iv_guide_point_2;
    private ImageView iv_guide_point_3;
    private ViewPager mViewPager;

    private View mView1;
    private View mView2;
    private View mView3;
    private BasePagerAdapter mPagerAdapter;
    private ImageView mIv_guide_night;
    private ImageView mIv_guide_star;
    private ImageView mIv_guide_smile;
    private MediaPlayerManager mPlayerManager;
    private ObjectAnimator mAnimator;

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
        initView();
    }

    private void initView() {
        iv_music_switch = (ImageView) findViewById(R.id.iv_music_switch);
        tv_guide_skip = (TextView) findViewById(R.id.tv_guide_skip);
        iv_guide_point_1 = (ImageView) findViewById(R.id.iv_guide_point_1);
        iv_guide_point_2 = (ImageView) findViewById(R.id.iv_guide_point_2);
        iv_guide_point_3 = (ImageView) findViewById(R.id.iv_guide_point_3);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);

        mView1 = View.inflate(this, R.layout.layout_pager_guide_1, null);
        mView2 = View.inflate(this, R.layout.layout_pager_guide_2, null);
        mView3 = View.inflate(this, R.layout.layout_pager_guide_3, null);

        iv_music_switch.setOnClickListener(this);
        tv_guide_skip.setOnClickListener(this);

        mPageList.add(mView1);
        mPageList.add(mView2);
        mPageList.add(mView3);

        //预加载
        mViewPager.setOffscreenPageLimit(mPageList.size());

        mPagerAdapter = new BasePagerAdapter(mPageList);
        mViewPager.setAdapter(mPagerAdapter);

        //帧动画
        mIv_guide_night = mView2.findViewById(R.id.iv_guide_night);
        mIv_guide_star = mView1.findViewById(R.id.iv_guide_star);
        mIv_guide_smile = mView3.findViewById(R.id.iv_guide_smile);

        //播放帧动画
        AnimationDrawable animNight = (AnimationDrawable) mIv_guide_night.getBackground();
        animNight.start();
        AnimationDrawable animSmile = (AnimationDrawable) mIv_guide_smile.getBackground();
        animSmile.start();
        AnimationDrawable animStar = (AnimationDrawable) mIv_guide_star.getBackground();
        animStar.start();

        //小圆点逻辑
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectPoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //歌曲的逻辑
        startMusic();


    }

    private void startMusic() {
        mPlayerManager = new MediaPlayerManager();
        mPlayerManager.setLooping(true);
        final AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.guide);
        mPlayerManager.startPlay(file);
        mPlayerManager.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlayerManager.startPlay(file);
            }
        });

        //旋转动画
        mAnimator = AnimUtils.rotation(iv_music_switch);
        mAnimator.start();

    }

    /**
     * 动态选择小圆点
     *
     * @param position
     */
    private void selectPoint(int position) {
        switch (position) {
            case 0:
                iv_guide_point_1.setImageResource(R.drawable.img_guide_point_p);
                iv_guide_point_2.setImageResource(R.drawable.img_guide_point);
                iv_guide_point_3.setImageResource(R.drawable.img_guide_point);
                break;
            case 1:
                iv_guide_point_1.setImageResource(R.drawable.img_guide_point);
                iv_guide_point_2.setImageResource(R.drawable.img_guide_point_p);
                iv_guide_point_3.setImageResource(R.drawable.img_guide_point);
                break;
            case 2:
                iv_guide_point_1.setImageResource(R.drawable.img_guide_point);
                iv_guide_point_2.setImageResource(R.drawable.img_guide_point);
                iv_guide_point_3.setImageResource(R.drawable.img_guide_point_p);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_music_switch:
                if (mPlayerManager.MEDIA_STATUS == MediaPlayerManager.MEDIA_STATUS_PAUSE) {
                    mAnimator.start();
                    mPlayerManager.continuePlay();
                    iv_music_switch.setImageResource(R.drawable.img_guide_music);
                } else if (mPlayerManager.MEDIA_STATUS == MediaPlayerManager.MEDIA_STATUS_PLAY) {
                    mAnimator.pause();
                    mPlayerManager.pausePlay();
                    iv_music_switch.setImageResource(R.drawable.img_guide_music_off);
                }
                break;
            case R.id.tv_guide_skip:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

}