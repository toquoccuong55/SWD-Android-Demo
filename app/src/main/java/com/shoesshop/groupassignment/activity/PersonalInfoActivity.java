package com.shoesshop.groupassignment.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoesshop.groupassignment.R;

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImgClose;
    private Button mBtnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        initialView();
    }

    private void initialView() {
        mImgClose = findViewById(R.id.image_view_close);
        mImgClose.setOnClickListener(this);
        mBtnDone = findViewById(R.id.button_done);
        mBtnDone.setOnClickListener(this);
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
                finish();
                break;
        }
    }
}
