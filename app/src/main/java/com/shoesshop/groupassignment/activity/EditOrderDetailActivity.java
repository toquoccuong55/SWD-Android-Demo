package com.shoesshop.groupassignment.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.adapter.MainSliderAdapter;
import com.shoesshop.groupassignment.adapter.SizeListAdapter;
import com.shoesshop.groupassignment.model.Size;
import com.shoesshop.groupassignment.presenter.EditOrderDetailPresenter;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.room.entity.ProductVariant;
import com.shoesshop.groupassignment.utils.ConstantDataManager;
import com.shoesshop.groupassignment.utils.CurrencyManager;
import com.shoesshop.groupassignment.utils.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;

public class EditOrderDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLnlBack;
    private Button mBtnDone;
    private Slider mSlider;
    private List<String> mImageList;
    private FlowLayout mFlowLayoutSize;
    private TextView mTxtProductName, mTxtPrice, mTxtDescription, mTxtDeleteProduct, mTxtQuantity,
            mTxtDescrease, mTxtPlus;

    private RecyclerView mRcvSizeList;
    private List<Size> mSizeList;
    private SizeListAdapter mSizeListAdapter;

    private Product mProduct;
    private ProductVariant mVariant;
    private double mUnitPrice, mTotal;
    private int mQuantity;
    private EditOrderDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order_detail);
        getInitialIntent();
        initalView();
        initialData();
    }

    private void getInitialIntent() {
        Bundle bundle = getIntent().getBundleExtra(ConstantDataManager.INTENT_BUNDLE);
        if (bundle != null) {
            mProduct = (Product) bundle.getSerializable(ConstantDataManager.BUNDLE_PRODUCT);
        }
    }

    private void initalView() {
        mSlider = findViewById(R.id.slider_product_images);
        mLnlBack = findViewById(R.id.linear_layout_back);
        mLnlBack.setOnClickListener(this);

        mTxtPrice = findViewById(R.id.text_view_price);
        mTxtProductName = findViewById(R.id.text_view_product_name);
        mTxtDescription = findViewById(R.id.text_view_description);
        mBtnDone = findViewById(R.id.button_done);
        mBtnDone.setOnClickListener(this);
        mTxtDeleteProduct = findViewById(R.id.text_view_delete_product);
        mTxtDeleteProduct.setOnClickListener(this);
        mTxtQuantity = findViewById(R.id.text_view_quantity);
        mTxtDescrease = findViewById(R.id.text_view_substract);
        mTxtDescrease.setOnClickListener(this);
        mTxtPlus = findViewById(R.id.text_view_plus);
        mTxtPlus.setOnClickListener(this);

        mRcvSizeList = findViewById(R.id.recycler_view_list_size);
        mRcvSizeList.setHasFixedSize(true);
        int numberOfColumn = calculateNumberOfColumns(EditOrderDetailActivity.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(EditOrderDetailActivity.this,
                numberOfColumn);
        mRcvSizeList.setLayoutManager(gridLayoutManager);
        int spacing = getResources().getDimensionPixelSize(R.dimen.dp10);
        mRcvSizeList.addItemDecoration(new GridSpacingItemDecoration(numberOfColumn, spacing, true));

    }

    private void initialData() {
        mPresenter = new EditOrderDetailPresenter(getApplication());
        if (mProduct != null) {

            mImageList = new ArrayList<>();
            mImageList.add("https://image.goat.com/crop/750/attachments/product_template_additional_pictures/images/008/491/941/original/73359_01.jpg.jpeg");
            mImageList.add("https://image.goat.com/crop/750/attachments/product_template_additional_pictures/images/010/140/793/original/189274_01.jpg.jpeg");
            mImageList.add("https://image.goat.com/crop/750/attachments/product_template_additional_pictures/images/010/140/831/original/189275_01.jpg.jpeg");
            mImageList.add("https://image.goat.com/crop/750/attachments/product_template_pictures/images/004/896/057/original/AH9110_023.png");
            mImageList.add("https://image.goat.com/crop/750/attachments/product_template_pictures/images/014/770/352/original/AJ8_647380.png.png");
            mSlider.setAdapter(new MainSliderAdapter(mImageList));

            mTxtProductName.setText(mProduct.getName());
            for (ProductVariant variant : mProduct.getProductVariantList()) {
                if (variant.isSelected()) {
                    mVariant = variant;
                }
            }
            if (mVariant != null) {
                double total = mVariant.getUnitPrice();
                int quantity = mVariant.getQuantity();
                int id = mVariant.getId();

                mTxtPrice.setText(CurrencyManager.getPrice(total * quantity, ConstantDataManager.CURRENCY));
                mSizeList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    Size size = new Size();
                    size.setId(i);
                    size.setName(String.valueOf(i));
                    size.setChecked(false);
                    if (size.getId() == id) {
                        size.setChecked(true);
                    }
                    mSizeList.add(size);
                }
                mSizeListAdapter = new SizeListAdapter(mSizeList, EditOrderDetailActivity.this);
                mRcvSizeList.setAdapter(mSizeListAdapter);
                mSizeListAdapter.setmOnItemClickListener(new SizeListAdapter.OnItemClickListener() {
                    @Override
                    public void setOnItemClickListener(int position) {
                        for (Size size : mSizeList) {
                            size.setChecked(false);
                        }
                        mSizeList.get(position).setChecked(true);
                        mSizeListAdapter.notifyDataSetChanged();
                        mTotal = calculateToTal();
                        mTxtPrice.setText(CurrencyManager.getPrice(mTotal, ConstantDataManager.CURRENCY));
                    }
                });
                mTxtQuantity.setText(String.valueOf(quantity));
                mTxtDescription.setText(mProduct.getDescription());
            }


        } else {

        }
    }

    private double calculateToTal() {
        double total = 0;
        int selectedID = 0;
        for (Size size : mSizeList) {
            if (size.isChecked()) {
                selectedID = size.getId();
                break;
            }
        }
        ProductVariant selectedVariant = null;
        if (selectedID != 0) {
            for (ProductVariant variant : mProduct.getProductVariantList()) {
                if (variant.getId() == selectedID) {
                    selectedVariant = variant;
                    break;
                }
            }
        }
        int quantity = Integer.parseInt(mTxtQuantity.getText().toString().trim());
        if (selectedVariant != null) {
            total = quantity * selectedVariant.getUnitPrice();
        }
        return total;
    }

    private int calculateNumberOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 80);
        return noOfColumns;
    }

    public static void intentToEditOrderDetailActivitiy(Activity activity, Bundle bundle) {
        Intent intent = new Intent(activity, EditOrderDetailActivity.class);
        intent.putExtra(ConstantDataManager.INTENT_BUNDLE, bundle);
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
            case R.id.text_view_delete_product:
                showDeleteDialog();
                break;
            case R.id.text_view_substract:
                clickToSubstract();
                break;
            case R.id.text_view_plus:
                clickToPlus();
                break;
        }
    }

    private void clickToButtonDone() {
        int selectedID = 0;
        for (Size size : mSizeList) {
            if (size.isChecked()) {
                selectedID = size.getId();
                break;
            }
        }
        if (selectedID != 0) {
            for (ProductVariant variant : mProduct.getProductVariantList()) {
                if (variant.getId() == selectedID) {
                    int quantity = Integer.parseInt(mTxtQuantity.getText().toString().trim());
                    variant.setQuantity(quantity);
                    variant.setSelected(true);
                    break;
                }
            }
        }
        mPresenter.updateProduct(mProduct);
        finish();
    }

    private void clickToSubstract() {
        int quantity = Integer.parseInt(mTxtQuantity.getText().toString());
        if (quantity >= 2) {
            quantity -= 1;
        }
        mTxtQuantity.setText(String.valueOf(quantity));
        mTxtPrice.setText(CurrencyManager.getPrice(mTotal, ConstantDataManager.CURRENCY));
    }

    private void clickToPlus() {
        int quantity = Integer.parseInt(mTxtQuantity.getText().toString());
        quantity += 1;
        mTxtQuantity.setText(String.valueOf(quantity));
        mTotal = calculateToTal();
        mTxtPrice.setText(CurrencyManager.getPrice(mTotal, ConstantDataManager.CURRENCY));
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
                mPresenter.deleteProduct(mProduct);
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
