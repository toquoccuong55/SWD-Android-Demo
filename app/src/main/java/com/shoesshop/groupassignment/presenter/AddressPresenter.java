
package com.shoesshop.groupassignment.presenter;

import android.app.Application;

import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.manager.UserManager;
import com.shoesshop.groupassignment.view.AddressView;

public class AddressPresenter {
    private AddressView mAddressView;
    private UserManager mUserManager;

    public AddressPresenter( AddressView mAddressView, Application application) {
        this.mAddressView = mAddressView;
        mUserManager = new UserManager(application);
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

    public void updateCustomer(Customer customer){
        mUserManager.updateCustomer(customer);
    }
}
