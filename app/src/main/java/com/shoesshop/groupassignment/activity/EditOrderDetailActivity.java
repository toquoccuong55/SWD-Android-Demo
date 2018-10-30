package com.shoesshop.groupassignment.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shoesshop.groupassignment.R;

public class EditOrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order_detail);
    }


    public static void intentToEditOrderDetailActivitiy(Activity activity) {
        Intent intent = new Intent(activity, EditOrderDetailActivity.class);
        activity.startActivity(intent);
    }
}
