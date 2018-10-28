package com.shoesshop.groupassignment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shoesshop.groupassignment.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RelatedProductAdapter extends RecyclerView.Adapter<RelatedProductAdapter.ViewHolder> {
    private List<String> mProductList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public RelatedProductAdapter(List<String> mProductList, Context mContext) {
        this.mProductList = mProductList;
        this.mContext = mContext;
    }


    public interface OnItemClickListener {
        void setOnItemClickListener(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.row_item_related_product, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String productImage = mProductList.get(i);
        if (productImage != null && !productImage.isEmpty()) {
            Picasso.get()
                    .load(productImage)
                    .placeholder(R.mipmap.ic_default_shoe)
                    .error(R.mipmap.ic_default_shoe)
                    .into(viewHolder.mImgRelatedProduct);
        } else {
            viewHolder.mImgRelatedProduct.setImageResource(R.mipmap.ic_shoes);
        }
        viewHolder.mLnlRelatedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.setOnItemClickListener(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImgRelatedProduct;
        public LinearLayout mLnlRelatedProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgRelatedProduct = itemView.findViewById(R.id.image_view_related_product);
            mLnlRelatedProduct = itemView.findViewById(R.id.linear_layout_related_product);
        }
    }
}
