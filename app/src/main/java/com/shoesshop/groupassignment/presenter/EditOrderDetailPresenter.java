package com.shoesshop.groupassignment.presenter;

import android.app.Application;

import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.manager.ProductManager;
import com.shoesshop.groupassignment.view.EditOrderView;

import java.util.List;

public class EditOrderDetailPresenter {
    private ProductManager mProductManager;
    private EditOrderView mEditOrderView;

    public EditOrderDetailPresenter(EditOrderView mEditOrderView, Application application) {
        this.mEditOrderView = mEditOrderView;
        mProductManager = new ProductManager(application);
    }

    public void deleteProduct(Product product) {
        mProductManager.deleteProduct(product);
    }

    public void updateProduct(Product product) {
        mProductManager.updateProduct(product);
    }

    public void getShoppingBag(){
        mProductManager.getProductList(new ProductManager.OnDataCallBackProduct() {
            @Override
            public void onDataSuccess(List<Product> product) {
                mEditOrderView.showShoppingBag(product);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
}
