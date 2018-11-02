package com.shoesshop.groupassignment.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.shoesshop.groupassignment.room.entity.Customer;

@Dao
public interface CustomerDao {
    @Insert
    void insertCustomer(Customer... customers);

    @Query("DELETE FROM customers")
    void deleleAllCustomer();

    @Update
    void updateCustomer(Customer... customers);

    @Query("select * from customers")
    Customer getCustomerInfo();
}
