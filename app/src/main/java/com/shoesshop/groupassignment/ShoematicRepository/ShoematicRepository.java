package com.shoesshop.groupassignment.ShoematicRepository;

import android.content.Context;

import com.shoesshop.groupassignment.model.Order;
import com.shoesshop.groupassignment.model.OrderHistory;
import com.shoesshop.groupassignment.model.SuccessedOrder;
import com.shoesshop.groupassignment.model.UpdateCustomerResult;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.utils.CallBackData;

import java.util.List;

public interface ShoematicRepository {

    void loginByPhone(Context context, String fbAccessToken, CallBackData<Customer> callBackData);

    void getProductList(Context context, CallBackData<List<Product>> callBackData);

    void getOrderHistory(Context context, String accessToken, CallBackData<List<OrderHistory>> callBackData);

    void setOrder(Context context, Order order, CallBackData<SuccessedOrder> callBackData);

    void updateCustomer(Context context, Customer customer, CallBackData<Customer> callBackData);

}
