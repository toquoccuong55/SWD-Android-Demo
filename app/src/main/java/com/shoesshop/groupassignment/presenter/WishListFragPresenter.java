package com.shoesshop.groupassignment.presenter;

import android.app.Application;
import android.content.Context;

import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.manager.ProductManager;
import com.shoesshop.groupassignment.view.WishListFragView;

import java.util.List;

public class WishListFragPresenter {
    private Context mContext;
    private WishListFragView mWishListFragView;
    private ProductManager mProductManager;

    public WishListFragPresenter(Context mContext, WishListFragView mWishListFragView, Application application) {
        this.mContext = mContext;
        this.mWishListFragView = mWishListFragView;
        mProductManager = new ProductManager(application);
    }

    public void getWishList() {
        mProductManager.getProductList(new ProductManager.OnDataCallBackProduct() {
            @Override
            public void onDataSuccess(List<Product> product) {
                mWishListFragView.showWishList(product);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
}
