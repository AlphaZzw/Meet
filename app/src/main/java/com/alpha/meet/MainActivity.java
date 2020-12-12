package com.alpha.meet;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alpha.framework.base.BaseUIActivity;
import com.alpha.framework.utils.LogUtils;

import java.util.List;

public class MainActivity extends BaseUIActivity {

    public static final int REQUEST_CALL_PERMISSION = 10111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermiss();
    }

    private void requestPermiss() {
        request(new OnPermissionsResult() {
            @Override
            public void OnSuccess() {
                Toast.makeText(MainActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
                LogUtils.i("请求成功");
            }

            @Override
            public void OnFail(List<String> onPermissions) {

            }
        });
    }
}