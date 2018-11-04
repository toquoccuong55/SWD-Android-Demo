package com.shoesshop.groupassignment.view;

import com.shoesshop.groupassignment.room.entity.Customer;

public interface FirstLoginView {
    void addCustomer(boolean isSuccess);

    void insertCustomerToDBSuccess(Customer customer);

    void insertCustomerToDBFail(String message);

    void showCustomerInfo(Customer customer);
}
