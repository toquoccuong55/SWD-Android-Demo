package com.shoesshop.groupassignment.room.manager;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.shoesshop.groupassignment.room.dao.AddressDao;
import com.shoesshop.groupassignment.room.database.ShoematicDatabase;
import com.shoesshop.groupassignment.room.entity.Address;
import com.shoesshop.groupassignment.room.entity.Product;

import java.util.List;

public class AddressManager {
    private AddressDao mAddressDao;

    public AddressManager(Application application) {
        ShoematicDatabase libraryDatabase = ShoematicDatabase.getDatabase(application);
        this.mAddressDao = libraryDatabase.mAddressDao();
    }

    public void addAddress(Address address) {
        AddAddressAsyn addAddressAsyn = new AddAddressAsyn(mAddressDao);
        addAddressAsyn.execute(address);
    }

    public void deleteAddress(Address address) {
        DeleteAddressAsyn deleteAddressAsyn = new DeleteAddressAsyn(mAddressDao);
        deleteAddressAsyn.execute(address);
    }

    public void updateAddress(Address address) {
        UpdateAddressAsyn updateAddressAsyn = new UpdateAddressAsyn(mAddressDao);
        updateAddressAsyn.execute(address);
    }

    public void getAddress(OnDataCallBackAddress onDataCallBackAddress) {
        GetAddressAsyn getAddressAsyn = new GetAddressAsyn(mAddressDao, onDataCallBackAddress);
        getAddressAsyn.execute();
    }

    private class DeleteAddressAsyn extends AsyncTask<Address, Void, Void> {
        private AddressDao mAddressDaoAsyn;

        public DeleteAddressAsyn(AddressDao mAddressDaoAsyn) {
            this.mAddressDaoAsyn = mAddressDaoAsyn;
        }

        @Override
        protected Void doInBackground(Address... addresses) {
            try {
                mAddressDaoAsyn.deleteAddress(addresses);
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class AddAddressAsyn extends AsyncTask<Address, Void, Void> {
        private AddressDao mAddressDaoAsyn;

        public AddAddressAsyn(AddressDao mAddressDaoAsyn) {
            this.mAddressDaoAsyn = mAddressDaoAsyn;
        }

        @Override
        protected Void doInBackground(Address... addresses) {
            try {
                mAddressDaoAsyn.insertAddress(addresses);
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class UpdateAddressAsyn extends AsyncTask<Address, Void, Void> {
        private AddressDao mAddressDaoAsyn;

        public UpdateAddressAsyn(AddressDao mAddressDaoAsyn) {
            this.mAddressDaoAsyn = mAddressDaoAsyn;
        }

        @Override
        protected Void doInBackground(Address... addresses) {
            try {
                mAddressDaoAsyn.updateAddress(addresses);
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class GetAddressAsyn extends AsyncTask<Address, Void, Void> {
        private AddressDao mAddressDaoAsyn;
        private Address mAddress;
        private OnDataCallBackAddress mListener;

        public GetAddressAsyn(AddressDao mAddressDaoAsyn, OnDataCallBackAddress mListener) {
            this.mAddressDaoAsyn = mAddressDaoAsyn;
            this.mListener = mListener;
        }

        @Override
        protected Void doInBackground(Address... addresses) {
            try {
                mAddress = mAddressDaoAsyn.getAddress();
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (mAddress != null) {
                mListener.onDataSuccess(mAddress);
            } else {
                mListener.onDataSuccess(null);
            }

        }
    }

    public interface OnDataCallBackAddress {
        void onDataSuccess(Address address);

        void onDataFail();
    }
}
