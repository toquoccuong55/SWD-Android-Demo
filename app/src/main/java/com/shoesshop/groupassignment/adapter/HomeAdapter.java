package com.shoesshop.groupassignment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.room.entity.Product;
import com.shoesshop.groupassignment.utils.CurrencyManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<Product> mProductList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public HomeAdapter(List<Product> mProductList, Context mContext) {
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
        View view = layoutInflater.inflate(R.layout.row_item_product, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Product product = mProductList.get(i);
        if (product.getImage() != null && !product.getImage().isEmpty()) {
            Picasso.get()
                    .load(product.getImage())
                    .placeholder(R.mipmap.ic_default_shoe)
                    .error(R.mipmap.ic_default_shoe)
                    .into(viewHolder.mImgProduct);
        } else {
            viewHolder.mImgProduct.setImageResource(R.mipmap.ic_shoes);
        }
        viewHolder.mTxtProductName.setText(product.getName());
        viewHolder.mTxtUnitPrice.setText(CurrencyManager.getPrice(product.getUnitPrice(), "đ"));
        viewHolder.mCardViewItem.setOnClickListener(new View.OnClickListener() {
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
        public ImageView mImgProduct;
        public TextView mTxtProductName, mTxtUnitPrice;
        public CardView mCardViewItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgProduct = itemView.findViewById(R.id.image_view_product);
            mTxtProductName = itemView.findViewById(R.id.text_view_product_name);
            mTxtUnitPrice = itemView.findViewById(R.id.text_view_unit_price);
            mCardViewItem = itemView.findViewById(R.id.card_view_item);
        }
    }
}
