package com.shoesshop.groupassignment.view;

import com.shoesshop.groupassignment.room.entity.Address;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.entity.ProductVariant;

import java.util.List;

public interface CartFragView {
    void showOrderItemList(List<Product> productList);

    void showCustomer(Customer customer);

    void showAddress(Address address);
}
