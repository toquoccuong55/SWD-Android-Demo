package com.shoesshop.groupassignment.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.adapter.OrderHistoryDetailAdapter;
import com.shoesshop.groupassignment.model.OrderHistory;
import com.shoesshop.groupassignment.model.OrderHistoryDetail;
import com.shoesshop.groupassignment.utils.ConstantManager;

import java.util.List;

public class OrderHistoryDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecyclerViewOrderHistoryDetail;
    private List<OrderHistoryDetail> mOrderHistoryDetailList;
    private OrderHistoryDetailAdapter mOrderHistoryDetailAdapter;

    private LinearLayout mLnlBack, mLnlEmptyOrderHistoryDetail, mLnlHistoryDetail, mLnlPaymentDetail;
    private TextView mTxtReceiver, mTxtPhone, mTxtShippingAddress, mTxtNotes,
            mTxtTotalOrder, mTxtShippingFee, mTxtPromotion, mTxtTotalAmount;
    private OrderHistory mOrderHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_detail);
        getInitialIntent();
        initialView();
        initialData();
    }

    private void getInitialIntent() {
        Bundle bundle = getIntent().getBundleExtra(ConstantManager.INTENT_BUNDLE);
        if (bundle != null) {
            mOrderHistory = (OrderHistory) bundle.getSerializable(ConstantManager.BUNDLE_ORDER_HISTORY);
        }
    }

    private void initialView() {
        mRecyclerViewOrderHistoryDetail = findViewById(R.id.recycler_view_order_history_detail);
        mRecyclerViewOrderHistoryDetail.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(OrderHistoryDetailActivity.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerViewOrderHistoryDetail.setLayoutManager(layoutManager);

        mLnlBack = findViewById(R.id.linear_layout_back);
        mLnlBack.setOnClickListener(this);
        mLnlEmptyOrderHistoryDetail = findViewById(R.id.linear_layout_empty_order_history_detail);
        mLnlHistoryDetail = findViewById(R.id.linear_layout_order_history_detail);
        mLnlPaymentDetail = findViewById(R.id.linear_layout_payment_detail);

        mTxtReceiver = findViewById(R.id.text_view_receiver);
        mTxtPhone = findViewById(R.id.text_view_phone);
        mTxtShippingAddress = findViewById(R.id.text_view_shipping_address);
        mTxtNotes = findViewById(R.id.text_view_notes);
        mTxtTotalOrder = findViewById(R.id.text_view_total_order);
        mTxtShippingFee = findViewById(R.id.text_view_shipping_fee);
        mTxtPromotion = findViewById(R.id.text_view_promotion);
        mTxtTotalAmount = findViewById(R.id.text_view_total_amount);


    }

    private void initialData() {
        if (mOrderHistory != null) {
            mLnlHistoryDetail.setVisibility(View.VISIBLE);
            mLnlPaymentDetail.setVisibility(View.VISIBLE);
            mLnlEmptyOrderHistoryDetail.setVisibility(View.GONE);

            mOrderHistoryDetailList = mOrderHistory.getDetailHistoryList();
            mOrderHistoryDetailAdapter = new OrderHistoryDetailAdapter(mOrderHistoryDetailList, OrderHistoryDetailActivity.this);
            mRecyclerViewOrderHistoryDetail.setAdapter(mOrderHistoryDetailAdapter);

            //set other infor
        } else {
            mLnlHistoryDetail.setVisibility(View.GONE);
            mLnlPaymentDetail.setVisibility(View.GONE);
            mLnlEmptyOrderHistoryDetail.setVisibility(View.VISIBLE);

        }
    }


    public static void intentToOrderHistoryDetailActivitiy(Activity activity, Bundle bundle) {
        Intent intent = new Intent(activity, OrderHistoryDetailActivity.class);
        intent.putExtra(ConstantManager.INTENT_BUNDLE, bundle);
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
