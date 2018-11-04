package com.shoesshop.groupassignment.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.activity.ProductDetailActivity;
import com.shoesshop.groupassignment.adapter.WishlistAdapter;
import com.shoesshop.groupassignment.room.entity.Wishlist;
import com.shoesshop.groupassignment.presenter.WishListFragPresenter;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.view.WishListFragView;

import java.util.ArrayList;
import java.util.List;

public class WishlistFragment extends Fragment implements WishListFragView {
    private RecyclerView mRecyclerViewWishlist;
    private WishlistAdapter mWishlistAdapter;
    private List<Wishlist> mWishlist;

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

        mWishListFragPresenter = new WishListFragPresenter(getActivity(), WishlistFragment.this, getActivity().getApplication());
    }

    private void initialData() {
        mWishListFragPresenter.getWishList();

//        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));
//        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));
//        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));
//        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));
//        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));
//        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));
//        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));
//        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));
//        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));

    }

    @Override
    public void showWishList(List<Product> productList) {
//        mWishlist = new ArrayList<>();
//
//        for (Product product : productList) {
//            mWishlist.add(new Wishlist(product.getImage(), product.getName(), product.getUnitPrice()));
//        }
//
//        mWishlistAdapter = new WishlistAdapter(mWishlist, getContext());
//        mRecyclerViewWishlist.setAdapter(mWishlistAdapter);
//        mWishlistAdapter.setmOnItemClickListener(new WishlistAdapter.OnItemClickListener() {
//            @Override
//            public void setOnItemClickListener(int position) {
//                ProductDetailActivity.intentToProductDetailActivitiy(getActivity());
//            }
//        });
    }
}
