package com.shoesshop.groupassignment.presenter;

import android.app.Application;
import android.util.Log;

import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.manager.ProductManager;
import com.shoesshop.groupassignment.room.manager.UserManager;
import com.shoesshop.groupassignment.room.manager.WishListManager;
import com.shoesshop.groupassignment.view.ProfileFragView;

public class ProfileFragPresenter {
    private UserManager mUserManager;
    private ProfileFragView mProfileFragView;
    private ProductManager mProductManager;
    private WishListManager mWishListManager;

    public ProfileFragPresenter(ProfileFragView mProfileFragView, Application application) {
        this.mProfileFragView = mProfileFragView;
        mUserManager = new UserManager(application);
        mProductManager = new ProductManager(application);
    }

    public void getCustomer() {
        mUserManager.getCustomerInfo(new UserManager.OnDataCallBackCustomer() {
            @Override
            public void onDataSuccess(Customer customer) {
                mProfileFragView.showCustomer(customer);
            }

            @Override
            public void onDataFail() {
                Log.e("", "onDataFail: ");
            }
        });
    }

    public void deleteCustomerInfo(){
        mUserManager.deleteAllCustomer();
    }

    public void deleteAllProduct(){
        mProductManager.deleteAllProduct();
    }

    public void deleteAllWishList(){
        mWishListManager.deleteAllWishlist();
    }
}
