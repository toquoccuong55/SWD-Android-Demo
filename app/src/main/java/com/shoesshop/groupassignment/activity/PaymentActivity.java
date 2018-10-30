package com.shoesshop.groupassignment.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shoesshop.groupassignment.R;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    public static void intentToPaymentActivitiy(Activity activity) {
        Intent intent = new Intent(activity, PaymentActivity.class);
        activity.startActivity(intent);
    }
}
