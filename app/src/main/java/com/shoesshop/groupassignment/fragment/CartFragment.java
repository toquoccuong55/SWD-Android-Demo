package com.shoesshop.groupassignment.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.entity.ProductVariant;
import com.shoesshop.groupassignment.utils.ConstantDataManager;
import com.shoesshop.groupassignment.utils.CurrencyManager;
import com.shoesshop.groupassignment.utils.PreferenceUtils;
import com.shoesshop.groupassignment.view.CartFragView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements View.OnClickListener, CartFragView {
    private RecyclerView mRcvOrderDetail;
    private OrderDetailAdapter mOrderDetailAdapter;
    private List<OrderDetail> mOrderDetailList;

    private LinearLayout mLnlDeliveryInfo, mLnlNote, mLnlPayment, mOrderInfo, mPaymentInfo,
            mLnlEmptyCart, mLnlReceiver, mLnlAddress;
    private TextView mTxtReceiver, mTxtAddress, mTxtTotalOrder, mTxtShippingFee, mTxtPromotion,
            mTxtTotalAmount, mTxtPaymentName, mTxtNote;

    private List<Product> mShoppingBag;
    private Customer mCustomer;
    private Address mAddress;
    private CartFragPresenter mPresenter;

    private int mPaymentType = 1;

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
        mTxtTotalOrder = getView().findViewById(R.id.text_view_total_order);
        mTxtShippingFee = getView().findViewById(R.id.text_view_shipping_fee);
        mTxtPromotion = getView().findViewById(R.id.text_view_promotion);
        mTxtTotalAmount = getView().findViewById(R.id.text_view_total_amount);
        mTxtPaymentName = getView().findViewById(R.id.text_view_payment_name);
        mTxtNote = getView().findViewById(R.id.text_view_note);


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
    public void showOrderItemList(List<Product> variantList) {
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
            for (Product product : mShoppingBag) {
                for (ProductVariant variant : product.getProductVariantList()) {
                    if (variant.isSelected()) {
                        OrderDetail orderDetail = new OrderDetail(
                                variant.getId(),
                                variant.getName(),
                                variant.getPicURLList().get(0),
                                variant.getSize().getName(),
                                variant.getUnitPrice(),
                                variant.getQuantity());
                        mOrderDetailList.add(orderDetail);
                    }
                }
            }
            mOrderDetailAdapter = new OrderDetailAdapter(mOrderDetailList, getContext());
            mRcvOrderDetail.setAdapter(mOrderDetailAdapter);
            mOrderDetailAdapter.setmOnItemClickListener(new OrderDetailAdapter.OnItemClickListener() {
                @Override
                public void setOnItemClickListener(int position) {
                    Product product = mShoppingBag.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ConstantDataManager.BUNDLE_PRODUCT, product);
                    EditOrderDetailActivity.intentToEditOrderDetailActivitiy(getActivity(), bundle);
                }
            });
            calculateTotal();
        }
    }

    private void calculateTotal() {
        double totalOrder = 0;
        for (Product product : mShoppingBag) {
            for (ProductVariant variant : product.getProductVariantList()) {
                if (variant.isSelected()) {
                    totalOrder += variant.getQuantity() * variant.getUnitPrice();
                }
            }
        }

        mTxtTotalOrder.setText(CurrencyManager.getPrice(totalOrder, ConstantDataManager.CURRENCY));
        mTxtShippingFee.setText(CurrencyManager.getPrice(0, ConstantDataManager.CURRENCY));
        mTxtPromotion.setText("- " + CurrencyManager.getPrice(0, ConstantDataManager.CURRENCY));
        mTxtTotalAmount.setText("- " + CurrencyManager.getPrice(totalOrder, ConstantDataManager.CURRENCY));

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
        Intent intent;
        switch (view.getId()) {
            case R.id.linear_layout_delivery_info:
                intent = new Intent(getActivity(), ShippingAddressActivity.class);
                startActivityForResult(intent, ConstantDataManager.REQUEST_CODE_ADDRESS);
                break;
            case R.id.linear_layout_note:
                intent = new Intent(getActivity(), NoteActivity.class);
                startActivityForResult(intent, ConstantDataManager.REQUEST_CODE_NOTE);
                break;
            case R.id.linear_layout_payment:
                intent = new Intent(getActivity(), PaymentActivity.class);
                startActivityForResult(intent, ConstantDataManager.REQUEST_CODE_SELECT_PAYMENT);
                break;
            case R.id.button_set_order:
                clickToSetOrder();

                break;

        }
    }

    private void clickToSetOrder(){
        OrderSuccessActivity.intentToOrderSuccessActivitiy(getActivity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ConstantDataManager.REQUEST_CODE_ADDRESS:
                getSelectedShippingAddress(requestCode, data);
                break;
            case ConstantDataManager.REQUEST_CODE_NOTE:
                getNote(resultCode, data);
                break;
            case ConstantDataManager.REQUEST_CODE_SELECT_PAYMENT:
                getPayment(resultCode, data);
                break;
        }
    }

    private void getSelectedShippingAddress(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            mPresenter.getAddress();
        }
    }

    private void getNote(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            String note = PreferenceUtils.getStringSharedPreference(getActivity(),
                    ConstantDataManager.PREFENCED_NOTE);
            mTxtNote.setText(note);
        }
    }

    private void getPayment(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            mPaymentType = PreferenceUtils.getIntSharedPreference(getActivity(),
                    ConstantDataManager.PREFENCED_PAYMENT);
            if (mPaymentType == 0) {
                mPaymentType = 1;
            }
            switch (mPaymentType) {
                case 1:
                    mTxtPaymentName.setText(R.string.title_payment_cash);
                    break;

            }
        }
    }
}
