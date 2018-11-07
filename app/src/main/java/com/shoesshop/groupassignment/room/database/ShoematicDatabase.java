package com.shoesshop.groupassignment.room.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.shoesshop.groupassignment.room.dao.CustomerDao;
import com.shoesshop.groupassignment.room.dao.ProductDao;
import com.shoesshop.groupassignment.room.dao.WishlistDao;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.entity.Wishlist;

import static com.shoesshop.groupassignment.room.database.ShoematicDatabase.DATABASE_VERSION;

@Database(entities = {Customer.class, Product.class, Wishlist.class},
        version = DATABASE_VERSION, exportSchema = false)
public abstract class ShoematicDatabase extends RoomDatabase {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "shoematic-database";
    private static ShoematicDatabase INSTANCE;

    public abstract CustomerDao mCustomerDao();

    public abstract ProductDao mProductDao();

    public abstract WishlistDao mWishlistDao();

    public static ShoematicDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ShoematicDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ShoematicDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }

        }
        return INSTANCE;
    }
}
