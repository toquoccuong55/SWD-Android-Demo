package com.shoesshop.groupassignment.fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.activity.ShippingAddressActivity;

public class AddLocationBottomDialogFragment  extends BottomSheetDialogFragment implements View.OnClickListener {
    private ImageView mImgCloseDialog;
    private LinearLayout mLnLCompany;
    private LinearLayout mLnLHouse;
    private LinearLayout mLnLOtherLocation;
    private View mViewFragment;

    public static AddLocationBottomDialogFragment newInstance() {
        return new AddLocationBottomDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewFragment = inflater.inflate(R.layout.fragment_select_location, container,
                false);
        initialView();
        return mViewFragment;
    }

    public void initialView() {
        mImgCloseDialog = mViewFragment.findViewById(R.id.image_view_close_dialog);
        mImgCloseDialog.setOnClickListener(this);
        mLnLCompany = mViewFragment.findViewById(R.id.linear_layout_company);
        mLnLCompany.setOnClickListener(this);
        mLnLHouse = mViewFragment.findViewById(R.id.linear_layout_house);
        mLnLHouse.setOnClickListener(this);
        mLnLOtherLocation = mViewFragment.findViewById(R.id.linear_layout_other_location);
        mLnLOtherLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_view_close_dialog: {
                dismiss();
                break;
            }
            case R.id.linear_layout_company: {
                getShippingAddressActivity().mTxtType.setText(R.string.company);
                dismiss();
                break;
            }
            case R.id.linear_layout_house: {
                getShippingAddressActivity().mTxtType.setText(R.string.house);
                dismiss();
                break;
            }
            case R.id.linear_layout_other_location: {
                getShippingAddressActivity().mTxtType.setText(R.string.otherLocation);
                dismiss();
                break;
            }
        }
    }

    private ShippingAddressActivity getShippingAddressActivity() {
        return (ShippingAddressActivity) getActivity();
    }
}
