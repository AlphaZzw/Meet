package com.alpha.meet.ui;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.alpha.framework.base.BasePagerAdapter;
import com.alpha.framework.base.BaseUIActivity;
import com.alpha.framework.utils.LogUtils;
import com.alpha.meet.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseUIActivity {

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

        


    }

}