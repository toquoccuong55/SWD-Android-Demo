package com.shoesshop.groupassignment.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.fragment.AddLocationBottomDialogFragment;
import com.shoesshop.groupassignment.presenter.AddressPresenter;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.view.AddressView;

public class ShippingAddressActivity extends AppCompatActivity implements View.OnClickListener, AddressView {
    private LinearLayout mLnlBack, mLnlAddressType;
    private Button mBtnSave;
    public TextView mTxtType, mTxtReceiver, mTxtPhone;
    private EditText mEdtAddress;
    private String TAG = "ShippingAddressActivity";
    private AddressPresenter mAddressPresenter;
    private Customer mCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address);
        initialView();
        initialData();
    }

    private void initialView() {
        mLnlBack = findViewById(R.id.linear_layout_back);
        mLnlBack.setOnClickListener(this);
        mBtnSave = findViewById(R.id.button_save);
        mBtnSave.setOnClickListener(this);
        mLnlAddressType = findViewById(R.id.linear_layout_show_dialog_type);
        mLnlAddressType.setOnClickListener(this);
        mTxtType = findViewById(R.id.text_view_address_type);
        mEdtAddress = findViewById(R.id.edit_text_address);
        mTxtReceiver = findViewById(R.id.text_view_customer_name);
        mTxtPhone = findViewById(R.id.text_view_phone);


    }

    private void initialData() {
        mAddressPresenter = new AddressPresenter(ShippingAddressActivity.this, getApplication());
        mAddressPresenter.getCustomer();

    }

    @Override
    public void showCustomer(Customer customer) {
        mCustomer = customer;
        if (mCustomer != null) {
            mTxtType.setText(mCustomer.getAddressType());
            mEdtAddress.setText(mCustomer.getAddress());
            mTxtReceiver.setText(mCustomer.getFullName());
            mTxtPhone.setText(mCustomer.getPhone());
        } else {
            String addressType = getResources().getString(R.string.company);
            mTxtType.setText(addressType);
            mEdtAddress.setText("");
            mTxtReceiver.setText("");
            mTxtPhone.setText("");
        }
    }

    public static void intentToShippingAddressActivitiy(Activity activity) {
        Intent intent = new Intent(activity, ShippingAddressActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_back:
                finish();
                break;
            case R.id.button_save:
                clickToButtonSave();
                break;
            case R.id.linear_layout_show_dialog_type:
                showFragment();
                break;
        }
    }

    private void clickToButtonSave() {

        String typeString = mTxtType.getText().toString();
        String address = mEdtAddress.getText().toString();
        if(typeString.isEmpty() || address.isEmpty()){
            showInvalidInputDialog();
        }else{
            if (mCustomer != null) {
                mCustomer.setAddressType(typeString);
                mCustomer.setAddress(address);
            } else {
                mCustomer = new Customer();
                mCustomer.setAddressType(typeString);
                mCustomer.setAddress(address);
                mCustomer.setFullName(mTxtReceiver.getText().toString());
                mCustomer.setPhone(mTxtPhone.getText().toString());
            }
            mAddressPresenter.updateCustomer(mCustomer);
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private void showInvalidInputDialog() {
        final Dialog dialog = new Dialog(ShippingAddressActivity.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_information, null);
        dialog.setContentView(view);

        TextView txtTitle = dialog.findViewById(R.id.text_view_dialog_title);
        TextView txtSubInfo = dialog.findViewById(R.id.text_view_sub_infor);
        View viewLine = dialog.findViewById(R.id.view_line);
        View viewLine2 = dialog.findViewById(R.id.view_line2);
        LinearLayout lnlOptions = dialog.findViewById(R.id.linear_layout_options);
        Button option1 = dialog.findViewById(R.id.button_num1);
        Button option2 = dialog.findViewById(R.id.button_num2);

        txtTitle.setText("Thông tin hợp lệ!");
        txtSubInfo.setText("Xin kiểm tra lại thông tin cá nhân");
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

    public void showFragment() {
        AddLocationBottomDialogFragment addBottomDialogFragment =
                AddLocationBottomDialogFragment.newInstance();
        addBottomDialogFragment.show(getSupportFragmentManager(), TAG);
    }

}
