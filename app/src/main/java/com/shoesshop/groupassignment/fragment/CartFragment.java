package com.shoesshop.groupassignment.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.adapter.OrderDetailAdapter;
import com.shoesshop.groupassignment.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    private RecyclerView mRecyclerViewOrderDetail;
    private OrderDetailAdapter mOrderDetailAdapter;
    private List<OrderDetail> mOrderDetailList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialView();
        initialData();
    }

    private void initialView() {
        mRecyclerViewOrderDetail = getView().findViewById(R.id.recycler_view_order_detail);
        mRecyclerViewOrderDetail.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerViewOrderDetail.setLayoutManager(layoutManager);

    }

    private void initialData() {
        mOrderDetailList = new ArrayList<>();
        mOrderDetailList.add(new OrderDetail("Nike Air Force Jester", "", "39", 560000, 1));
        mOrderDetailList.add(new OrderDetail("Nike Air Force Jester", "", "39", 560000, 1));
        mOrderDetailList.add(new OrderDetail("Nike Air Force Jester", "", "39", 560000, 1));

        mOrderDetailAdapter = new OrderDetailAdapter(mOrderDetailList, getContext());
        mRecyclerViewOrderDetail.setAdapter(mOrderDetailAdapter);
        mOrderDetailAdapter.setmOnItemClickListener(new OrderDetailAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(int position) {

            }
        });
    }
}
