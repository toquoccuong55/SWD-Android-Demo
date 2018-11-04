package com.shoesshop.groupassignment.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.entity.Wishlist;

import java.util.List;

@Dao
public interface WishlistDao {

    @Insert
    void insertWishlist(Wishlist... wishlists);

    @Delete
    void deleteWishlist(Wishlist... wishlists);

    @Update
    void updateWishlist(Wishlist... wishlists);

    @Query("SELECT * FROM wishlist")
    List<Wishlist> getWishList();

    @Query("DELETE FROM wishlist")
    void deleteAllWishList();
}
