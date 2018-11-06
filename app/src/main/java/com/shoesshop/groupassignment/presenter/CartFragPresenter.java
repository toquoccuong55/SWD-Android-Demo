package com.shoesshop.groupassignment.presenter;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.shoesshop.groupassignment.ShoematicRepository.ShoematicRepository;
import com.shoesshop.groupassignment.ShoematicRepository.ShoematicRepositoryImpl;
import com.shoesshop.groupassignment.model.Order;
import com.shoesshop.groupassignment.model.SuccessedOrder;
import com.shoesshop.groupassignment.room.entity.Address;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.entity.ProductVariant;
import com.shoesshop.groupassignment.room.manager.AddressManager;
import com.shoesshop.groupassignment.room.manager.ProductManager;
import com.shoesshop.groupassignment.room.manager.UserManager;
import com.shoesshop.groupassignment.room.manager.VariantManager;
import com.shoesshop.groupassignment.room.manager.WishListManager;
import com.shoesshop.groupassignment.utils.CallBackData;
import com.shoesshop.groupassignment.view.CartFragView;

import java.util.List;

public class CartFragPresenter {
    private Context mContext;
    private ProductManager mProductManager;
    private UserManager mUserManager;
    private ShoematicRepository mShoematicRepository;
    private CartFragView mCartFragView;
    private WishListManager mWishListManager;

    public CartFragPresenter(Context mContext, CartFragView mCartFragView, Application application) {
        this.mContext = mContext;
        this.mCartFragView = mCartFragView;
        mProductManager = new ProductManager(application);
        mUserManager = new UserManager(application);
        mWishListManager = new WishListManager(application);
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

    public void setOrder(Order order) {
        mShoematicRepository.setOrder(mContext, order, new CallBackData<SuccessedOrder>() {
            @Override
            public void onSuccess(SuccessedOrder successedOrder) {
                mCartFragView.setOrderSuccess(successedOrder);
            }

            @Override
            public void onFail(String message) {
                Log.e("", message );
            }
        });
    }


    public void deleteCustomerInfo() {
        mUserManager.deleteAllCustomer();
    }

    public void deleteAllProduct() {
        mProductManager.deleteAllProduct();
    }

    public void deleteAllWishList() {
        mWishListManager.deleteAllWishlist();
    }
}
