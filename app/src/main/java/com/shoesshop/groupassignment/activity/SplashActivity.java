package com.shoesshop.groupassignment.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.messaging.FirebaseMessaging;
import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.presenter.SplashPresenter;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.utils.ConstantManager;
import com.shoesshop.groupassignment.view.SplashView;

public class SplashActivity extends AppCompatActivity implements SplashView {
    private SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseMessaging.getInstance().subscribeToTopic("SHOEMATICS");
        delaySplashScreen();
    }

    private void delaySplashScreen() {
        mSplashPresenter = new SplashPresenter(SplashActivity.this, getApplication());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSplashPresenter.getCustomer();
            }
        }, ConstantManager.SPLASH_TIME_OUT);
    }

    @Override
    public void showCustomer(Customer customer) {
        if (customer != null) {
            if(customer.isFirstLogin()){
                FirstLoginActivity.intentToFirstLoginActivitiy(SplashActivity.this);
            }else{
                HomeActivity.intentToHomeActivitiy(SplashActivity.this);
            }
            finish();
        } else {
            LoginActivity.intentToLoginActivitiy(SplashActivity.this);
        }
    }
}
