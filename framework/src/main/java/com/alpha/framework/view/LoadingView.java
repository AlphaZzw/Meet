package com.alpha.framework.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpha.framework.R;
import com.alpha.framework.manager.DialogManager;
import com.alpha.framework.utils.AnimUtils;

public class LoadingView {

    private final DialogView mLoadingView;
    private final TextView mTv_loading;
    private final ImageView mIv_loading;
    private final ObjectAnimator mAnimator;

    public LoadingView(Context context) {
        mLoadingView = DialogManager.getInstance().initView(context, R.layout.dialog_loading);
        mIv_loading = (ImageView) mLoadingView.findViewById(R.id.iv_loading);
        mTv_loading = (TextView) mLoadingView.findViewById(R.id.tv_loading_text);
        mAnimator = AnimUtils.rotation(mIv_loading);
    }

    /**
     * 设置加载提示的文本
     *
     * @param text
     */
    public void setLoadingView(String text) {
        if (!TextUtils.isEmpty(text)) {
            mTv_loading.setText(text);
        }
    }

    public void show() {
        mAnimator.start();
        DialogManager.getInstance().show(mLoadingView);
    }

    public void show(String text) {
        mAnimator.start();
        setLoadingView(text);
        DialogManager.getInstance().show(mLoadingView);
    }

    public void hide() {
        mAnimator.pause();
        DialogManager.getInstance().hide(mLoadingView);
    }

    public void setCancelable(boolean flag) {
        mLoadingView.setCancelable(flag);
    }
}
