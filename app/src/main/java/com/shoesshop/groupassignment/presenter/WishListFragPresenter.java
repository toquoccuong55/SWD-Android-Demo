package com.shoesshop.groupassignment.presenter;

import android.app.Application;
import android.content.Context;

import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.entity.Wishlist;
import com.shoesshop.groupassignment.room.manager.ProductManager;
import com.shoesshop.groupassignment.room.manager.WishListManager;
import com.shoesshop.groupassignment.view.WishListFragView;

import java.util.List;

public class WishListFragPresenter {
    private WishListFragView mWishListFragView;
    private WishListManager mWishListManager;

    public WishListFragPresenter(WishListFragView mWishListFragView, Application application) {
        this.mWishListFragView = mWishListFragView;
        mWishListManager = new WishListManager(application);
    }

    public void getWishList() {
        mWishListManager.getWishList(new WishListManager.OnDataCallBackWishList() {
            @Override
            public void onDataSuccess(List<Wishlist> wishlistList) {
                mWishListFragView.showWishList(wishlistList);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
}
