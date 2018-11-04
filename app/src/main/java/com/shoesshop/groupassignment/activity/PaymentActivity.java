package com.shoesshop.groupassignment.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.utils.ConstantDataManager;
import com.shoesshop.groupassignment.utils.PreferenceUtils;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLnlBack;
    private Button mBtnDone;
    private RadioGroup mRdgPayment;
    private RadioButton mRdbCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        intialView();
    }

    private void intialView() {
        mLnlBack = findViewById(R.id.linear_layout_back);
        mLnlBack.setOnClickListener(this);
        mBtnDone = findViewById(R.id.button_done);
        mBtnDone.setOnClickListener(this);
        mRdgPayment = findViewById(R.id.radio_group_payment);
        mRdbCash = findViewById(R.id.radio_button_cash);


    }

    public static void intentToPaymentActivitiy(Activity activity) {
        Intent intent = new Intent(activity, PaymentActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_back:
                finish();
                break;
            case R.id.button_done:
                clickToButtonDone();
                break;
        }
    }

    private void clickToButtonDone() {
        int selectedID = mRdgPayment.getCheckedRadioButtonId();
        switch (selectedID) {
            case R.id.radio_button_cash:
                PreferenceUtils.saveIntSharedPreference(PaymentActivity.this,
                        ConstantDataManager.PREFENCED_PAYMENT, 1);
                break;
        }
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
