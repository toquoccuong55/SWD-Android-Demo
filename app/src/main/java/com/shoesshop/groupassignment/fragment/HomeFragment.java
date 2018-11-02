package com.shoesshop.groupassignment.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.activity.ProductDetailActivity;
import com.shoesshop.groupassignment.adapter.HomeAdapter;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.presenter.HomeFragPresenter;
import com.shoesshop.groupassignment.utils.ConstantDataManager;
import com.shoesshop.groupassignment.utils.GridSpacingItemDecoration;
import com.shoesshop.groupassignment.view.HomeFragView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeFragView {
    private RecyclerView mRecyclerViewProduct;
    private HomeAdapter mHomeAdapter;
    private List<Product> mProductList;
    private HomeFragPresenter mHomeFragPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialView();

    }

    @Override
    public void onStart() {
        super.onStart();
        initialData();
    }

    private void initialView() {
        mRecyclerViewProduct = getView().findViewById(R.id.recycler_view_products);
        mRecyclerViewProduct.setHasFixedSize(true);
        int numberOfColumn = calculateNumberOfColumns(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), numberOfColumn);
        mRecyclerViewProduct.setLayoutManager(layoutManager);
        int spacing = getResources().getDimensionPixelSize(R.dimen.dp7);
        mRecyclerViewProduct.addItemDecoration(new GridSpacingItemDecoration(numberOfColumn, spacing, true));

    }

    private int calculateNumberOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }

    private void initialData() {
        mHomeFragPresenter = new HomeFragPresenter(getActivity(), HomeFragment.this);
        mHomeFragPresenter.getProductList();
    }

    @Override
    public void showProductList(List<Product> productList) {
        mProductList = productList;
        mHomeAdapter = new HomeAdapter(mProductList, getContext());
        mRecyclerViewProduct.setAdapter(mHomeAdapter);
        mHomeAdapter.setmOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(int position) {
                Product product = mProductList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantDataManager.BUNDLE_PRODUCT, product);
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra(ConstantDataManager.INTENT_BUNDLE, bundle);
                startActivity(intent);
            }
        });
    }
}
