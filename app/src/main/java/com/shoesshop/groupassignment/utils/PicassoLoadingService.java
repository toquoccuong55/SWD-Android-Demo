package com.shoesshop.groupassignment.utils;

import android.widget.ImageView;

import com.shoesshop.groupassignment.R;
import com.squareup.picasso.Picasso;

import ss.com.bannerslider.ImageLoadingService;

public class PicassoLoadingService implements ImageLoadingService {

    public PicassoLoadingService() {
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        Picasso.get().load(url).into(imageView);
    }

    @Override
    public void loadImage(int resource, ImageView imageView) {
        Picasso.get().load(resource).into(imageView);
    }

    @Override
    public void loadImage(String url, int placeHolder, int errorDrawable, ImageView imageView) {
//        Picasso.get().load(url).placeholder(placeHolder).error(errorDrawable).into(imageView);
        if (url != null && !url.isEmpty()) {
            Picasso.get()
                    .load(url)
                    .placeholder(R.mipmap.ic_default_shoe)
                    .error(R.mipmap.ic_default_shoe)
                    .into(imageView);
        } else {
            imageView.setImageResource(R.mipmap.ic_default_shoe);
        }
    }
}
