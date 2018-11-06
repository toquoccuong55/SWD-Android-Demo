package com.shoesshop.groupassignment.presenter;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.shoesshop.groupassignment.ShoematicRepository.ShoematicRepository;
import com.shoesshop.groupassignment.ShoematicRepository.ShoematicRepositoryImpl;
import com.shoesshop.groupassignment.model.UpdateCustomerResult;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.manager.UserManager;
import com.shoesshop.groupassignment.utils.CallBackData;
import com.shoesshop.groupassignment.view.PersonalInfoView;

public class PersonalInfoPresenter {
    private UserManager mUserManager;
    private Context mContext;
    private ShoematicRepository mShoematicRepository;
    private PersonalInfoView mPersonalInfoView;

    public PersonalInfoPresenter(Context mContext,
                                 PersonalInfoView mPersonalInfoView, Application application) {
        this.mContext = mContext;
        this.mPersonalInfoView = mPersonalInfoView;
        mUserManager = new UserManager(application);
        mShoematicRepository = new ShoematicRepositoryImpl();
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

    public void updateCustomer(Customer customer) {
        mUserManager.updateCustomer(customer);
    }

    public void updateServerCustomer(final Customer customer) {
        mShoematicRepository.updateCustomer(mContext, customer, new CallBackData<UpdateCustomerResult>() {
            @Override
            public void onSuccess(UpdateCustomerResult updateCustomerResult) {
                mPersonalInfoView.updateServerCustomer(customer);
            }

            @Override
            public void onFail(String message) {
                Log.d("PersonalPresenter: ",message);
            }
        });
    }

}
