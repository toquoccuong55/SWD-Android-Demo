package com.shoesshop.groupassignment.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.shoesshop.groupassignment.R;

public class ShippingAddressActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLnlBack;
    private Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address);
    }

    private void initialView() {
        mLnlBack = findViewById(R.id.linear_layout_back);
        mLnlBack.setOnClickListener(this);
        mBtnSave = findViewById(R.id.button_save);
        mBtnSave.setOnClickListener(this);
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
                finish();
                break;
        }
    }
}
