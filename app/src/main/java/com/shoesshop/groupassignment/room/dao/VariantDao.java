package com.shoesshop.groupassignment.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.shoesshop.groupassignment.room.entity.ProductVariant;

import java.util.List;

@Dao
public interface VariantDao {

    @Insert
    void addVariant(ProductVariant... variants);

    @Update
    void updateVariant(ProductVariant... variants);

    @Delete
    void deleteVariant(ProductVariant... variants);

    @Query("SELECT * FROM variant")
    List<ProductVariant> getVariant();

}
