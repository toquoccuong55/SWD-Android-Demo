package com.shoesshop.groupassignment.presenter;

import android.app.Application;

import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.manager.UserManager;
import com.shoesshop.groupassignment.view.SplashView;

public class SplashPresenter {
    private UserManager mUserManagement;
    private SplashView mSplashView;

    public SplashPresenter(SplashView mSplashView, Application application) {
        this.mSplashView = mSplashView;
        mUserManagement = new UserManager(application);
    }

    public void getCustomer() {
        mUserManagement.getCustomerInfo(new UserManager.OnDataCallBackCustomer() {
            @Override
            public void onDataSuccess(Customer customer) {
                mSplashView.showCustomer(customer);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
}
