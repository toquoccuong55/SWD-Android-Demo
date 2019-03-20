package com.shoesshop.groupassignment.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.activity.AboutUsActivity;
import com.shoesshop.groupassignment.activity.ContactActivity;
import com.shoesshop.groupassignment.activity.OrderHistoryActivity;
import com.shoesshop.groupassignment.activity.PersonalInfoActivity;
import com.shoesshop.groupassignment.activity.ShippingAddressActivity;
import com.shoesshop.groupassignment.activity.SplashActivity;
import com.shoesshop.groupassignment.presenter.ProfileFragPresenter;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.utils.ConstantManager;
import com.shoesshop.groupassignment.utils.PreferenceUtils;
import com.shoesshop.groupassignment.view.ProfileFragView;

public class ProfileFragment extends Fragment implements View.OnClickListener, ProfileFragView {
    private LinearLayout mLnlCustomerInfo, mLnlShippingAddress, mLnlOrderHistory, mLnlAboutUs,
            mLnlContact, mLnlLogout;
    private TextView mTxtFullName, mTxtPhone;

    private ProfileFragPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialView();
    }

    private void initialView() {
        mLnlCustomerInfo = getView().findViewById(R.id.linear_layout_info_customer);
        mLnlCustomerInfo.setOnClickListener(this);
        mLnlShippingAddress = getView().findViewById(R.id.linear_layout_shipping_address);
        mLnlShippingAddress.setOnClickListener(this);
        mLnlOrderHistory = getView().findViewById(R.id.linear_layout_order_history);
        mLnlOrderHistory.setOnClickListener(this);
        mLnlAboutUs = getView().findViewById(R.id.linear_layout_about_us);
        mLnlAboutUs.setOnClickListener(this);
        mLnlContact = getView().findViewById(R.id.linear_layout_contact);
        mLnlContact.setOnClickListener(this);
        mLnlLogout = getView().findViewById(R.id.linear_layout_logout);
        mLnlLogout.setOnClickListener(this);
        mTxtFullName = getView().findViewById(R.id.linear_layout_fullname);
        mTxtPhone = getView().findViewById(R.id.linear_layout_phone);
        mPresenter = new ProfileFragPresenter(ProfileFragment.this, getActivity().getApplication());
    }

    @Override
    public void onStart() {
        super.onStart();
        initialData();
    }

    private void initialData() {
        mPresenter.getCustomer();

    }

    @Override
    public void showCustomer(Customer customer) {
        if (customer != null) {
            mTxtFullName.setText(customer.getFullName());
            mTxtPhone.setText(customer.getPhone());
        } else {
            mTxtFullName.setText("");
            mTxtPhone.setText("");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_info_customer:
                PersonalInfoActivity.intentToPersonalInfoActivitiy(getActivity());
                break;
            case R.id.linear_layout_shipping_address:
                ShippingAddressActivity.intentToShippingAddressActivitiy(getActivity());
                break;
            case R.id.linear_layout_order_history:
                OrderHistoryActivity.intentToOrderHistoryActivitiy(getActivity());
                break;
            case R.id.linear_layout_about_us:
                AboutUsActivity.intentToAboutUsActivitiy(getActivity());
                break;
            case R.id.linear_layout_contact:
                ContactActivity.intentToContactActivitiy(getActivity());
                break;
            case R.id.linear_layout_logout:
                showLogoutDialog();
                break;
        }
    }

    private void showLogoutDialog() {
        final Dialog dialog = new Dialog(getContext());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_information, null);
        dialog.setContentView(view);

        TextView txtTitle = dialog.findViewById(R.id.text_view_dialog_title);
        TextView txtSubInfo = dialog.findViewById(R.id.text_view_sub_infor);
        View viewLine = dialog.findViewById(R.id.view_line);
        LinearLayout lnlOptions = dialog.findViewById(R.id.linear_layout_options);
        Button option1 = dialog.findViewById(R.id.button_num1);
        Button option2 = dialog.findViewById(R.id.button_num2);

        txtTitle.setText("Bạn có chắc chắn muốn đăng xuất?");
        txtSubInfo.setText("Tất cả thông tin sẽ bị xóa sau khi bạn đăng xuất");
        viewLine.setVisibility(View.VISIBLE);
        lnlOptions.setVisibility(View.VISIBLE);
        option1.setText("Đồng ý");
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogout();
            }
        });
        option2.setText("Hủy");
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void handleLogout(){
        mPresenter.deleteCustomerInfo();
        mPresenter.deleteAllProduct();
        mPresenter.deleteAllWishList();
        PreferenceUtils.removeStringSharedPreference(getActivity(), ConstantManager.PREFENCED_NOTE);
        PreferenceUtils.removeIntSharedPreference(getActivity(), ConstantManager.PREFENCED_NOTE);

        Intent intent = new Intent(getActivity(), SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
