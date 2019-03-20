package com.shoesshop.groupassignment.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.activity.ProductDetailActivity;
import com.shoesshop.groupassignment.adapter.HomeAdapter;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.presenter.HomeFragPresenter;
import com.shoesshop.groupassignment.utils.ConstantManager;
import com.shoesshop.groupassignment.utils.GridSpacingItemDecoration;
import com.shoesshop.groupassignment.view.HomeFragView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeFragView, View.OnClickListener {
    private RecyclerView mRecyclerViewProduct;
    private HomeAdapter mHomeAdapter;
    private List<Product> mProductList;
    private HomeFragPresenter mHomeFragPresenter;
    private LinearLayout mLmlSearch, mlmlSearchTab;
    private EditText mEdtSearch;
    private TextView mTxtCancelSearch;
    private FrameLayout mFmlLogo;

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

        mLmlSearch = getView().findViewById(R.id.linear_layout_search);
        mLmlSearch.setOnClickListener(this);
        mEdtSearch = getView().findViewById(R.id.edit_text_search);
        mTxtCancelSearch = getView().findViewById(R.id.text_view_cancel_search);
        mTxtCancelSearch.setOnClickListener(this);
        mlmlSearchTab = getView().findViewById(R.id.linear_layout_open_search_tab);
        mFmlLogo = getView().findViewById(R.id.frame_layout_logo);

        addTextWatcher();
    }

    private int calculateNumberOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }

    private void initialData() {
        mHomeFragPresenter = new HomeFragPresenter(getActivity(), HomeFragment.this);
        if (mProductList == null) {
            mHomeFragPresenter.getProductList();
        } else {
            showProductList(mProductList);
        }
    }

    @Override
    public void showProductList(List<Product> productList) {
        mProductList = productList;
        if (mProductList == null) {
            mProductList = new ArrayList<>();
        }

        mHomeAdapter = new HomeAdapter(mProductList, getContext());
        mRecyclerViewProduct.setAdapter(mHomeAdapter);
        mHomeAdapter.setmOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(int position) {
                Product product = mProductList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantManager.BUNDLE_PRODUCT, product);
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra(ConstantManager.INTENT_BUNDLE, bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_search:
                mFmlLogo.setVisibility(View.GONE);
                mlmlSearchTab.setVisibility(View.VISIBLE);
                break;
            case R.id.text_view_cancel_search:
                mlmlSearchTab.setVisibility(View.GONE);
                mFmlLogo.setVisibility(View.VISIBLE);
                mHomeFragPresenter.getProductList();
                break;
        }
    }

    private void addTextWatcher() {
        mEdtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String searchText = mEdtSearch.getText().toString().trim().toLowerCase();

                if (!searchText.isEmpty()) {
                    List<Product> productList = new ArrayList<>();
                    for (Product product : mProductList) {
                        if (product.getName().toLowerCase().contains(searchText)) {
                            productList.add(product);
                        }
                    }
                    searchProduct(productList);
                } else {
                    mHomeFragPresenter.getProductList();
                }
            }
        });
    }

    private void searchProduct(List<Product> productList) {
        mHomeAdapter = new HomeAdapter(productList, getContext());
        mRecyclerViewProduct.setAdapter(mHomeAdapter);
        mHomeAdapter.setmOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(int position) {
                Product product = mProductList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantManager.BUNDLE_PRODUCT, product);
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra(ConstantManager.INTENT_BUNDLE, bundle);
                startActivity(intent);
            }
        });
    }
}
