package com.shoesshop.groupassignment.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.adapter.MainSliderAdapter;
import com.shoesshop.groupassignment.adapter.RelatedProductAdapter;
import com.shoesshop.groupassignment.model.Size;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;

public class EditOrderDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLnlBack;
    private Button mBtnDone;
    private Slider mSlider;
    private List<String> mImageList;
    private FlowLayout mFlowLayoutSize;
    private TextView mTxtProductName, mTxtPrice, mTxtDescription, mTxtDeleteProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order_detail);
        initalView();
        initialData();
    }

    private void initalView() {
        mSlider = findViewById(R.id.slider_product_images);
        mLnlBack = findViewById(R.id.linear_layout_back);
        mLnlBack.setOnClickListener(this);
        mFlowLayoutSize = findViewById(R.id.flow_layout_list_size);
        mFlowLayoutSize.setOnClickListener(this);

        mTxtPrice = findViewById(R.id.text_view_price);
        mTxtProductName = findViewById(R.id.text_view_product_name);
        mTxtDescription = findViewById(R.id.text_view_description);
        mBtnDone = findViewById(R.id.button_done);
        mBtnDone.setOnClickListener(this);
        mTxtDeleteProduct = findViewById(R.id.text_view_delete_product);
        mTxtDeleteProduct.setOnClickListener(this);

    }

    private void initialData() {
        addSizeLayoutToFlowLayout();

        mImageList = new ArrayList<>();
        mImageList.add("https://image.goat.com/crop/750/attachments/product_template_additional_pictures/images/008/491/941/original/73359_01.jpg.jpeg");
        mImageList.add("https://image.goat.com/crop/750/attachments/product_template_additional_pictures/images/010/140/793/original/189274_01.jpg.jpeg");
        mImageList.add("https://image.goat.com/crop/750/attachments/product_template_additional_pictures/images/010/140/831/original/189275_01.jpg.jpeg");
        mImageList.add("https://image.goat.com/crop/750/attachments/product_template_pictures/images/004/896/057/original/AH9110_023.png");
        mImageList.add("https://image.goat.com/crop/750/attachments/product_template_pictures/images/014/770/352/original/AJ8_647380.png.png");

        mSlider.setAdapter(new MainSliderAdapter(mImageList));

    }

    public void addSizeLayoutToFlowLayout() {

        ArrayList<Size> sizeList = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        name.add("38");
        name.add("39");
        name.add("40");
        name.add("41");
        name.add("42");
        name.add("43");

        for (int i = 0; i < name.size(); i++) {
            Size size = new Size();
            size.setName(name.get(i));
            sizeList.add(size);
        }

        for (int i = 0; i < sizeList.size(); i++) {
            View to_add = buildSizeTag(sizeList.get(i).getName());
            mFlowLayoutSize.addView(to_add);
        }
    }

    private View buildSizeTag(String colorName) {
        TextView mTxtSizeName;
        Context context = this.getBaseContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View colorView = inflater.inflate(R.layout.row_item_size,
                mFlowLayoutSize, false);
        mTxtSizeName = colorView.findViewById(R.id.text_view_size_name);
        mTxtSizeName.setText(colorName);
        return colorView;
    }

    public static void intentToEditOrderDetailActivitiy(Activity activity) {
        Intent intent = new Intent(activity, EditOrderDetailActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_back:
                finish();
                break;
            case R.id.button_done:
                finish();
                break;
            case R.id.text_view_delete_product:
                showDeleteDialog();
                break;

        }
    }

    private void showDeleteDialog() {
        final Dialog dialog = new Dialog(EditOrderDetailActivity.this);
        LayoutInflater layoutInflater = EditOrderDetailActivity.this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_information, null);
        dialog.setContentView(view);

        TextView txtTitle = dialog.findViewById(R.id.text_view_dialog_title);
        TextView txtSubInfo = dialog.findViewById(R.id.text_view_sub_infor);
        View viewLine = dialog.findViewById(R.id.view_line);
        LinearLayout lnlOptions = dialog.findViewById(R.id.linear_layout_options);
        Button option1 = dialog.findViewById(R.id.button_num1);
        Button option2 = dialog.findViewById(R.id.button_num2);

        txtTitle.setText("Bạn muốn bỏ sản phẩm này?");
        txtSubInfo.setText("Sản phẩm sẽ bị xóa trong giỏ hàng.");
        viewLine.setVisibility(View.VISIBLE);
        lnlOptions.setVisibility(View.VISIBLE);
        option1.setText("Đồng ý");
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        option2.setText("Hủy");
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
