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
import android.widget.TextView;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.activity.EditOrderDetailActivity;
import com.shoesshop.groupassignment.activity.NoteActivity;
import com.shoesshop.groupassignment.activity.OrderSuccessActivity;
import com.shoesshop.groupassignment.activity.PaymentActivity;
import com.shoesshop.groupassignment.activity.ShippingAddressActivity;
import com.shoesshop.groupassignment.adapter.OrderDetailAdapter;
import com.shoesshop.groupassignment.model.OrderDetail;
import com.shoesshop.groupassignment.presenter.CartFragPresenter;
import com.shoesshop.groupassignment.room.entity.Address;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.entity.ProductVariant;
import com.shoesshop.groupassignment.view.CartFragView;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements View.OnClickListener, CartFragView {
    private RecyclerView mRcvOrderDetail;
    private OrderDetailAdapter mOrderDetailAdapter;
    private List<OrderDetail> mOrderDetailList;

    private LinearLayout mLnlDeliveryInfo, mLnlNote, mLnlPayment, mOrderInfo, mPaymentInfo,
            mLnlEmptyCart, mLnlReceiver, mLnlAddress;
    private TextView mTxtReceiver, mTxtAddress;

    private List<ProductVariant> mShoppingBag;
    private Customer mCustomer;
    private Address mAddress;
    private CartFragPresenter mPresenter;

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

    }

    @Override
    public void onStart() {
        super.onStart();
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
        mTxtReceiver = getView().findViewById(R.id.text_view_receiver);
        mLnlReceiver = getView().findViewById(R.id.linear_layout_receiver);
        mTxtAddress = getView().findViewById(R.id.text_view_address);
        mLnlAddress = getView().findViewById(R.id.linear_layout_address);
        mLnlAddress.setOnClickListener(this);


        mOrderInfo = getView().findViewById(R.id.linear_layout_order_infor);
        mPaymentInfo = getView().findViewById(R.id.linear_layout_payment_infor);
        mLnlEmptyCart = getView().findViewById(R.id.linear_layout_empty_cart);

        mPresenter = new CartFragPresenter(getActivity(), CartFragment.this, getActivity().getApplication());

    }

    private void initialData() {
        mPresenter.getOrderItemList();
        mPresenter.getCustomer();
        mPresenter.getAddress();
    }

    @Override
    public void showOrderItemList(List<ProductVariant> variantList) {
        mShoppingBag = variantList;
        if (mShoppingBag == null || mShoppingBag.isEmpty()) {
            mOrderInfo.setVisibility(View.GONE);
            mPaymentInfo.setVisibility(View.GONE);
            mLnlEmptyCart.setVisibility(View.VISIBLE);
        } else {
            mOrderInfo.setVisibility(View.VISIBLE);
            mPaymentInfo.setVisibility(View.VISIBLE);
            mLnlEmptyCart.setVisibility(View.GONE);

            mOrderDetailList = new ArrayList<>();
            for (ProductVariant variant : mShoppingBag) {
                OrderDetail orderDetail = new OrderDetail(
                        variant.getName(),
                        variant.getPicURLList().get(0),
                        variant.getSize().getName(),
                        variant.getUnitPrice(),
                        variant.getQuantity());
                mOrderDetailList.add(orderDetail);
            }
            mOrderDetailAdapter = new OrderDetailAdapter(mOrderDetailList, getContext());
            mRcvOrderDetail.setAdapter(mOrderDetailAdapter);
            mOrderDetailAdapter.setmOnItemClickListener(new OrderDetailAdapter.OnItemClickListener() {
                @Override
                public void setOnItemClickListener(int position) {
                    EditOrderDetailActivity.intentToEditOrderDetailActivitiy(getActivity());
                }
            });
        }
    }

    @Override
    public void showCustomer(Customer customer) {
        mCustomer = customer;
        if (mCustomer == null) {
            mLnlReceiver.setVisibility(View.GONE);
        } else {
            mLnlReceiver.setVisibility(View.VISIBLE);
            String receiver = mCustomer.getFullName() + " - " + mCustomer.getPhone();
            mTxtReceiver.setText(receiver);
        }
    }

    @Override
    public void showAddress(Address address) {
        mAddress = address;
        if (mAddress == null) {
            mTxtAddress.setText("Vui lòng nhập địa chỉ tại đây!");
        } else {
            String addressString = mAddress.getType() + ": " + mAddress.getAddress();
            mTxtAddress.setText(addressString);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_delivery_info:
                ShippingAddressActivity.intentToShippingAddressActivitiy(getActivity());
                break;
            case R.id.linear_layout_address:
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
