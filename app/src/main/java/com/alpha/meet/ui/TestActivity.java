package com.alpha.meet.ui;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alpha.framework.base.BaseUIActivity;
import com.alpha.framework.view.PictureV;
import com.alpha.meet.R;

public class TestActivity extends BaseUIActivity {
    private SeekBar mSeekBar;
    private PictureV mPictureV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_code_view);
        initView();
    }

    private void initView() {
        mSeekBar = (SeekBar) findViewById(R.id.mSeekBar);
        mPictureV = (PictureV) findViewById(R.id.mPictureV);

        mPictureV.setPictureVSucceed(new PictureV.OnPictureVSucceed() {
            @Override
            public void outValue(int pos, int maxValue) {
                mSeekBar.setMax(maxValue);
                mSeekBar.setProgress(pos);
            }

            @Override
            public void onSucceed() {
                Toast.makeText(TestActivity.this, "成功", Toast.LENGTH_SHORT).show();
            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mPictureV.updateX(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
