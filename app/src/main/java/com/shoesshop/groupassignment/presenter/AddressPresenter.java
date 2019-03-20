
package com.shoesshop.groupassignment.presenter;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.shoesshop.groupassignment.ShoematicRepository.ShoematicRepository;
import com.shoesshop.groupassignment.ShoematicRepository.ShoematicRepositoryImpl;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.manager.UserManager;
import com.shoesshop.groupassignment.utils.CallBackData;
import com.shoesshop.groupassignment.view.AddressView;

public class AddressPresenter {
    private Context mContext;
    private AddressView mAddressView;
    private UserManager mUserManager;
    private ShoematicRepository mShoematicRepository;

    public AddressPresenter(Context context, AddressView mAddressView, Application application) {
        this.mContext = context;
        this.mAddressView = mAddressView;
        mUserManager = new UserManager(application);
        mShoematicRepository = new ShoematicRepositoryImpl();
    }

    public void getCustomer() {
        mUserManager.getCustomerInfo(new UserManager.OnDataCallBackCustomer() {
            @Override
            public void onDataSuccess(Customer customer) {
                mAddressView.showCustomer(customer);
            }

            @Override
            public void onDataFail() {

            }
        });
    }

    public void updateDBCustomer(final Customer customer){
        mShoematicRepository.updateCustomer(mContext, customer, new CallBackData<Customer>() {
            @Override
            public void onSuccess(Customer customerResult) {
                mAddressView.updateServerCustomer(customer);
            }

            @Override
            public void onFail(String message) {
                Log.d("PersonalPresenter: ",message);
            }
        });
    }


    public void updateCustomer(Customer customer){
        mUserManager.updateCustomer(customer);
    }
}
