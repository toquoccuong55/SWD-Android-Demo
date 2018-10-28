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
import com.shoesshop.groupassignment.adapter.WishlistAdapter;
import com.shoesshop.groupassignment.model.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class WishlistFragment extends Fragment {
    private RecyclerView mRecyclerViewWishlist;
    private WishlistAdapter mWishlistAdapter;
    private List<Wishlist> mWishlist;


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
        initialData();
    }

    private void initialView() {
        mRecyclerViewWishlist = getView().findViewById(R.id.recycler_view_wishlist);
        mRecyclerViewWishlist.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerViewWishlist.setLayoutManager(layoutManager);
        mRecyclerViewWishlist.setNestedScrollingEnabled(false);
    }

    private void initialData() {
        mWishlist = new ArrayList<>();
        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));
        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));
        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));
        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));
        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));
        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));
        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));
        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));
        mWishlist.add(new Wishlist("", "Nike Air Force Jester", 560000));

        mWishlistAdapter = new WishlistAdapter(mWishlist, getContext());
        mRecyclerViewWishlist.setAdapter(mWishlistAdapter);
    }

}
