package com.shoesshop.groupassignment.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.shoesshop.groupassignment.utils.PicassoLoadingService;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLnlBack, mLnlWishlist;
    private Slider mSlider;
    private List<String> mImageList;
    private FlowLayout mFlowLayoutSize;
    private TextView mTxtProductName, mTxtPrice, mTxtDescription;
    private Button mBtnAddToCart;

    private RecyclerView mRecyclerViewRelatedProducts;
    private RelatedProductAdapter mRelatedProductAdapter;
    private List<String> mRelatedProductList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initalView();
        initialData();
        Slider.init(new PicassoLoadingService());
    }

    private void initalView() {
        mSlider = findViewById(R.id.slider_product_images);
        mLnlBack = findViewById(R.id.linear_layout_back);
        mLnlBack.setOnClickListener(this);
        mFlowLayoutSize = findViewById(R.id.flow_layout_list_size);
        mFlowLayoutSize.setOnClickListener(this);
        mLnlWishlist = findViewById(R.id.linear_layout_wishlist);
        mTxtPrice = findViewById(R.id.text_view_price);
        mTxtProductName = findViewById(R.id.text_view_product_name);
        mTxtDescription = findViewById(R.id.text_view_description);
        mRecyclerViewRelatedProducts = findViewById(R.id.recycler_view_related_products);
        mBtnAddToCart = findViewById(R.id.button_add_to_cart);
        mBtnAddToCart.setOnClickListener(this);

        mRecyclerViewRelatedProducts.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewRelatedProducts.setLayoutManager(layoutManager);

    }

    private void initialData() {
        addSizeLayoutToFlowLayout();

        mImageList = new ArrayList<>();
        mImageList.add("https://image.goat.com/150/attachments/product_template_pictures/images/010/140/774/original/189274_00.png.png");
        mImageList.add("https://image.goat.com/150/attachments/product_template_pictures/images/004/896/057/original/AH9110_023.png");
        mImageList.add("https://image.goat.com/150/attachments/product_template_pictures/images/014/770/352/original/AJ8_647380.png.png");
        mImageList.add("https://image.goat.com/150/attachments/product_template_pictures/images/000/763/688/original/HO13_MNJDLS_090.png");
        mImageList.add("https://image.goat.com/150/attachments/product_template_pictures/images/000/551/836/original/705317_305_.png");

        mSlider.setAdapter(new MainSliderAdapter(mImageList));

        mRelatedProductList = new ArrayList<>();
        mRelatedProductList.add("https://image.goat.com/150/attachments/product_template_pictures/images/004/896/057/original/AH9110_023.png");
        mRelatedProductList.add("https://image.goat.com/150/attachments/product_template_pictures/images/004/896/057/original/AH9110_023.png");
        mRelatedProductList.add("https://image.goat.com/150/attachments/product_template_pictures/images/004/896/057/original/AH9110_023.png");
        mRelatedProductList.add("https://image.goat.com/150/attachments/product_template_pictures/images/004/896/057/original/AH9110_023.png");
        mRelatedProductList.add("https://image.goat.com/150/attachments/product_template_pictures/images/004/896/057/original/AH9110_023.png");

        mRelatedProductAdapter = new RelatedProductAdapter(mRelatedProductList, ProductDetailActivity.this);
        mRecyclerViewRelatedProducts.setAdapter(mRelatedProductAdapter);
        mRelatedProductAdapter.setmOnItemClickListener(new RelatedProductAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(int position) {

            }
        });
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_back:
                finish();
                break;
            case R.id.linear_layout_wishlist:

                break;
        }
    }
}
