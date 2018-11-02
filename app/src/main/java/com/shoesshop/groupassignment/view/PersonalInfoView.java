package com.shoesshop.groupassignment.view;

import android.app.Application;
import android.content.Context;

import com.shoesshop.groupassignment.activity.PersonalInfoActivity;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.manager.UserManager;

public interface PersonalInfoView {
    void showCustomerInfo(Customer customer);

}
