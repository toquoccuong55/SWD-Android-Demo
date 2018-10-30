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
import com.shoesshop.groupassignment.activity.EditOrderDetailActivity;
import com.shoesshop.groupassignment.activity.NoteActivity;
import com.shoesshop.groupassignment.activity.OrderSuccessActivity;
import com.shoesshop.groupassignment.activity.PaymentActivity;
import com.shoesshop.groupassignment.activity.ShippingAddressActivity;
import com.shoesshop.groupassignment.adapter.OrderDetailAdapter;
import com.shoesshop.groupassignment.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements View.OnClickListener {
    private RecyclerView mRcvOrderDetail;
    private OrderDetailAdapter mOrderDetailAdapter;
    private List<OrderDetail> mOrderDetailList;
    private LinearLayout mLnlDeliveryInfo, mLnlNote, mLnlPayment;

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
        mRcvOrderDetail = getView().findViewById(R.id.recycler_view_order_detail);
        mRcvOrderDetail.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRcvOrderDetail.setLayoutManager(layoutManager);

        mLnlDeliveryInfo = getView().findViewById(R.id.linear_layout_delivery_info);
        mLnlDeliveryInfo.setOnClickListener(this);
        mLnlNote = getView().findViewById(R.id.linear_layout_note);
        mLnlNote.setOnClickListener(this);
        mLnlPayment = getView().findViewById(R.id.linear_layout_payment);
        mLnlPayment.setOnClickListener(this);
    }

    private void initialData() {
        mOrderDetailList = new ArrayList<>();
        mOrderDetailList.add(new OrderDetail("Nike Air Force Jester", "", "39", 560000, 1));
        mOrderDetailList.add(new OrderDetail("Nike Air Force Jester", "", "39", 560000, 1));
        mOrderDetailList.add(new OrderDetail("Nike Air Force Jester", "", "39", 560000, 1));

        mOrderDetailAdapter = new OrderDetailAdapter(mOrderDetailList, getContext());
        mRcvOrderDetail.setAdapter(mOrderDetailAdapter);
        mOrderDetailAdapter.setmOnItemClickListener(new OrderDetailAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(int position) {
                EditOrderDetailActivity.intentToEditOrderDetailActivitiy(getActivity());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_delivery_info:
                ShippingAddressActivity.intentToShippingAddressActivitiy(getActivity());
                break;
            case R.id.linear_layout_note:
                NoteActivity.intentToNoteActivitiy(getActivity());
                break;
            case R.id.linear_layout_payment:
                PaymentActivity.intentToPaymentActivitiy(getActivity());
                break;
            case R.id.button_set_order:
                OrderSuccessActivity.intentToOrderSuccessActivitiy(getActivity());
                break;


        }
    }
}
