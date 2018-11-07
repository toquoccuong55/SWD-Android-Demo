package com.shoesshop.groupassignment.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.activity.EditOrderDetailActivity;
import com.shoesshop.groupassignment.activity.NoteActivity;
import com.shoesshop.groupassignment.activity.OrderSuccessActivity;
import com.shoesshop.groupassignment.activity.PaymentActivity;
import com.shoesshop.groupassignment.activity.ShippingAddressActivity;
import com.shoesshop.groupassignment.adapter.OrderDetailAdapter;
import com.shoesshop.groupassignment.model.Order;
import com.shoesshop.groupassignment.model.OrderDetail;
import com.shoesshop.groupassignment.model.SuccessedOrder;
import com.shoesshop.groupassignment.presenter.CartFragPresenter;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.entity.ProductVariant;
import com.shoesshop.groupassignment.utils.ConstantDataManager;
import com.shoesshop.groupassignment.utils.CurrencyManager;
import com.shoesshop.groupassignment.utils.PreferenceUtils;
import com.shoesshop.groupassignment.view.CartFragView;

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
    private Button mBtnOrder;

    private List<Product> mShoppingBag;
    private Customer mCustomer;
    private CartFragPresenter mPresenter;

    private int mPaymentType = 1;
    private String mNoteString = "", mAddressString = "";

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
        mBtnOrder = getView().findViewById(R.id.button_set_order);
        mBtnOrder.setOnClickListener(this);

        mOrderInfo = getView().findViewById(R.id.linear_layout_order_infor);
        mPaymentInfo = getView().findViewById(R.id.linear_layout_payment_infor);
        mLnlEmptyCart = getView().findViewById(R.id.linear_layout_empty_cart);

        mPresenter = new CartFragPresenter(getActivity(), CartFragment.this, getActivity().getApplication());

    }

    private void initialData() {
        mPresenter.getOrderItemList();
        mPresenter.getCustomer();
        getNote();
        getPayment();
    }

    @Override
    public void showOrderItemList(List<Product> productList) {
        mShoppingBag = productList;
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
                                variant.getSizeString(),
                                variant.getUnitPrice(),
                                variant.getBuyQuantity());
                        mOrderDetailList.add(orderDetail);
                        break;
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
                    totalOrder += variant.getBuyQuantity() * variant.getUnitPrice();
                    break;
                }
            }
        }

        mTxtTotalOrder.setText(CurrencyManager.getPrice(totalOrder, ConstantDataManager.CURRENCY));
        mTxtShippingFee.setText(CurrencyManager.getPrice(0, ConstantDataManager.CURRENCY));
        mTxtPromotion.setText("- " + CurrencyManager.getPrice(0, ConstantDataManager.CURRENCY));
        mTxtTotalAmount.setText(CurrencyManager.getPrice(totalOrder, ConstantDataManager.CURRENCY));

    }

    @Override
    public void showCustomer(Customer customer) {
        mCustomer = customer;
        if (mCustomer == null) {
            mTxtReceiver.setText("Vui lòng nhập thông tin người nhận tại đây");
            mTxtAddress.setText("Vui lòng nhập địa chỉ tại đây!");
        } else {
            if (mCustomer.getFullName().isEmpty() || mCustomer.getPhone().isEmpty()) {
                mTxtReceiver.setText("Vui lòng nhập thông tin người nhận tại đây");
            } else {
                String receiver = mCustomer.getFullName() + " - " + mCustomer.getPhone();
                mTxtReceiver.setText(receiver);
            }
            if (mCustomer.getAddress().trim().isEmpty()) {
                mTxtAddress.setText("Vui lòng nhập địa chỉ tại đây!");
            } else {
                mAddressString = mCustomer.getAddressType() + ": " + mCustomer.getAddress();
                mTxtAddress.setText(mAddressString);
            }
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
                showConfirmDialog();
                break;

        }
    }

    private void showConfirmDialog() {
        final Dialog dialog = new Dialog(getContext());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_information, null);
        dialog.setContentView(view);

        TextView txtTitle = dialog.findViewById(R.id.text_view_dialog_title);
        TextView txtSubInfo = dialog.findViewById(R.id.text_view_sub_infor);
        View viewLine = dialog.findViewById(R.id.view_line);
        View viewLine2 = dialog.findViewById(R.id.view_line2);
        LinearLayout lnlOptions = dialog.findViewById(R.id.linear_layout_options);
        Button option1 = dialog.findViewById(R.id.button_num1);
        Button option2 = dialog.findViewById(R.id.button_num2);

        txtTitle.setText("Bạn xác nhận đặt hàng nhé?");
        txtSubInfo.setVisibility(View.GONE);
        viewLine.setVisibility(View.VISIBLE);
        viewLine2.setVisibility(View.VISIBLE);
        lnlOptions.setVisibility(View.VISIBLE);
        option1.setText("Chờ đã");
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        option2.setText("Đồng ý");
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickToSetOrder();
                dialog.cancel();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void clickToSetOrder() {

        Order order = new Order();

        if (mCustomer.getAccessToken().isEmpty()) {
            showInvalidInputDialog();
        }else{
            order.setAccessToken(mCustomer.getAccessToken());
            order.setNote(mNoteString);
            if (mAddressString.isEmpty()) {
                showInvalidInputDialog();
            } else {
                order.setShippingAddress(mAddressString);
                order.setPaymentType(mPaymentType);

                ArrayList<ProductVariant> variantArrayList = new ArrayList<>();
                for (Product product : mShoppingBag) {
                    for (ProductVariant variant : product.getProductVariantList()) {
                        if (variant.isSelected()) {
                            variantArrayList.add(variant);
                            break;
                        }
                    }
                }
                order.setOrderDetailList(variantArrayList);

                mPresenter.setOrder(order);
            }
        }
    }

    private void showInvalidInputDialog() {
        final Dialog dialog = new Dialog(getContext());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_information, null);
        dialog.setContentView(view);

        TextView txtTitle = dialog.findViewById(R.id.text_view_dialog_title);
        TextView txtSubInfo = dialog.findViewById(R.id.text_view_sub_infor);
        View viewLine = dialog.findViewById(R.id.view_line);
        View viewLine2 = dialog.findViewById(R.id.view_line2);
        LinearLayout lnlOptions = dialog.findViewById(R.id.linear_layout_options);
        Button option1 = dialog.findViewById(R.id.button_num1);
        Button option2 = dialog.findViewById(R.id.button_num2);

        txtTitle.setText("Thông tin đơn hàng chưa hợp lệ!");
        txtSubInfo.setText("Xin kiểm tra lại thông tin của đơn hàng");
        viewLine.setVisibility(View.VISIBLE);
        viewLine2.setVisibility(View.GONE);
        lnlOptions.setVisibility(View.VISIBLE);
        option1.setText("Thử lại");
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        option2.setVisibility(View.GONE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void setOrderSuccess(SuccessedOrder successedOrder) {
        if (successedOrder != null) {
            if (successedOrder.getOrderId() > 0) {

                mPresenter.deleteAllProduct();
                PreferenceUtils.removeStringSharedPreference(getActivity(), ConstantDataManager.PREFENCED_NOTE);

                OrderSuccessActivity.intentToOrderSuccessActivitiy(getActivity());
                getActivity().finish();
            } else {
                showOrderFailedDialog();
            }
        } else {
            showOrderFailedDialog();
        }
    }

    private void showOrderFailedDialog() {
        final Dialog dialog = new Dialog(getContext());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_information, null);
        dialog.setContentView(view);

        TextView txtTitle = dialog.findViewById(R.id.text_view_dialog_title);
        TextView txtSubInfo = dialog.findViewById(R.id.text_view_sub_infor);
        View viewLine = dialog.findViewById(R.id.view_line);
        View viewLine2 = dialog.findViewById(R.id.view_line2);
        LinearLayout lnlOptions = dialog.findViewById(R.id.linear_layout_options);
        Button option1 = dialog.findViewById(R.id.button_num1);
        Button option2 = dialog.findViewById(R.id.button_num2);

        txtTitle.setText("Đặt hàng không thành công");
        txtSubInfo.setText("Xin kiểm tra lại thông tin của đơn hàng hoặc đường truyền Internet");
        viewLine.setVisibility(View.VISIBLE);
        viewLine2.setVisibility(View.GONE);
        lnlOptions.setVisibility(View.VISIBLE);
        option1.setText("Thử lại");
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ConstantDataManager.REQUEST_CODE_ADDRESS:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    mPresenter.getCustomer();
                }
                break;

            case ConstantDataManager.REQUEST_CODE_NOTE:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    getNote();
                }
                break;

            case ConstantDataManager.REQUEST_CODE_SELECT_PAYMENT:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    getPayment();
                }
                break;

        }
    }

    private void getNote() {
        mNoteString = PreferenceUtils.getStringSharedPreference(getActivity(),
                ConstantDataManager.PREFENCED_NOTE);
        if (mNoteString.isEmpty()) {
            mTxtNote.setText("Bạn có ghi chú gì không?");
        } else {
            mTxtNote.setText(mNoteString);
        }
    }

    private void getPayment() {

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
