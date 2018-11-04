package com.shoesshop.groupassignment.presenter;

import android.app.Application;
import android.content.Context;

import com.shoesshop.groupassignment.ShoematicRepository.ShoematicRepository;
import com.shoesshop.groupassignment.ShoematicRepository.ShoematicRepositoryImpl;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.manager.UserManager;
import com.shoesshop.groupassignment.utils.CallBackData;
import com.shoesshop.groupassignment.view.FirstLoginView;

public class FirstLoginPresenter {
    private FirstLoginView mFirstLoginView;
    private UserManager mUserManager;
    private ShoematicRepository mShoematicRepository;
    private Context mContext;

    public FirstLoginPresenter(Context context, FirstLoginView mFirstLoginView, Application application) {
        mContext = context;
        this.mFirstLoginView = mFirstLoginView;
        mUserManager = new UserManager(application);
        mShoematicRepository = new ShoematicRepositoryImpl();
    }

    public void insertCustomerToDB(final Customer customer) {
        mShoematicRepository.updateCustomer(mContext, customer, new CallBackData<String>() {
            @Override
            public void onSuccess(String accessToken) {
                if (accessToken != null) {
                    mFirstLoginView.insertCustomerToDBSuccess(customer);
                } else {
                    mFirstLoginView.insertCustomerToDBFail("");
                }

            }

            @Override
            public void onFail(String message) {
                mFirstLoginView.insertCustomerToDBFail(message);
            }
        });
    }

    public void addCustomer(Customer customer) {
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
