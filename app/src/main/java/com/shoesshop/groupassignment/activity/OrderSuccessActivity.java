package com.shoesshop.groupassignment.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shoesshop.groupassignment.R;

public class OrderSuccessActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnOrderMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);
        initialView();
    }

    private void initialView(){
        mBtnOrderMore = findViewById(R.id.button_order_more);
        mBtnOrderMore.setOnClickListener(this);
    }

    public static void intentToOrderSuccessActivitiy(Activity activity) {
        Intent intent = new Intent(activity, OrderSuccessActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_order_more:
                HomeActivity.intentToHomeActivitiy(OrderSuccessActivity.this);
                finish();
                break;
        }
    }
}
