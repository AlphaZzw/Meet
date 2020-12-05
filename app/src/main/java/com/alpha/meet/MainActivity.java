package com.alpha.meet;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alpha.framework.bmob.BmobManager;
import com.alpha.framework.bmob.IMUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IMUser imUser = BmobManager.getInstance().getUser();
        Toast.makeText(this, "imUser" + imUser.getMobilePhoneNumber(), Toast.LENGTH_SHORT).show();

    }
}