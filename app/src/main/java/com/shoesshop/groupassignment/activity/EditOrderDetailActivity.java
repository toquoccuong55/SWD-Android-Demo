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
import com.shoesshop.groupassignment.utils.PicassoLoadingService;
import com.shoesshop.groupassignment.view.EditOrderView;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;

public class EditOrderDetailActivity extends AppCompatActivity implements View.OnClickListener, EditOrderView {
    private LinearLayout mLnlBack;
    private Button mBtnDone;
    private Slider mSlider;
    private List<String> mImageList;
    private TextView mTxtProductName, mTxtPrice, mTxtDescription, mTxtDeleteProduct, mTxtQuantity,
            mTxtDescrease, mTxtPlus;

    private RecyclerView mRcvSizeList;
    private List<Size> mSizeList;
    private SizeListAdapter mSizeListAdapter;

    private Product mProduct;
    private double mTotal;
    private EditOrderDetailPresenter mPresenter;

    private List<Product> mShoppingBag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order_detail);
        getInitialIntent();
        initalView();
        initialData();
        Slider.init(new PicassoLoadingService());
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
        mPresenter = new EditOrderDetailPresenter(EditOrderDetailActivity.this, getApplication());
        mPresenter.getShoppingBag();

        if (mProduct != null) {

            mImageList = mProduct.getProductVariantList().get(0).getPicURLList();
            mSlider.setAdapter(new MainSliderAdapter(mImageList));
            mTxtProductName.setText(mProduct.getName());
            ProductVariant selectedVariant = null;
            for (ProductVariant variant : mProduct.getProductVariantList()) {
                if (variant.isSelected()) {
                    selectedVariant = variant;
                }
            }
            if (selectedVariant != null) {
                mTxtPrice.setText(CurrencyManager.getPrice(selectedVariant.getUnitPrice(),
                        ConstantDataManager.CURRENCY));

                mSizeList = new ArrayList<>();
                for (ProductVariant productVariant : mProduct.getProductVariantList()) {
                    Size size = new Size();
                    size.setId(productVariant.getId());
                    size.setName(productVariant.getSizeString());
                    if (productVariant.getId() != selectedVariant.getId()) {
                        size.setChecked(false);
                    } else {
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

                mTxtQuantity.setText(String.valueOf(selectedVariant.getBuyQuantity()));
                mTxtDescription.setText(mProduct.getDescription());
            } else {
                mTxtPrice.setText(CurrencyManager.getPrice(mProduct.getProductVariantList().get(0).getUnitPrice(),
                        ConstantDataManager.CURRENCY));

                mSizeList = new ArrayList<>();
                for (ProductVariant productVariant : mProduct.getProductVariantList()) {
                    Size size = new Size();
                    size.setId(productVariant.getId());
                    size.setName(productVariant.getSizeString());
                    size.setChecked(false);
                    mSizeList.add(size);
                }
                mSizeList.get(0).setChecked(true);
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

                mTxtQuantity.setText(String.valueOf(1));
                mTxtDescription.setText(mProduct.getDescription());
            }
        } else {

        }
    }

    @Override
    public void showShoppingBag(List<Product> productList) {
        mShoppingBag = productList;
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
        Size selectedSize = null;
        for (Size size : mSizeList) {
            if (size.isChecked()) {
                selectedSize = size;
                break;
            }
        }
        boolean isInStock = false;
        if (selectedSize != null) {
            for (ProductVariant variant : mProduct.getProductVariantList()) {
                if (variant.getId() == selectedSize.getId()) {
                    int quantity = Integer.parseInt(mTxtQuantity.getText().toString().trim());
                    int buyQuantity = variant.getBuyQuantity();
                    int extraQuantity = quantity - buyQuantity;
                    isInStock = checkQuantityInOrder(variant.getId(), extraQuantity);
                    if (isInStock) {
                        variant.setBuyQuantity(quantity);
                        variant.setSizeString(selectedSize.getName());
                        variant.setSelected(true);
                    }
                } else {
                    variant.setBuyQuantity(1);
                    variant.setSelected(false);
                }
            }
        }
        if (isInStock) {
            mPresenter.updateProduct(mProduct);
            finish();
        }

    }

    private void showOutOfStockDialog() {
        final Dialog dialog = new Dialog(EditOrderDetailActivity.this);
        LayoutInflater layoutInflater = EditOrderDetailActivity.this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_information, null);
        dialog.setContentView(view);

        TextView txtTitle = dialog.findViewById(R.id.text_view_dialog_title);
        TextView txtSubInfo = dialog.findViewById(R.id.text_view_sub_infor);
        View viewLine = dialog.findViewById(R.id.view_line);
        View viewLine2 = dialog.findViewById(R.id.view_line2);
        LinearLayout lnlOptions = dialog.findViewById(R.id.linear_layout_options);
        Button option1 = dialog.findViewById(R.id.button_num1);
        Button option2 = dialog.findViewById(R.id.button_num2);

        txtTitle.setVisibility(View.GONE);
        txtSubInfo.setText("Chúng tôi rất tiếc là sản phẩm này không còn đủ số lượng yêu cầu");
        viewLine.setVisibility(View.VISIBLE);
        viewLine2.setVisibility(View.GONE);
        lnlOptions.setVisibility(View.VISIBLE);
        option1.setText("Thử lại");
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        option2.setVisibility(View.GONE);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private boolean checkQuantityInOrder(int variantId, int extraQuantity) {
        boolean isInStock = true;
        int totalBuyQuantity = 0;
        int inStockQuantity = 0;
        for (Product product : mShoppingBag) {
            for (ProductVariant variant : product.getProductVariantList()) {
                if (variant.getId() == variantId) {
                    inStockQuantity = variant.getQuantity();
                    totalBuyQuantity += variant.getBuyQuantity();
                }
            }
        }
        totalBuyQuantity += extraQuantity;
        if (totalBuyQuantity > inStockQuantity) {
            isInStock = false;
            showOutOfStockDialog();
        }
        return isInStock;
    }

    private void clickToSubstract() {
        int quantity = Integer.parseInt(mTxtQuantity.getText().toString());
        if (quantity >= 2) {
            quantity -= 1;
        }
        mTxtQuantity.setText(String.valueOf(quantity));
        mTotal = calculateToTal();
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
