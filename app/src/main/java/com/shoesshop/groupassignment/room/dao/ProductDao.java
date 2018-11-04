package com.shoesshop.groupassignment.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.shoesshop.groupassignment.room.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insertProduct(Product... product);

    @Delete
    void deleteProduct(Product... product);

    @Update
    void updateProduct(Product... product);

    @Query("SELECT * FROM product")
    List<Product> getProductList();

    @Query("DELETE FROM product")
    void deleteAllProduct();
}
