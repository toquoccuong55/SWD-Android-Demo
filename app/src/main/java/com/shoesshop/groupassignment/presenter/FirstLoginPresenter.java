package com.shoesshop.groupassignment.presenter;

import android.app.Application;

import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.manager.UserManager;
import com.shoesshop.groupassignment.view.FirstLoginView;

public class FirstLoginPresenter {
    private FirstLoginView mFirstLoginView;
    private UserManager mUserManager;

    public FirstLoginPresenter(FirstLoginView mFirstLoginView, Application application) {
        this.mFirstLoginView = mFirstLoginView;
        mUserManager = new UserManager(application);
    }

    public void addCustomer(Customer customer){
        mUserManager.addCustomer(customer, new UserManager.OnDataCallBackCustomer() {
            @Override
            public void onDataSuccess(Customer customer) {
                mFirstLoginView.addCustomer(true);
            }

            @Override
            public void onDataFail() {
                mFirstLoginView.addCustomer(false);
            }
        });
    }
}
