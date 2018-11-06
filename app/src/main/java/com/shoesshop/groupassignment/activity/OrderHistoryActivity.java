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
import com.shoesshop.groupassignment.adapter.OrderHistoryAdapter;
import com.shoesshop.groupassignment.model.OrderHistory;
import com.shoesshop.groupassignment.model.OrderHistoryDetail;
import com.shoesshop.groupassignment.presenter.OrderHistoryPresenter;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.utils.ConstantDataManager;
import com.shoesshop.groupassignment.utils.CurrencyManager;
import com.shoesshop.groupassignment.view.OrderHistoryView;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity implements View.OnClickListener, OrderHistoryView {
    private RecyclerView mRecyclerViewOrderHistory;
    private List<OrderHistory> mOrderHistoryList;
    private OrderHistoryAdapter mOrderHistoryAdapter;

    private LinearLayout mLnlBack, mLnlEmptyOrderHistory;
    private OrderHistoryPresenter mPresenter;
    private List<Product> mProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        initialView();
        intialData();
    }

    public static void intentToOrderHistoryActivitiy(Activity activity) {
        Intent intent = new Intent(activity, OrderHistoryActivity.class);
        activity.startActivity(intent);
    }

    private void initialView() {
        mRecyclerViewOrderHistory = findViewById(R.id.recycler_view_order_history);
        mRecyclerViewOrderHistory.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(OrderHistoryActivity.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerViewOrderHistory.setLayoutManager(layoutManager);

        mLnlBack = findViewById(R.id.linear_layout_back);
        mLnlBack.setOnClickListener(this);
        mLnlEmptyOrderHistory = findViewById(R.id.linear_layout_empty_order_history);

    }

    private void intialData() {
        mPresenter = new OrderHistoryPresenter(OrderHistoryActivity.this, OrderHistoryActivity.this, getApplication());
        mPresenter.getCustomer();

    }

    @Override
    public void showCustomer(Customer customer) {
        mPresenter.getOrderHistory(customer.getAccessToken());
    }

    @Override
    public void showOrderHistory(final List<OrderHistory> orderHistories) {

        mOrderHistoryList = orderHistories;
        if (mOrderHistoryList == null || mOrderHistoryList.isEmpty()) {
            mRecyclerViewOrderHistory.setVisibility(View.GONE);
            mLnlEmptyOrderHistory.setVisibility(View.VISIBLE);
        } else {
            mRecyclerViewOrderHistory.setVisibility(View.VISIBLE);
            mLnlEmptyOrderHistory.setVisibility(View.GONE);
            mPresenter.getProductList();
        }

    }

    @Override
    public void showProductList(List<Product> productList) {
        mProductList = productList;
        if (mProductList == null || mProductList.isEmpty()) {
            mRecyclerViewOrderHistory.setVisibility(View.GONE);
            mLnlEmptyOrderHistory.setVisibility(View.VISIBLE);
        } else {
            mRecyclerViewOrderHistory.setVisibility(View.VISIBLE);
            mLnlEmptyOrderHistory.setVisibility(View.GONE);

            for (OrderHistory orderHistory : mOrderHistoryList) {
                orderHistory.setOrderDetailImage(orderHistory.getDetailHistoryList().get(0).getOrderDetailImage());
                orderHistory.setOrderDetailName(orderHistory.getDetailHistoryList().get(0).getOrderDetailTitle());
                orderHistory.setUnitPriceQuantity(CurrencyManager.getPrice(
                        orderHistory.getDetailHistoryList().get(0).getUnitPrice(),
                        ConstantDataManager.CURRENCY) + " x " +
                        String.valueOf(orderHistory.getDetailHistoryList().get(0).getQuantity()));
            }
            mOrderHistoryAdapter = new OrderHistoryAdapter(mOrderHistoryList, OrderHistoryActivity.this);
            mRecyclerViewOrderHistory.setAdapter(mOrderHistoryAdapter);
            mOrderHistoryAdapter.setmOnItemClickListener(new OrderHistoryAdapter.OnItemClickListener() {
                @Override
                public void setOnItemClickListener(int position) {
                    OrderHistory orderHistory = mOrderHistoryList.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ConstantDataManager.BUNDLE_ORDER_HISTORY, orderHistory);
                    OrderHistoryDetailActivity.intentToOrderHistoryDetailActivitiy(OrderHistoryActivity.this, bundle);
                }
            });
        }

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
