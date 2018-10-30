package com.shoesshop.groupassignment.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.shoesshop.groupassignment.R;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout mLnlBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initialView();
    }

    private void initialView() {
        mLnlBack = findViewById(R.id.linear_layout_back);
        mLnlBack.setOnClickListener(this);
    }

    public static void intentToContactActivitiy(Activity activity) {
        Intent intent = new Intent(activity, ContactActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_back:
                finish();
                break;
        }
    }
}
