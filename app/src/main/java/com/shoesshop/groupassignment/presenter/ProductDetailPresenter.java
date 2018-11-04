package com.shoesshop.groupassignment.presenter;

import android.app.Application;
import android.content.Context;

import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.entity.ProductVariant;
import com.shoesshop.groupassignment.room.manager.ProductManager;
import com.shoesshop.groupassignment.room.manager.VariantManager;

public class ProductDetailPresenter {
    private ProductManager mProductManager;

    public ProductDetailPresenter(Application application) {
        mProductManager = new ProductManager(application);
    }

    public void addFavorite(Product product) {
        mProductManager.addProduct(product);
    }

    public void deleteFavorite(Product product) {
        mProductManager.deleteProduct(product);
    }

    public void addProduct(Product product) {
        mProductManager.addProduct(product);
    }
}
