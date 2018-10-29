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
import com.shoesshop.groupassignment.model.OrderDetail;
import com.shoesshop.groupassignment.model.Product;
import com.shoesshop.groupassignment.utils.CurrencyManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {
    private List<OrderDetail> mOrderDetailList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public OrderDetailAdapter(List<OrderDetail> mOrderDetailList, Context mContext) {
        this.mOrderDetailList = mOrderDetailList;
        this.mContext = mContext;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void setOnItemClickListener(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.row_item_order_detail, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        OrderDetail orderDetail = mOrderDetailList.get(i);
        if (orderDetail.getPicUrl() != null && !orderDetail.getPicUrl().isEmpty()) {
            Picasso.get()
                    .load(orderDetail.getPicUrl())
                    .placeholder(R.mipmap.ic_default_shoe)
                    .error(R.mipmap.ic_default_shoe)
                    .into(viewHolder.mImgProduct);
        } else {
            viewHolder.mImgProduct.setImageResource(R.mipmap.ic_shoes);
        }
        viewHolder.mTxtProductName.setText(orderDetail.getName());
        viewHolder.mTxtPriceQuantity.setText(CurrencyManager.getPrice(orderDetail.getUnitPrice(), "") + " x " + orderDetail.getQuantity());
        viewHolder.mTxtSizeName.setText("Size " + orderDetail.getSizeName());
        viewHolder.mLnlOrderDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.setOnItemClickListener(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mOrderDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImgProduct;
        public TextView mTxtProductName, mTxtPriceQuantity, mTxtSizeName;
        public LinearLayout mLnlOrderDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgProduct = itemView.findViewById(R.id.image_view_product);
            mTxtProductName = itemView.findViewById(R.id.text_view_product_name);
            mTxtPriceQuantity = itemView.findViewById(R.id.text_view_price_quantity);
            mTxtSizeName = itemView.findViewById(R.id.text_view_size_name);
            mLnlOrderDetail = itemView.findViewById(R.id.linear_layout_order_detail);

        }
    }
}
