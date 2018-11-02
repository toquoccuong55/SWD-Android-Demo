package com.shoesshop.groupassignment.ShoematicRepository;

import android.content.Context;

import com.shoesshop.groupassignment.model.Order;
import com.shoesshop.groupassignment.model.OrderResult;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.utils.CallBackData;

import java.util.List;

public interface ShoematicRepository {

    void loginByPhone(Context context, String fbAccessToken, CallBackData<Customer> callBackData);

    void loginByFacebook(Context context, String fbAccessToken, CallBackData<Customer> callBackData);

    void getProductList(Context context, CallBackData<List<Product>> callBackData);

    void setOrder(Context context, Order order, String accessToken, CallBackData<OrderResult> callBackData);

}
