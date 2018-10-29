package com.shoesshop.groupassignment.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.adapter.OrderHistoryAdapter;
import com.shoesshop.groupassignment.model.OrderHistory;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewOrderHistory;
    private List<OrderHistory> mOrderHistoryList;
    private OrderHistoryAdapter mOrderHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        initialView();
        intialData();
    }

    private void initialView() {
        mRecyclerViewOrderHistory = findViewById(R.id.recycler_view_order_history);
        mRecyclerViewOrderHistory.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(OrderHistoryActivity.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerViewOrderHistory.setLayoutManager(layoutManager);


    }

    private void intialData() {
        mOrderHistoryList = new ArrayList<>();
        mOrderHistoryList.add(new OrderHistory("12321412", "12h11 ngày 12/11/2018",
                350000, "123", "Bath bomb Glo AROMA",
                "35.000đ x 10", "Đã giao hàng"));
        mOrderHistoryList.add(new OrderHistory("12321412", "12h11 ngày 12/11/2018",
                350000, "123", "Bath bomb Glo AROMA",
                "35.000đ x 10", "Đã giao hàng"));
        mOrderHistoryList.add(new OrderHistory("12321412", "12h11 ngày 12/11/2018",
                350000, "123", "Bath bomb Glo AROMA",
                "35.000đ x 10", "Đã giao hàng"));


        mOrderHistoryAdapter = new OrderHistoryAdapter(mOrderHistoryList, OrderHistoryActivity.this);
        mRecyclerViewOrderHistory.setAdapter(mOrderHistoryAdapter);
    }
}
