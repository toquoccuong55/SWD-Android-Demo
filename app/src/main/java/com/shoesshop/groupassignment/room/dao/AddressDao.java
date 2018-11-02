package com.shoesshop.groupassignment.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.shoesshop.groupassignment.room.entity.Address;

import java.util.List;

@Dao
public interface AddressDao {

    @Insert
    void insertAddress(Address... addresses);

    @Delete
    void deleteAddress(Address... addresses);

    @Update
    void updateAddress(Address... addresses);

    @Query("SELECT * FROM address")
    Address getAddress();

}
