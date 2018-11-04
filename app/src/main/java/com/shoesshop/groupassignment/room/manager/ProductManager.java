package com.shoesshop.groupassignment.room.manager;


import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.shoesshop.groupassignment.room.dao.CustomerDao;
import com.shoesshop.groupassignment.room.dao.ProductDao;
import com.shoesshop.groupassignment.room.database.ShoematicDatabase;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.entity.Product;

import java.util.List;

public class ProductManager {
    private ProductDao mProductDao;


    public ProductManager(Application application) {
        ShoematicDatabase libraryDatabase = ShoematicDatabase.getDatabase(application);
        this.mProductDao = libraryDatabase.mProductDao();
    }

    public void getProductList(OnDataCallBackProduct listener) {
        GetProductListAsync getProductListAsync = new GetProductListAsync(mProductDao, listener);
        getProductListAsync.execute();
    }

    public void addProduct(Product product) {
        AddProductAsync addProductAsync = new AddProductAsync(mProductDao);
        addProductAsync.execute(product);
    }

    public void deleteProduct(Product product) {
        DeleteProductAsync deleteProductAsync = new DeleteProductAsync(mProductDao);
        deleteProductAsync.execute(product);
    }
    public void updateProduct(Product product) {
        UpdateProductAsync updateProductAsync = new UpdateProductAsync(mProductDao);
        updateProductAsync.execute(product);
    }

    private class AddProductAsync extends AsyncTask<Product, Void, Void> {
        private ProductDao mProductDaoAsyn;

        public AddProductAsync(ProductDao mProductDao) {
            this.mProductDaoAsyn = mProductDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            try {
                mProductDaoAsyn.insertProduct(products);
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private class DeleteProductAsync extends AsyncTask<Product, Void, Void> {
        private ProductDao mDaoAsync;

        public DeleteProductAsync(ProductDao mDaoAsync) {
            this.mDaoAsync = mDaoAsync;
        }

        @Override
        protected Void doInBackground(Product... products) {
            try {
                mDaoAsync.deleteProduct(products);
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class UpdateProductAsync extends AsyncTask<Product, Void, Void> {
        private ProductDao mDaoAsync;

        public UpdateProductAsync(ProductDao mDaoAsync) {
            this.mDaoAsync = mDaoAsync;
        }

        @Override
        protected Void doInBackground(Product... products) {
            try {
                mDaoAsync.updateProduct(products);
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class GetProductListAsync extends AsyncTask<Product, Void, Void> {
        private ProductDao mProductDaoAsync;
        private List<Product> mProductList;
        private OnDataCallBackProduct mListener;

        public GetProductListAsync(ProductDao mProductDao, OnDataCallBackProduct mListener) {
            this.mProductDaoAsync = mProductDao;
            this.mListener = mListener;
        }

        @Override
        protected Void doInBackground(Product... products) {
            try {
                mProductList = mProductDaoAsync.getProductList();
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mListener.onDataSuccess(mProductList);
        }
    }

    public interface OnDataCallBackProduct {
        void onDataSuccess(List<Product> product);

        void onDataFail();
    }
}
