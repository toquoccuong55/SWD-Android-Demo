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
import com.shoesshop.groupassignment.adapter.RelatedProductAdapter;
import com.shoesshop.groupassignment.adapter.SizeListAdapter;
import com.shoesshop.groupassignment.room.entity.ProductVariant;
import com.shoesshop.groupassignment.presenter.ProductDetailPresenter;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.model.Size;
import com.shoesshop.groupassignment.room.entity.Wishlist;
import com.shoesshop.groupassignment.utils.ConstantDataManager;
import com.shoesshop.groupassignment.utils.CurrencyManager;
import com.shoesshop.groupassignment.utils.GridSpacingItemDecoration;
import com.shoesshop.groupassignment.utils.PicassoLoadingService;
import com.shoesshop.groupassignment.view.ProductDetailView;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener, ProductDetailView {
    public static final String CURRENCY = "đ";

    private LinearLayout mLnlBack, mLnlWishlist;
    private Slider mSlider;
    private List<String> mImageList;
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
    private List<Wishlist> mWishList;
    private List<Product> mShoppingBag;

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

    private int calculateNumberOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 80);
        return noOfColumns;
    }

    private void initialData() {
        mProductDetailPresenter = new ProductDetailPresenter(getApplication(), ProductDetailActivity.this);
        mProductDetailPresenter.getShoppingBag();
    }

    @Override
    public void showShoppingBag(List<Product> productList) {
        mShoppingBag = productList;

        if (mProduct != null) {
            mProductDetailPresenter.getWishList();

            mImageList = mProduct.getProductVariantList().get(0).getPicURLList();
            if (mImageList.isEmpty()) {
                mImageList.add("https://img.icons8.com/android/1600/trainers.png");
                mImageList.add("https://img.icons8.com/android/1600/trainers.png");
                mImageList.add("https://img.icons8.com/android/1600/trainers.png");
            }
            mSlider.setAdapter(new MainSliderAdapter(mImageList));
            mTxtProductName.setText(mProduct.getName());
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
                    mTotal = calculateToTal();
                    mTxtPrice.setText(CurrencyManager.getPrice(mTotal, ConstantDataManager.CURRENCY));
                }
            });

            mTxtQuantity.setText(String.valueOf(1));
            mTxtDescription.setText(mProduct.getDescription());

        }
    }

    @Override
    public void showWishList(List<Wishlist> wishlists) {
        mWishList = wishlists;
        if (mWishList == null || mWishList.isEmpty()) {

        } else {
            for (Wishlist wishlist : mWishList) {
                if (mProduct.getId() == wishlist.getId()) {
                    mProduct.setFavorite(true);
                }
            }
            if (mProduct.isFavorite()) {
                mLnlWishlist.setBackground(getResources().getDrawable(R.drawable.background_button_wishlist_on));
            } else {
                mLnlWishlist.setBackground(getResources().getDrawable(R.drawable.background_button_wishlist_off));
            }
        }
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
            Wishlist wishlist = convertProductToWishList(mProduct);
            mProductDetailPresenter.deleteWishList(wishlist);
        } else {
            mLnlWishlist.setBackground(getResources().getDrawable(R.drawable.background_button_wishlist_on));
            mProduct.setFavorite(true);
            Wishlist wishlist = convertProductToWishList(mProduct);
            mProductDetailPresenter.addWishList(wishlist);
        }
    }

    private Wishlist convertProductToWishList(Product product) {
        Wishlist wishlist = new Wishlist();

        wishlist.setId(product.getId());
        wishlist.setName(product.getName());
        wishlist.setUnitPrice(product.getUnitPrice());
        wishlist.setImage(product.getImage());
        wishlist.setDescription(product.getDescription());
        wishlist.setFavorite(product.isFavorite());
        wishlist.setProductVariantList(product.getProductVariantList());

        return wishlist;
    }

    private void clickToSubstract() {
        int quantity = Integer.parseInt((String) mTxtQuantity.getText().toString());
        if (quantity >= 2) {
            quantity -= 1;
        }
        mTxtQuantity.setText(String.valueOf(quantity));
        mTotal = calculateToTal();
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
        boolean isInStock = true;
        if (selectedID != 0) {
            for (ProductVariant variant : mProduct.getProductVariantList()) {
                if (variant.getId() == selectedID) {
                    int quantity = Integer.parseInt(mTxtQuantity.getText().toString().trim());
                    if (mShoppingBag == null || mShoppingBag.isEmpty()) {
                        if (quantity > variant.getQuantity()) {
                            showOutOfDetailStockDialog(variant.getQuantity());
                            isInStock = false;
                        }
                    } else {
                        boolean isExisted = isExistedInShoppingBag(variant.getId());
                        if (isExisted) {
                            isInStock = checkQuantityInOrder(variant.getId(), quantity);
                        } else {
                            if (quantity > variant.getQuantity()) {
                                isInStock = false;
                            }
                        }
                        if (isInStock == false) {
                            showOutOfStockDialog();
                        }
                    }
                    if (isInStock) {
                        variant.setBuyQuantity(quantity);
                        variant.setSelected(true);
                    }
                }
            }
        }

        if (isInStock) {
            Product product = null;
            try {
                product = (Product) mProduct.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            if (product != null) {
                mProductDetailPresenter.addProduct(product);
            } else {
                mProductDetailPresenter.addProduct(mProduct);
            }
            finish();
        }
    }

    private boolean isExistedInShoppingBag(int variantId) {
        boolean isExisted = false;
        for (Product product : mShoppingBag) {
            for (ProductVariant variant : product.getProductVariantList()) {
                if (variant.getId() == variantId && variant.isSelected() == true) {
                    isExisted = true;
                }
            }
        }
        return isExisted;
    }

    private boolean checkQuantityInOrder(int variantId, int quantity) {
        boolean isInStock = true;
        int totalBuyQuantity = 0;
        int inStockQuantity = 0;
        boolean isExisted = false;
        for (Product product : mShoppingBag) {
            for (ProductVariant variant : product.getProductVariantList()) {
                if (variant.getId() == variantId && variant.isSelected() == true) {
                    inStockQuantity = variant.getQuantity();
                    totalBuyQuantity += variant.getBuyQuantity();
                    isExisted = true;
                }
            }
        }
        totalBuyQuantity += quantity;
        if (isExisted) {
            if (totalBuyQuantity > inStockQuantity) {
                isInStock = false;
            }
        }
        return isInStock;
    }

    private void showOutOfDetailStockDialog(int quantity) {
        final Dialog dialog = new Dialog(ProductDetailActivity.this);
        LayoutInflater layoutInflater = ProductDetailActivity.this.getLayoutInflater();
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
        if (quantity <= 0) {
            txtSubInfo.setText("Chúng tôi rất tiếc, sản phẩm này đã hết hàng.");
        } else {
            txtSubInfo.setText("Chúng tôi rất tiếc, sản phẩm này chỉ còn lại số lượng là " + quantity);
        }
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


    private void showOutOfStockDialog() {
        final Dialog dialog = new Dialog(ProductDetailActivity.this);
        LayoutInflater layoutInflater = ProductDetailActivity.this.getLayoutInflater();
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
        txtSubInfo.setText("Chúng tôi rất tiếc, sản phẩm này không còn đủ số lượng như yêu cầu");
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

}
