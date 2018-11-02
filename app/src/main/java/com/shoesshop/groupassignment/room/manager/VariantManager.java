package com.shoesshop.groupassignment.room.manager;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.shoesshop.groupassignment.room.dao.VariantDao;
import com.shoesshop.groupassignment.room.database.ShoematicDatabase;
import com.shoesshop.groupassignment.room.entity.ProductVariant;

import java.util.List;

public class VariantManager {
    private VariantDao mVariantDao;

    public VariantManager(Application application) {
        ShoematicDatabase libraryDatabase = ShoematicDatabase.getDatabase(application);
        this.mVariantDao = libraryDatabase.mVariantDao();
    }

    public void getVariant(OnDataCallBackVariant onDataCallBackVariant){
        GetVariantAsyn getVariantAsyn = new GetVariantAsyn(mVariantDao,onDataCallBackVariant);
        getVariantAsyn.execute();
    }

    public void deleteVariant(ProductVariant variant){
        DeleteVariantAsyn deleteVariantAsyn = new DeleteVariantAsyn(mVariantDao);
        deleteVariantAsyn.execute(variant);
    }

    public void updateVariant(ProductVariant variant){
        UpdateVariantAsyn updateVariantAsyn = new UpdateVariantAsyn(mVariantDao);
        updateVariantAsyn.execute(variant);
    }

    public void addVariant(ProductVariant variant){
        AddVariantAsyn addVariantAsyn = new AddVariantAsyn(mVariantDao);
        addVariantAsyn.execute(variant);
    }

    private class GetVariantAsyn extends AsyncTask<Void, Void, Void> {
        private VariantDao mVariantDaoAsyn;
        private List<ProductVariant> variantList;
        private OnDataCallBackVariant mListener;

        public GetVariantAsyn(VariantDao mVariantDaoAsyn, OnDataCallBackVariant mListener) {
            this.mVariantDaoAsyn = mVariantDaoAsyn;
            this.mListener = mListener;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                variantList = mVariantDaoAsyn.getVariant();
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (variantList != null) {
                mListener.onDataSuccess(variantList);
            } else {
                mListener.onDataSuccess(null);
            }
        }
    }

    private class DeleteVariantAsyn extends AsyncTask<ProductVariant, Void, Void> {
        private VariantDao mVariantDaoAsyn;

        public DeleteVariantAsyn(VariantDao mVariantDaoAsyn) {
            this.mVariantDaoAsyn = mVariantDaoAsyn;
        }

        @Override
        protected Void doInBackground(ProductVariant... variants) {
            try {
                mVariantDaoAsyn.deleteVariant(variants);
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class UpdateVariantAsyn extends AsyncTask<ProductVariant, Void, Void> {
        private VariantDao mVariantDaoAsyn;

        public UpdateVariantAsyn(VariantDao mVariantDaoAsyn) {
            this.mVariantDaoAsyn = mVariantDaoAsyn;
        }

        @Override
        protected Void doInBackground(ProductVariant... variants) {
            try {
                mVariantDaoAsyn.updateVariant(variants);
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class AddVariantAsyn extends AsyncTask<ProductVariant, Void, Void> {
        private VariantDao mVariantDaoAsyn;

        public AddVariantAsyn(VariantDao mVariantDaoAsyn) {
            this.mVariantDaoAsyn = mVariantDaoAsyn;
        }

        @Override
        protected Void doInBackground(ProductVariant... variants) {
            try {
                mVariantDaoAsyn.addVariant(variants);
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    public interface OnDataCallBackVariant {
        void onDataSuccess(List<ProductVariant> variantList);

        void onDataFail();
    }
}
