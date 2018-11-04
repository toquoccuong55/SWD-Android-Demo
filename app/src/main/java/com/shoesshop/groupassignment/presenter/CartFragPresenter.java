package com.shoesshop.groupassignment.presenter;

import android.app.Application;
import android.content.Context;

import com.shoesshop.groupassignment.ShoematicRepository.ShoematicRepository;
import com.shoesshop.groupassignment.ShoematicRepository.ShoematicRepositoryImpl;
import com.shoesshop.groupassignment.room.entity.Address;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.entity.ProductVariant;
import com.shoesshop.groupassignment.room.manager.AddressManager;
import com.shoesshop.groupassignment.room.manager.ProductManager;
import com.shoesshop.groupassignment.room.manager.UserManager;
import com.shoesshop.groupassignment.room.manager.VariantManager;
import com.shoesshop.groupassignment.view.CartFragView;

import java.util.List;

public class CartFragPresenter {
    private Context mContext;
    private ProductManager mProductManager;
    private UserManager mUserManager;
    private AddressManager mAddressManager;
    private ShoematicRepository mShoematicRepository;
    private CartFragView mCartFragView;

    public CartFragPresenter(Context mContext, CartFragView mCartFragView, Application application) {
        this.mContext = mContext;
        this.mCartFragView = mCartFragView;
        mProductManager = new ProductManager(application);
        mUserManager = new UserManager(application);
        mAddressManager = new AddressManager(application);
        mShoematicRepository = new ShoematicRepositoryImpl();
    }

    public void getOrderItemList() {
        mProductManager.getProductList(new ProductManager.OnDataCallBackProduct() {
            @Override
            public void onDataSuccess(List<Product> product) {
                mCartFragView.showOrderItemList(product);
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
                mCartFragView.showCustomer(customer);
            }

            @Override
            public void onDataFail() {

            }
        });
    }

    public void getAddress() {
        mAddressManager.getAddress(new AddressManager.OnDataCallBackAddress() {
            @Override
            public void onDataSuccess(Address address) {
                mCartFragView.showAddress(address);
            }

            @Override
            public void onDataFail() {

            }
        });
    }

    public void setOrder(){

    }
}
