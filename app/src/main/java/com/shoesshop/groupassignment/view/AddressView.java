package com.shoesshop.groupassignment.view;

import com.shoesshop.groupassignment.room.entity.Address;
import com.shoesshop.groupassignment.room.entity.Customer;

public interface AddressView {
    void showAddress(Address address);

    void showCustomer(Customer customer);
}
