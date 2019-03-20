package com.shoesshop.groupassignment.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.utils.ConstantManager;
import com.shoesshop.groupassignment.utils.PreferenceUtils;

public class NoteActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLnlBack;
    private Button mBtnDone;
    private EditText mEdtNote;
    private ImageView mImgDeleteNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initialView();
        initialData();
    }

    private void initialView() {
        mLnlBack = findViewById(R.id.linear_layout_back);
        mLnlBack.setOnClickListener(this);
        mBtnDone = findViewById(R.id.button_save_note);
        mBtnDone.setOnClickListener(this);
        mEdtNote = findViewById(R.id.edit_text_note);
        mImgDeleteNote = findViewById(R.id.image_delete_note);
        mImgDeleteNote.setOnClickListener(this);
        addTextChangedWatcher();
    }

    private void initialData() {
        String note = PreferenceUtils.getStringSharedPreference(NoteActivity.this,
                ConstantManager.PREFENCED_NOTE);
        mEdtNote.setText(note);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_back:
                finish();
                break;
            case R.id.button_save_note:
                String note = mEdtNote.getText().toString().trim();
                PreferenceUtils.saveStringSharedPreference(NoteActivity.this, ConstantManager.PREFENCED_NOTE, note);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.image_delete_note:
                if (mEdtNote != null && !mEdtNote.getText().toString().isEmpty()) {
                    mEdtNote.setText("");
                }
                break;
        }
    }

    private void addTextChangedWatcher() {
        mEdtNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mEdtNote.getText().toString().isEmpty()) {
                    mImgDeleteNote.setVisibility(View.GONE);
                } else {
                    mImgDeleteNote.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
