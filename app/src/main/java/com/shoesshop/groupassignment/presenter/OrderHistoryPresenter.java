package com.shoesshop.groupassignment.presenter;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.shoesshop.groupassignment.ShoematicRepository.ShoematicRepository;
import com.shoesshop.groupassignment.ShoematicRepository.ShoematicRepositoryImpl;
import com.shoesshop.groupassignment.model.OrderHistory;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.manager.UserManager;
import com.shoesshop.groupassignment.utils.CallBackData;
import com.shoesshop.groupassignment.view.OrderHistoryView;

import java.util.List;

public class OrderHistoryPresenter {
    private Context mContext;
    private ShoematicRepository mShoematicRepository;
    private OrderHistoryView mOrderHistoryView;
    private UserManager mUserManager;

    public OrderHistoryPresenter(Context mContext, OrderHistoryView mOrderHistoryView, Application application) {
        this.mContext = mContext;
        this.mOrderHistoryView = mOrderHistoryView;
        mShoematicRepository = new ShoematicRepositoryImpl();
        mUserManager = new UserManager(application);
    }

    public void getCustomer() {
        mUserManager.getCustomerInfo(new UserManager.OnDataCallBackCustomer() {
            @Override
            public void onDataSuccess(Customer customer) {
                mOrderHistoryView.showCustomer(customer);
            }

            @Override
            public void onDataFail() {

            }
        });
    }

    public void getOrderHistory(String accessToken) {
        mShoematicRepository.getOrderHistory(mContext, accessToken, new CallBackData<List<OrderHistory>>() {
            @Override
            public void onSuccess(List<OrderHistory> orderHistories) {
                mOrderHistoryView.showOrderHistory(orderHistories);
            }

            @Override
            public void onFail(String message) {
                Log.e("OrderHistoryPresenter", message);
            }
        });
    }

    public void getProductList(){
        mShoematicRepository.getProductList(mContext, new CallBackData<List<Product>>() {
            @Override
            public void onSuccess(List<Product> productList) {
                mOrderHistoryView.showProductList(productList);
            }

            @Override
            public void onFail(String message) {
                Log.e("OrderHistoryPresenter: ", message );
            }
        });
    }
}
