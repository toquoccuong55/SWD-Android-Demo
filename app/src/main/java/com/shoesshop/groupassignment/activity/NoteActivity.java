package com.shoesshop.groupassignment.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shoesshop.groupassignment.R;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }

    public static void intentToNoteActivitiy(Activity activity) {
        Intent intent = new Intent(activity, NoteActivity.class);
        activity.startActivity(intent);
    }
}
