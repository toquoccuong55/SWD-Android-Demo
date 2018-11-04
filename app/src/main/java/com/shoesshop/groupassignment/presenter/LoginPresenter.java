package com.shoesshop.groupassignment.presenter;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.shoesshop.groupassignment.ShoematicRepository.ShoematicRepository;
import com.shoesshop.groupassignment.ShoematicRepository.ShoematicRepositoryImpl;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.manager.UserManager;
import com.shoesshop.groupassignment.utils.CallBackData;
import com.shoesshop.groupassignment.view.LoginView;

public class LoginPresenter {
    private Context mContext;
    private ShoematicRepository mShoematicRepository;
    private LoginView mLoginView;
    private UserManager mUserManager;

    public LoginPresenter(Context mContext, LoginView mLoginView, Application application) {
        this.mContext = mContext;
        this.mLoginView = mLoginView;
        mShoematicRepository = new ShoematicRepositoryImpl();
        mUserManager = new UserManager(application);
    }


    public void loginByPhone(String accessToken) {
        mShoematicRepository.loginByPhone(mContext, accessToken, new CallBackData<Customer>() {
            @Override
            public void onSuccess(Customer customer) {
                mLoginView.loginByPhoneSuccess(customer);
            }

            @Override
            public void onFail(String message) {
                mLoginView.loginByPhoneFailed(message);
            }
        });
    }

    public void loginByFacebook(String fbAccessToken) {
        mShoematicRepository.loginByFacebook(mContext, fbAccessToken, new CallBackData<Customer>() {
            @Override
            public void onSuccess(Customer customer) {
                mLoginView.loginByFacebookSuccess(customer);
            }

            @Override
            public void onFail(String message) {
                mLoginView.loginByFacebookFailed(message);
            }
        });
    }

    public void addCustomer(final Customer newCustomer) {
        mUserManager.addCustomer(newCustomer, new UserManager.OnDataCallBackCustomer() {
            @Override
            public void onDataSuccess(Customer customer) {
                mLoginView.addCustomerSuccess(newCustomer);
            }

            @Override
            public void onDataFail() {
                Log.e("", "onDataFail: ");
            }
        });
    }

}
