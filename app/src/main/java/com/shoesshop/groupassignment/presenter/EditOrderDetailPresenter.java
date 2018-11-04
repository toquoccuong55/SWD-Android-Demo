package com.shoesshop.groupassignment.presenter;

import android.app.Application;

import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.manager.ProductManager;

public class EditOrderDetailPresenter {
    private ProductManager mProductManager;

    public EditOrderDetailPresenter( Application application) {
        mProductManager = new ProductManager(application);
    }

    public void deleteProduct(Product product) {
        mProductManager.deleteProduct(product);
    }

    public void updateProduct(Product product){
        mProductManager.updateProduct(product);
    }
}
