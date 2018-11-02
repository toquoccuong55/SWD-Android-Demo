package com.shoesshop.groupassignment.presenter;

import android.content.Context;

import com.shoesshop.groupassignment.ShoematicRepository.ShoematicRepository;
import com.shoesshop.groupassignment.ShoematicRepository.ShoematicRepositoryImpl;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.utils.CallBackData;
import com.shoesshop.groupassignment.view.HomeFragView;

import java.util.List;

public class HomeFragPresenter {
    private Context mContext;
    private ShoematicRepository mShoematicRepository;
    private HomeFragView mHomeFragView;

    public HomeFragPresenter(Context mContext, HomeFragView mHomeFragView) {
        this.mContext = mContext;
        this.mHomeFragView = mHomeFragView;
        mShoematicRepository = new ShoematicRepositoryImpl();
    }

    public void getProductList() {
        mShoematicRepository.getProductList(mContext, new CallBackData<List<Product>>() {
            @Override
            public void onSuccess(List<Product> products) {
                mHomeFragView.showProductList(products);
            }

            @Override
            public void onFail(String message) {
            }
        });
    }
}
