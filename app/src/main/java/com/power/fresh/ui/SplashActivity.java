package com.power.fresh.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.power.fresh.MainActivity;
import com.power.fresh.R;
import com.power.fresh.ui.login.LoginActivity;
import com.powerrich.common.base.UIActivity;

public class SplashActivity extends UIActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        postDelayed(() ->{
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        } , 1000);
    }

    @Override
    protected void initData() {

    }
}
