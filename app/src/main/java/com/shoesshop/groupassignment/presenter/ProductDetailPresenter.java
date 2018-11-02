package com.shoesshop.groupassignment.presenter;

import android.app.Application;
import android.content.Context;

import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.entity.ProductVariant;
import com.shoesshop.groupassignment.room.manager.ProductManager;
import com.shoesshop.groupassignment.room.manager.VariantManager;

public class ProductDetailPresenter {
    private Context mContext;
    private ProductManager mProductManager;
    private VariantManager mVariantManager;

    public ProductDetailPresenter(Context mContext, Application application) {
        this.mContext = mContext;
        mProductManager = new ProductManager(application);
        mVariantManager = new VariantManager(application);
    }

    public void addFavorite(Product product) {
        mProductManager.addProduct(product);
    }

    public void deleteFavorite(Product product) {
        mProductManager.deleteProduct(product);
    }

    public void addVariant(ProductVariant variant){
        mVariantManager.addVariant(variant);
    }
}
