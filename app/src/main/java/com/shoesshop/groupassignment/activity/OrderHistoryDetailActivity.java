package com.shoesshop.groupassignment.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.adapter.OrderHistoryDetailAdapter;
import com.shoesshop.groupassignment.model.OrderHistoryDetail;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDetailActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewOrderHistoryDetail;
    private List<OrderHistoryDetail> mOrderHistoryDetailList;
    private OrderHistoryDetailAdapter mOrderHistoryDetailAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_detail);
        initialView();
        initialData();
    }


    private void initialView() {
        mRecyclerViewOrderHistoryDetail = findViewById(R.id.recycler_view_order_history_detail);
        mRecyclerViewOrderHistoryDetail.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(OrderHistoryDetailActivity.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerViewOrderHistoryDetail.setLayoutManager(layoutManager);
    }

    private void initialData() {
        mOrderHistoryDetailList = new ArrayList<>();
        mOrderHistoryDetailList.add(new OrderHistoryDetail(
                "123", "Nike Air Force Jester",
                "43", 350000, 1));
        mOrderHistoryDetailList.add(new OrderHistoryDetail(
                "123", "Nike Air Force Jester",
                "43", 350000, 1));
        mOrderHistoryDetailList.add(new OrderHistoryDetail(
                "123", "Nike Air Force Jester",
                "43", 350000, 1));


        mOrderHistoryDetailAdapter = new OrderHistoryDetailAdapter(mOrderHistoryDetailList, OrderHistoryDetailActivity.this);
        mRecyclerViewOrderHistoryDetail.setAdapter(mOrderHistoryDetailAdapter);
    }
}
