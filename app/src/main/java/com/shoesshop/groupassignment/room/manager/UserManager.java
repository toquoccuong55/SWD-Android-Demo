package com.shoesshop.groupassignment.room.manager;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.shoesshop.groupassignment.room.dao.CustomerDao;
import com.shoesshop.groupassignment.room.database.ShoematicDatabase;
import com.shoesshop.groupassignment.room.entity.Customer;

public class UserManager {
    private CustomerDao mCustomerDao;

    public UserManager(Application application) {
        ShoematicDatabase libraryDatabase = ShoematicDatabase.getDatabase(application);
        this.mCustomerDao = libraryDatabase.mCustomerDao();
    }

    public void getCustomerInfo(OnDataCallBackCustomer listener) {
        GetCustomerAsync getCustomerAsync = new GetCustomerAsync(mCustomerDao, listener);
        getCustomerAsync.execute();
    }

    public void updateCustomer(Customer customer) {
        UpdateCustomerAsyn updateCustomerAsyn = new UpdateCustomerAsyn(mCustomerDao);
        updateCustomerAsyn.execute(customer);
    }

    public void deleteAllCustomer(OnDataCallBackCustomer listener) {
        DeleteAllCustomerAsync deleteAsync = new DeleteAllCustomerAsync(mCustomerDao, listener);
        deleteAsync.execute();
    }

    public void addCustomer(Customer customer, OnDataCallBackCustomer listener) {
        AddCustomerAsync addCustomerAsync = new AddCustomerAsync(mCustomerDao, listener);
        addCustomerAsync.execute(customer);
    }

    private class AddCustomerAsync extends AsyncTask<Customer, Void, Void> {
        private CustomerDao mDaoAsync;
        private OnDataCallBackCustomer mListener;

        public AddCustomerAsync(CustomerDao mDaoAsync, OnDataCallBackCustomer listener) {
            this.mDaoAsync = mDaoAsync;
            this.mListener = listener;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            try {
                mDaoAsync.insertCustomer(customers);
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mListener.onDataSuccess(null);
        }
    }

    private class DeleteAllCustomerAsync extends AsyncTask<Customer, Void, Void> {
        private CustomerDao mDaoAsync;
        private OnDataCallBackCustomer mListener;

        public DeleteAllCustomerAsync(CustomerDao mDaoAsync, OnDataCallBackCustomer mListener) {
            this.mDaoAsync = mDaoAsync;
            this.mListener = mListener;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            try {
                mDaoAsync.deleleAllCustomer();
            } catch (SQLiteConstraintException e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mListener.onDataSuccess(null);
        }
    }

    private class UpdateCustomerAsyn extends AsyncTask<Customer, Void, Void> {
        private CustomerDao mCustomerDaoAsync;

        public UpdateCustomerAsyn(CustomerDao mCustomerDaoAsync) {
            this.mCustomerDaoAsync = mCustomerDaoAsync;
        }


        @Override
        protected Void doInBackground(Customer... customers) {
            try {
                mCustomerDaoAsync.updateCustomer(customers);
            } catch (SQLiteConstraintException e) {

            }
            return null;
        }
    }

    private class GetCustomerAsync extends AsyncTask<Customer, Void, Void> {
        private CustomerDao mCustomerDaoAsync;
        private Customer mCustomer;
        private OnDataCallBackCustomer mListener;

        public GetCustomerAsync(CustomerDao mCustomerDaoAsync, OnDataCallBackCustomer mListener) {
            this.mCustomerDaoAsync = mCustomerDaoAsync;
            this.mListener = mListener;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            try {
                mCustomer = mCustomerDaoAsync.getCustomerInfo();
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mListener.onDataSuccess(mCustomer);
        }
    }

    public interface OnDataCallBackCustomer {
        void onDataSuccess(Customer customer);

        void onDataFail();
    }
}
