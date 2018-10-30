package com.shoesshop.groupassignment.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.utils.ConstantDataManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        delaySplashScreen();
    }

    private void delaySplashScreen() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginActivity.intentToLoginActivitiy(SplashActivity.this);
            }
        }, ConstantDataManager.SPLASH_TIME_OUT);
    }
}
