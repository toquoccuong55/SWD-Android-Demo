package com.shoesshop.groupassignment.presenter;

import android.app.Application;

import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.entity.Wishlist;
import com.shoesshop.groupassignment.room.manager.ProductManager;
import com.shoesshop.groupassignment.room.manager.WishListManager;
import com.shoesshop.groupassignment.view.ProductDetailView;

import java.util.List;

public class ProductDetailPresenter {
    private ProductManager mProductManager;
    private WishListManager mWishListManager;
    private ProductDetailView mProductDetailView;

    public ProductDetailPresenter(Application application, ProductDetailView productDetailView) {
        mProductManager = new ProductManager(application);
        mWishListManager = new WishListManager(application);
        mProductDetailView = productDetailView;
    }

    public void getWishList() {
        mWishListManager.getWishList(new WishListManager.OnDataCallBackWishList() {
            @Override
            public void onDataSuccess(List<Wishlist> wishlistList) {
                mProductDetailView.showWishList(wishlistList);
            }

            @Override
            public void onDataFail() {

            }
        });
    }

    public void addWishList(Wishlist wishlist) {
        mWishListManager.addWishlist(wishlist);
    }

    public void deleteWishList(Wishlist wishlist) {
        mWishListManager.deleteWishlist(wishlist);
    }

    public void addProduct(Product product) {
        mProductManager.addProduct(product);
    }

    public void getShoppingBag(){
        mProductManager.getProductList(new ProductManager.OnDataCallBackProduct() {
            @Override
            public void onDataSuccess(List<Product> product) {
                mProductDetailView.showShoppingBag(product);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
}
