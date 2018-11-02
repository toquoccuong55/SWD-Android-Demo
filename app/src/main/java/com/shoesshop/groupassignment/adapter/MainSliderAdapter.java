package com.shoesshop.groupassignment.adapter;

import com.shoesshop.groupassignment.R;

import java.util.List;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {
    List<String> productImagesList;

    @Override
    public int getItemCount() {
        return productImagesList.size();
    }

    public MainSliderAdapter(List<String> productImagesList) {
        this.productImagesList = productImagesList;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {
        imageSlideViewHolder.bindImageSlide(productImagesList.get(position), R.mipmap.ic_shoes, R.mipmap.ic_shoes);
    }
}
