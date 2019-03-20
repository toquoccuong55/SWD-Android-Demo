package com.shoesshop.groupassignment.view;

import com.shoesshop.groupassignment.model.SuccessedOrder;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.entity.Product;

import java.util.List;

public interface CartFragView {
    void showOrderItemList(List<Product> productList);

    void showCustomer(Customer customer);

    void setOrderSuccess(SuccessedOrder successedOrder);

    void setOrderFailed(String message);

}
