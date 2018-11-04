package com.shoesshop.groupassignment.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.adapter.RelatedProductAdapter;
import com.shoesshop.groupassignment.adapter.SizeListAdapter;
import com.shoesshop.groupassignment.room.entity.ProductVariant;
import com.shoesshop.groupassignment.presenter.ProductDetailPresenter;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.model.Size;
import com.shoesshop.groupassignment.utils.ConstantDataManager;
import com.shoesshop.groupassignment.utils.CurrencyManager;
import com.shoesshop.groupassignment.utils.GridSpacingItemDecoration;
import com.shoesshop.groupassignment.utils.PicassoLoadingService;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String CURRENCY = "đ";

    private LinearLayout mLnlBack, mLnlWishlist;
    private Slider mSlider;
    private List<String> mImageList;
    private FlowLayout mFlowLayoutSize;
    private TextView mTxtProductName, mTxtPrice, mTxtDescription, mTxtQuantity,
            mTxtDescrease, mTxtPlus;
    private Button mBtnAddToCart;

    private RecyclerView mRecyclerViewRelatedProducts;
    private RelatedProductAdapter mRelatedProductAdapter;
    private List<String> mRelatedProductList;

    private RecyclerView mRcvSizeList;
    private List<Size> mSizeList;
    private SizeListAdapter mSizeListAdapter;

    private Product mProduct;
    private ProductDetailPresenter mProductDetailPresenter;

    private double mTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
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
        mLnlWishlist = findViewById(R.id.linear_layout_wishlist);
        mLnlWishlist.setOnClickListener(this);
        mTxtPrice = findViewById(R.id.text_view_price);
        mTxtProductName = findViewById(R.id.text_view_product_name);
        mTxtDescription = findViewById(R.id.text_view_description);
        mRecyclerViewRelatedProducts = findViewById(R.id.recycler_view_related_products);
        mBtnAddToCart = findViewById(R.id.button_add_to_cart);
        mBtnAddToCart.setOnClickListener(this);
        mTxtQuantity = findViewById(R.id.text_view_quantity);
        mTxtDescrease = findViewById(R.id.text_view_substract);
        mTxtDescrease.setOnClickListener(this);
        mTxtPlus = findViewById(R.id.text_view_plus);
        mTxtPlus.setOnClickListener(this);

        mRcvSizeList = findViewById(R.id.recycler_view_list_size);
        mRcvSizeList.setHasFixedSize(true);
        int numberOfColumn = calculateNumberOfColumns(ProductDetailActivity.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ProductDetailActivity.this,
                numberOfColumn);
        mRcvSizeList.setLayoutManager(gridLayoutManager);
        int spacing = getResources().getDimensionPixelSize(R.dimen.dp10);
        mRcvSizeList.addItemDecoration(new GridSpacingItemDecoration(numberOfColumn, spacing, true));


//        mRecyclerViewRelatedProducts.setHasFixedSize(true);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
//        mRecyclerViewRelatedProducts.setLayoutManager(layoutManager);

    }

    private void initialData() {
        mProductDetailPresenter = new ProductDetailPresenter(getApplication());


        mSizeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Size size = new Size();
            size.setId(i);
            size.setName(String.valueOf(i));
            size.setChecked(false);
            mSizeList.add(size);
        }
        mSizeListAdapter = new SizeListAdapter(mSizeList, ProductDetailActivity.this);
        mRcvSizeList.setAdapter(mSizeListAdapter);
        mSizeListAdapter.setmOnItemClickListener(new SizeListAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(int position) {
                for (Size size : mSizeList) {
                    size.setChecked(false);
                }
                mSizeList.get(position).setChecked(true);
                mSizeListAdapter.notifyDataSetChanged();
//                mTotal = calculateToTal();
//                mTxtPrice.setText(CurrencyManager.getPrice(mTotal, CURRENCY));
                mBtnAddToCart.setVisibility(View.VISIBLE);
            }
        });

//        if (mProduct != null) {
//            if (mProduct.isFavorite()) {
//                mLnlWishlist.setBackground(getResources().getDrawable(R.drawable.background_button_wishlist_on));
//            } else {
//                mLnlWishlist.setBackground(getResources().getDrawable(R.drawable.background_button_wishlist_off));
//            }
//
//            mImageList = mProduct.getProductVariantList().get(0).getPicURLList();
//            mSlider.setAdapter(new MainSliderAdapter(mImageList));
//            mTxtProductName.setText(mProduct.getName());
//            mTxtPrice.setText(CurrencyManager.getPrice(mProduct.getUnitPrice(), "đ"));
//
//            mSizeList = new ArrayList<>();
//            for (ProductVariant productVariant : mProduct.getProductVariantList()) {
//                Size size = new Size();
//                size.setId(productVariant.getId());
//                size.setName(productVariant.getName());
//                mSizeList.add(size);
//            }
//            mSizeListAdapter = new SizeListAdapter(mSizeList, ProductDetailActivity.this);
//            mRcvSizeList.setAdapter(mSizeListAdapter);
//            mSizeListAdapter.setmOnItemClickListener(new SizeListAdapter.OnItemClickListener() {
//                @Override
//                public void setOnItemClickListener(int position) {
//                    for (Size size : mSizeList) {
//                        size.setChecked(false);
//                    }
//                    mSizeList.get(position).setChecked(true);
//                    mSizeListAdapter.notifyDataSetChanged();
//                }
//            });
//
//            mTxtQuantity.setText(String.valueOf(1));
//            mTxtDescription.setText(mProduct.getDescription());
//
//        }
    }

    private int calculateNumberOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 80);
        return noOfColumns;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_back:
                finish();
                break;
            case R.id.linear_layout_wishlist:
                clickToFavorite();
                break;
            case R.id.text_view_substract:
                clickToSubstract();
                break;
            case R.id.text_view_plus:
                clickToPlus();
                break;
            case R.id.button_add_to_cart:
                clickToButtonAddToCart();
                break;
        }
    }

    private void clickToFavorite() {
        if (mProduct.isFavorite()) {
            mLnlWishlist.setBackground(getResources().getDrawable(R.drawable.background_button_wishlist_off));
            mProduct.setFavorite(false);
            mProductDetailPresenter.deleteFavorite(mProduct);
        } else {
            mLnlWishlist.setBackground(getResources().getDrawable(R.drawable.background_button_wishlist_on));
            mProduct.setFavorite(true);
            mProductDetailPresenter.addFavorite(mProduct);
        }
    }

    private void clickToSubstract() {
        int quantity = Integer.parseInt((String) mTxtQuantity.getText().toString());
        if (quantity >= 2) {
            quantity -= 1;
        }
        mTxtQuantity.setText(String.valueOf(quantity));
        mTxtPrice.setText(CurrencyManager.getPrice(mTotal, CURRENCY));
    }

    private void clickToPlus() {
        int quantity = Integer.parseInt((String) mTxtQuantity.getText().toString());
        quantity += 1;
        mTxtQuantity.setText(String.valueOf(quantity));
        mTotal = calculateToTal();
        mTxtPrice.setText(CurrencyManager.getPrice(mTotal, CURRENCY));
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

    private void clickToButtonAddToCart() {
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
        HomeActivity.mShoppingBag.add(mProduct);
        mProductDetailPresenter.addProduct(mProduct);
    }

    public static void intentToProductDetailActivitiy(Activity activity) {
        Intent intent = new Intent(activity, ProductDetailActivity.class);
        activity.startActivity(intent);
    }

}
