package com.shoesshop.groupassignment.view;

import com.shoesshop.groupassignment.model.OrderHistory;
import com.shoesshop.groupassignment.room.entity.Customer;

import java.util.List;

public interface OrderHistoryView {
    void showCustomer(Customer customer);

    void showOrderHistory(List<OrderHistory> orderHistories);
}
