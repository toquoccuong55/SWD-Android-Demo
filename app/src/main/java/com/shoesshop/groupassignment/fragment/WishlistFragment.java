package com.shoesshop.groupassignment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.activity.ProductDetailActivity;
import com.shoesshop.groupassignment.adapter.WishlistAdapter;
import com.shoesshop.groupassignment.room.entity.Wishlist;
import com.shoesshop.groupassignment.presenter.WishListFragPresenter;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.utils.ConstantDataManager;
import com.shoesshop.groupassignment.view.WishListFragView;

import java.util.ArrayList;
import java.util.List;

public class WishlistFragment extends Fragment implements WishListFragView {
    private RecyclerView mRecyclerViewWishlist;
    private WishlistAdapter mWishlistAdapter;
    private List<Wishlist> mWishlist;

    private LinearLayout mLnlWishlist;
    private NestedScrollView mNsvWishlist;

    private WishListFragPresenter mWishListFragPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wishlist, container, false);
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
        mRecyclerViewWishlist = getView().findViewById(R.id.recycler_view_wishlist);
        mRecyclerViewWishlist.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerViewWishlist.setLayoutManager(layoutManager);
        mRecyclerViewWishlist.setNestedScrollingEnabled(false);

        mLnlWishlist = getView().findViewById(R.id.linear_layout_empty_cart);
        mNsvWishlist = getView().findViewById(R.id.nested_scroll_view_wishlist);

        mWishListFragPresenter = new WishListFragPresenter(WishlistFragment.this, getActivity().getApplication());
    }

    private void initialData() {
        mWishListFragPresenter.getWishList();
    }

    @Override
    public void showWishList(List<Wishlist> wishlists) {
        mWishlist = wishlists;
        if(mWishlist == null || mWishlist.isEmpty()){
            mLnlWishlist.setVisibility(View.VISIBLE);
            mNsvWishlist.setVisibility(View.GONE);
        }else{
            mLnlWishlist.setVisibility(View.GONE);
            mNsvWishlist.setVisibility(View.VISIBLE);
            mWishlistAdapter = new WishlistAdapter(mWishlist, getContext());
            mRecyclerViewWishlist.setAdapter(mWishlistAdapter);
            mWishlistAdapter.setmOnItemClickListener(new WishlistAdapter.OnItemClickListener() {
                @Override
                public void setOnItemClickListener(int position) {
                    Wishlist wishlist = mWishlist.get(position);
                    Product product = convertWishListToProduct(wishlist);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ConstantDataManager.BUNDLE_PRODUCT, product);
                    Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                    intent.putExtra(ConstantDataManager.INTENT_BUNDLE, bundle);
                    startActivity(intent);
                }
            });
        }
    }

    private Product convertWishListToProduct(Wishlist wishlist) {
        Product product = new Product();

        product.setId(wishlist.getId());
        product.setName(wishlist.getName());
        product.setUnitPrice(wishlist.getUnitPrice());
        product.setImage(wishlist.getImage());
        product.setDescription(wishlist.getDescription());
        product.setFavorite(wishlist.isFavorite());
        product.setProductVariantList(wishlist.getProductVariantList());

        return product;
    }

}
