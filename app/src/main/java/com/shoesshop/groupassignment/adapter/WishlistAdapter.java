package com.shoesshop.groupassignment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.room.entity.Wishlist;
import com.shoesshop.groupassignment.utils.ConstantManager;
import com.shoesshop.groupassignment.utils.CurrencyManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {
    private List<Wishlist> mWishlist;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public WishlistAdapter(List<Wishlist> mWishlist, Context mContext) {
        this.mWishlist = mWishlist;
        this.mContext = mContext;
    }

    public interface OnItemClickListener {
        void setOnItemClickListener(int position);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.row_item_wishlist, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Wishlist wishlist = mWishlist.get(i);
        if (wishlist.getImageList() != null && !wishlist.getImageList().isEmpty()) {
            Picasso.get()
                    .load(wishlist.getImageList().get(0))
                    .placeholder(R.mipmap.ic_default_shoe)
                    .error(R.mipmap.ic_default_shoe)
                    .into(viewHolder.mImgProduct);
        } else {
            viewHolder.mImgProduct.setImageResource(R.mipmap.ic_shoes);
        }
        viewHolder.mTxtProductName.setText(wishlist.getName());
        viewHolder.mTxtUnitPrice.setText(CurrencyManager.getPrice(wishlist.getProductVariantList().get(0).getUnitPrice(),
                ConstantManager.CURRENCY));
        viewHolder.mLnlWishlistItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.setOnItemClickListener(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWishlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImgProduct;
        public TextView mTxtProductName, mTxtUnitPrice;
        public LinearLayout mLnlWishlistItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgProduct = itemView.findViewById(R.id.image_view_product);
            mTxtProductName = itemView.findViewById(R.id.text_view_product_name);
            mTxtUnitPrice = itemView.findViewById(R.id.text_view_unit_price);
            mLnlWishlistItem = itemView.findViewById(R.id.linear_layout_wishlist_item);

        }
    }
}
