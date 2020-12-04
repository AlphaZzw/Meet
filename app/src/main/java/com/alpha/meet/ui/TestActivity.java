package com.alpha.meet.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alpha.framework.base.BaseUIActivity;
import com.alpha.framework.bmob.MyData;
import com.alpha.framework.utils.LogUtils;
import com.alpha.meet.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class TestActivity extends BaseUIActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_test);
        Button add = (Button) findViewById(R.id.btn_add);
        Button delete = (Button) findViewById(R.id.btn_delete);
        Button change = (Button) findViewById(R.id.btn_change);
        Button cha = (Button) findViewById(R.id.btn_cha);
        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        cha.setOnClickListener(this);
        change.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                MyData myData = new MyData();
                myData.setName("张三");
                myData.setSex(0);
                myData.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Toast.makeText(TestActivity.this, "新增成功" + s, Toast.LENGTH_SHORT).show();
                            LogUtils.i("新增成功" + s);
                        }
                    }
                });
                break;
            case R.id.btn_delete:

                break;
            case R.id.btn_change:

                break;
            case R.id.btn_cha:

                break;

        }
    }
}
