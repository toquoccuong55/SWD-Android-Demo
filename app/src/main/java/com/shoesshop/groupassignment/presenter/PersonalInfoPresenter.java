package com.shoesshop.groupassignment.presenter;

import android.app.Application;
import android.content.Context;

import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.manager.UserManager;
import com.shoesshop.groupassignment.view.PersonalInfoView;

public class PersonalInfoPresenter {
    private UserManager mUserManager;
    private Context mContext;
    private PersonalInfoView mPersonalInfoView;

    public PersonalInfoPresenter(Context mContext,
                                 PersonalInfoView mPersonalInfoView, Application application) {
        this.mContext = mContext;
        this.mPersonalInfoView = mPersonalInfoView;
        mUserManager = new UserManager(application);
    }

    public void getCustomerInfo() {
        mUserManager.getCustomerInfo(new UserManager.OnDataCallBackCustomer() {
            @Override
            public void onDataSuccess(Customer customer) {
                mPersonalInfoView.showCustomerInfo(customer);
            }

            @Override
            public void onDataFail() {
            }
        });
    }

    public void updateCustomer(Customer customer){
        mUserManager.updateCustomer(customer);
    }
}
