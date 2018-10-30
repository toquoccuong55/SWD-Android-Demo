package com.shoesshop.groupassignment.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.adapter.OrderHistoryDetailAdapter;
import com.shoesshop.groupassignment.model.OrderHistoryDetail;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecyclerViewOrderHistoryDetail;
    private List<OrderHistoryDetail> mOrderHistoryDetailList;
    private OrderHistoryDetailAdapter mOrderHistoryDetailAdapter;

    private LinearLayout mLnlBack;


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

        mLnlBack = findViewById(R.id.linear_layout_back);
        mLnlBack.setOnClickListener(this);
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


    public static void intentToOrderHistoryDetailActivitiy(Activity activity) {
        Intent intent = new Intent(activity, OrderHistoryDetailActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_back:
                finish();
                break;
        }
    }
}
