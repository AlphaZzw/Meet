package com.alpha.meet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.alpha.meet.R;
import com.alpha.meet.adapter.CloudTagAdapter;
import com.moxun.tagcloudlib.view.TagCloudView;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Class: com.alpha.meet.fragment.StarFragment</p >
 * <p>Description: </p >
 * <pre>
 *
 * </pre>
 *
 * @author zhu zhengwei
 * @date 2020/12/12/下午2:02
 */
public class StarFragment extends Fragment implements View.OnClickListener {

    private TextView tv_star_title;
    private ImageView iv_camera;
    private ImageView iv_add;

    private TagCloudView mCloudView;

    private LinearLayout ll_random;
    private LinearLayout ll_soul;
    private LinearLayout ll_fate;
    private LinearLayout ll_love;

    //连接状态
    private TextView tv_connect_status;

    private List<String> mStarList = new ArrayList<>();
    private CloudTagAdapter mCloudTagAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_star, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        iv_camera = view.findViewById(R.id.iv_camera);
        iv_add = view.findViewById(R.id.iv_add);
        tv_connect_status = view.findViewById(R.id.tv_connect_status);

        tv_star_title = view.findViewById(R.id.tv_star_title);

        mCloudView = view.findViewById(R.id.mCloudView);

        ll_random = view.findViewById(R.id.ll_random);
        ll_soul = view.findViewById(R.id.ll_soul);
        ll_fate = view.findViewById(R.id.ll_fate);
        ll_love = view.findViewById(R.id.ll_love);

        iv_camera.setOnClickListener(this);
        iv_add.setOnClickListener(this);

        ll_random.setOnClickListener(this);
        ll_soul.setOnClickListener(this);
        ll_fate.setOnClickListener(this);
        ll_love.setOnClickListener(this);

        for (int i = 0; i < 100; i++) {
            mStarList.add("Star" + i);
        }

        //数据绑定
        mCloudTagAdapter = new CloudTagAdapter(getActivity(), mStarList);
        mCloudView.setAdapter(mCloudTagAdapter);

        //监听点击事件
        mCloudView.setOnTagClickListener(new TagCloudView.OnTagClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, int position) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
