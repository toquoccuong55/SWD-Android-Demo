package com.shoesshop.groupassignment.room.manager;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.shoesshop.groupassignment.room.dao.ProductDao;
import com.shoesshop.groupassignment.room.dao.WishlistDao;
import com.shoesshop.groupassignment.room.database.ShoematicDatabase;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.entity.Wishlist;

import java.util.List;

public class WishListManager {
    private WishlistDao mWishlistDao;


    public WishListManager(Application application) {
        ShoematicDatabase libraryDatabase = ShoematicDatabase.getDatabase(application);
        this.mWishlistDao = libraryDatabase.mWishlistDao();
    }

    public void getWishList(OnDataCallBackWishList listener) {
        GetWishListAsync getWishListAsync = new GetWishListAsync(mWishlistDao, listener);
        getWishListAsync.execute();
    }

    public void addWishlist(Wishlist wishlist) {
        AddWishListAsync addWishListAsync = new AddWishListAsync(mWishlistDao);
        addWishListAsync.execute(wishlist);
    }

    public void deleteWishlist(Wishlist wishlist) {
        DeleteWishListAsync deleteWishListAsync = new DeleteWishListAsync(mWishlistDao);
        deleteWishListAsync.execute(wishlist);
    }

    public void deleteAllWishlist() {
        DeleteAllWishListAsync deleteAllWishListAsync = new DeleteAllWishListAsync(mWishlistDao);
        deleteAllWishListAsync.execute();
    }

    public void updateWishlist(Wishlist wishlist) {
        UpdateWishListAsync updateWishListAsync = new UpdateWishListAsync(mWishlistDao);
        updateWishListAsync.execute(wishlist);
    }

    private class UpdateWishListAsync extends AsyncTask<Wishlist, Void, Void> {
        private WishlistDao mWishlistDaoAsyn;

        public UpdateWishListAsync(WishlistDao mDaoAsync) {
            this.mWishlistDaoAsyn = mDaoAsync;
        }

        @Override
        protected Void doInBackground(Wishlist... wishlists) {
            try {
                mWishlistDaoAsyn.updateWishlist(wishlists);
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class AddWishListAsync extends AsyncTask<Wishlist, Void, Void> {
        private WishlistDao mWishlistDaoAsyn;

        public AddWishListAsync(WishlistDao mDaoAsync) {
            this.mWishlistDaoAsyn = mDaoAsync;
        }

        @Override
        protected Void doInBackground(Wishlist... wishlists) {
            try {
                mWishlistDaoAsyn.insertWishlist(wishlists);
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class DeleteWishListAsync extends AsyncTask<Wishlist, Void, Void> {
        private WishlistDao mWishlistDaoAsyn;

        public DeleteWishListAsync(WishlistDao mDaoAsync) {
            this.mWishlistDaoAsyn = mDaoAsync;
        }

        @Override
        protected Void doInBackground(Wishlist... wishlists) {
            try {
                mWishlistDaoAsyn.deleteWishlist(wishlists);
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class DeleteAllWishListAsync extends AsyncTask<Void, Void, Void> {
        private WishlistDao mWishlistDaoAsyn;

        public DeleteAllWishListAsync(WishlistDao mDaoAsync) {
            this.mWishlistDaoAsyn = mDaoAsync;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                mWishlistDaoAsyn.deleteAllWishList();
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class GetWishListAsync extends AsyncTask<Void, Void, Void> {
        private WishlistDao mWishlistDaoAsync;
        private List<Wishlist> mWishList;
        private OnDataCallBackWishList mListener;

        public GetWishListAsync(WishlistDao mWishlistDaoAsync, OnDataCallBackWishList mListener) {
            this.mWishlistDaoAsync = mWishlistDaoAsync;
            this.mListener = mListener;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                mWishList = mWishlistDaoAsync.getWishList();
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mListener.onDataSuccess(mWishList);
        }
    }

    public interface OnDataCallBackWishList {
        void onDataSuccess(List<Wishlist> wishlistList);

        void onDataFail();
    }
}
