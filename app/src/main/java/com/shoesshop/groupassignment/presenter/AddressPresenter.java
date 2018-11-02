
package com.shoesshop.groupassignment.presenter;

import android.app.Application;
import android.content.Context;

import com.shoesshop.groupassignment.room.entity.Address;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.manager.AddressManager;
import com.shoesshop.groupassignment.room.manager.UserManager;
import com.shoesshop.groupassignment.view.AddressView;

public class AddressPresenter {
    private Context mContext;
    private AddressView mAddressView;
    private AddressManager mAddressManager;
    private UserManager mUserManager;

    public AddressPresenter(Context mContext, AddressView mAddressView, Application application) {
        this.mContext = mContext;
        this.mAddressView = mAddressView;
        mAddressManager = new AddressManager(application);
        mUserManager = new UserManager(application);
    }

    public void addAddress(Address address) {
        mAddressManager.addAddress(address);
    }

    public void updateAddress(Address address) {
        mAddressManager.updateAddress(address);
    }

    public void getAddress() {
        mAddressManager.getAddress(new AddressManager.OnDataCallBackAddress() {
            @Override
            public void onDataSuccess(Address address) {
                mAddressView.showAddress(address);
            }

            @Override
            public void onDataFail() {

            }
        });
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
}
