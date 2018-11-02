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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.presenter.PersonalInfoPresenter;
import com.shoesshop.groupassignment.room.entity.Customer;
import com.shoesshop.groupassignment.utils.ConstantDataManager;
import com.shoesshop.groupassignment.view.PersonalInfoView;

import java.util.regex.Pattern;

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener,
        PersonalInfoView {

    private static final Pattern PATTERN_PHONE
            = Pattern.compile("^(\\+84|0)[0-9]{9,10}");
    private static final Pattern PATTERN_EMAIL
            = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    private ImageView mImgClose;
    private Button mBtnDone;
    private EditText mEdtName, mEdtEmail, mEdtPhone;
    private String mFullName, mPhone, mEmail;
    private Customer mCustomer;
    private PersonalInfoPresenter mPersonalInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        initialView();
        initialData();
    }


    private void initialView() {
        mImgClose = findViewById(R.id.image_view_close);
        mImgClose.setOnClickListener(this);
        mBtnDone = findViewById(R.id.button_done);
        mBtnDone.setOnClickListener(this);

        mEdtName = findViewById(R.id.edit_text_profile_name);
        mEdtEmail = findViewById(R.id.edit_text_profile_email);
        mEdtPhone = findViewById(R.id.edit_text_profile_phone);

    }

    private void initialData() {
        mPersonalInfoPresenter = new PersonalInfoPresenter(PersonalInfoActivity.this,
                PersonalInfoActivity.this, getApplication());
        mPersonalInfoPresenter.getCustomerInfo();

    }

    @Override
    public void showCustomerInfo(Customer customer) {
        mCustomer = customer;
        if (mCustomer != null) {
            mPhone = mCustomer.getPhone();
            mFullName = mCustomer.getFullName();
            mEmail = mCustomer.getEmail();

            mEdtName.setText(mFullName);
            mEdtPhone.setText(mPhone);
            mEdtPhone.setEnabled(false);
            mEdtEmail.setText(mEmail);
        } else {
            mImgClose.setVisibility(View.GONE);
            mEdtName.setText("");
            mEdtPhone.setText("");
            mEdtEmail.setText("");
        }
    }

    public static void intentToPersonalInfoActivitiy(Activity activity) {
        Intent intent = new Intent(activity, PersonalInfoActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_view_close:
                finish();
                break;
            case R.id.button_done:
                savePersonal();
                break;
        }
    }

    public void savePersonal() {
        final String name = mEdtName.getText().toString();
        final String email = mEdtEmail.getText().toString();
//        String phone = mEdtPhone.getText().toString();

        if (!isValid()) {
            showInvalidInfoDialog();
        } else {
            mCustomer.setFullName(name);
//            mCustomer.setPhone(phone);
            mCustomer.setEmail(email);

            mPersonalInfoPresenter.updateCustomer(mCustomer);
        }
        finish();
    }

    private boolean isValid() {
        boolean isPhoneValid = PATTERN_PHONE.matcher(mEdtPhone.getText().toString().trim()).matches();
        boolean isEmailValid = PATTERN_EMAIL.matcher(mEdtEmail.getText().toString().trim()).matches();
        boolean isNameValid = !mEdtEmail.getText().toString().isEmpty();
        return isPhoneValid && isEmailValid && isNameValid;
    }

    private void showInvalidInfoDialog() {
        final Dialog dialog = new Dialog(PersonalInfoActivity.this);
        LayoutInflater layoutInflater = PersonalInfoActivity.this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_information, null);
        dialog.setContentView(view);

        TextView txtTitle = dialog.findViewById(R.id.text_view_dialog_title);
        TextView txtSubInfo = dialog.findViewById(R.id.text_view_sub_infor);
        View viewLine = dialog.findViewById(R.id.view_line);
        LinearLayout lnlOptions = dialog.findViewById(R.id.linear_layout_options);
        Button option1 = dialog.findViewById(R.id.button_num1);
        Button option2 = dialog.findViewById(R.id.button_num2);

        txtTitle.setText("Thông tin điền vào không hợp lệ!");
        txtSubInfo.setText("Vui lòng nhập thông tin hợp lệ.");
        viewLine.setVisibility(View.VISIBLE);
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

}
