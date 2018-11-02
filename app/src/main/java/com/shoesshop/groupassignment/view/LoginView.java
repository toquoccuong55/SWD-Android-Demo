package com.shoesshop.groupassignment.view;

import com.shoesshop.groupassignment.room.entity.Customer;

public interface LoginView {
    void addCustomerSuccess(Customer customer);

    void loginByPhoneSuccess(Customer customer);

    void loginByPhoneFailed(String message);

    void loginByFacebookSuccess(Customer customer);

    void loginByFacebookFailed(String message);
}
