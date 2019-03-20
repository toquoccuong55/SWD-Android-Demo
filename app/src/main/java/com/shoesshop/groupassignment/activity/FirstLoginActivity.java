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
import com.shoesshop.groupassignment.presenter.FirstLoginPresenter;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.view.FirstLoginView;

import java.util.regex.Pattern;

public class FirstLoginActivity extends AppCompatActivity implements View.OnClickListener, FirstLoginView {

    private static final Pattern PATTERN_PHONE
            = Pattern.compile("^(\\+84|0)[0-9]{9,10}");
    private static final Pattern PATTERN_EMAIL
            = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    private Button mBtnDone;
    private EditText mEdtName, mEdtPhone, mEdtEmail;
    private FirstLoginPresenter mFirstLoginPresenter;

    private Customer mCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);
        initialView();
        initialData();
    }

    private void initialView() {
        mBtnDone = findViewById(R.id.button_done);
        mBtnDone.setOnClickListener(this);
        mEdtName = findViewById(R.id.edit_text_profile_name);
        mEdtPhone = findViewById(R.id.edit_text_profile_phone);
        mEdtEmail = findViewById(R.id.edit_text_profile_email);

    }

    private void initialData() {
        mFirstLoginPresenter = new FirstLoginPresenter(FirstLoginActivity.this,
                FirstLoginActivity.this, getApplication());
        mFirstLoginPresenter.getCustomerInfo();
    }

    @Override
    public void showCustomerInfo(Customer customer) {
        mCustomer = customer;
        if (mCustomer != null) {
            String fullName = mCustomer.getFullName();
            String phone = mCustomer.getPhone();
            String email = mCustomer.getEmail();

            mEdtName.setText(fullName);
            mEdtPhone.setText(phone);
            mEdtEmail.setText(email);
        } else {
            mEdtName.setText("");
            mEdtPhone.setText("");
            mEdtEmail.setText("");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_done:
                clickToButtonDone();
                break;
        }
    }

    private void clickToButtonDone() {
        if (!isValid()) {
            showInvalidInfoDialog();
        } else {
            String name = mEdtName.getText().toString().trim();
            String phone = mEdtPhone.getText().toString().trim();
            String email = mEdtEmail.getText().toString().trim();

            mCustomer.setFullName(name);
            mCustomer.setPhone(phone);
            mCustomer.setEmail(email);

            mFirstLoginPresenter.insertCustomerToDB(mCustomer);

        }
    }

    @Override
    public void insertCustomerToDBSuccess(Customer customer) {
        mCustomer.setFirstLogin(false);
        mFirstLoginPresenter.updateCustomer(mCustomer);
        HomeActivity.intentToHomeActivitiy(FirstLoginActivity.this);
    }

    @Override
    public void insertCustomerToDBFail(String message) {
        showInvalidInfoDialog();
    }

    private boolean isValid() {
        boolean isPhoneValid = PATTERN_PHONE.matcher(mEdtPhone.getText().toString().trim()).matches();
        boolean isEmailValid = PATTERN_EMAIL.matcher(mEdtEmail.getText().toString().trim()).matches();
        boolean isNameValid = !mEdtEmail.getText().toString().isEmpty();
        return isPhoneValid && isEmailValid && isNameValid;
    }

    private void showInvalidInfoDialog() {
        final Dialog dialog = new Dialog(FirstLoginActivity.this);
        LayoutInflater layoutInflater = FirstLoginActivity.this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_information, null);
        dialog.setContentView(view);

        TextView txtTitle = dialog.findViewById(R.id.text_view_dialog_title);
        TextView txtSubInfo = dialog.findViewById(R.id.text_view_sub_infor);
        View viewLine = dialog.findViewById(R.id.view_line);
        View viewLine2 = dialog.findViewById(R.id.view_line2);
        LinearLayout lnlOptions = dialog.findViewById(R.id.linear_layout_options);
        Button option1 = dialog.findViewById(R.id.button_num1);
        Button option2 = dialog.findViewById(R.id.button_num2);

        txtTitle.setText("Thông tin điền vào không hợp lệ!");
        txtSubInfo.setText("Vui lòng nhập thông tin hợp lệ.");
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

    public static void intentToFirstLoginActivitiy(Activity activity) {
        Intent intent = new Intent(activity, FirstLoginActivity.class);
        activity.startActivity(intent);
    }

}
