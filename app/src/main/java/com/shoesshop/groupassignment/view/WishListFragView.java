package com.shoesshop.groupassignment.view;

import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.entity.Wishlist;

import java.util.List;

public interface WishListFragView {
    void showWishList(List<Wishlist> wishlists);
}
