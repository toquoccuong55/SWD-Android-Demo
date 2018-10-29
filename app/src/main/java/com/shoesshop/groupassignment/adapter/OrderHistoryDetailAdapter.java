package com.shoesshop.groupassignment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.model.OrderHistoryDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderHistoryDetailAdapter extends RecyclerView.Adapter<OrderHistoryDetailAdapter.ViewHolder> {
    private List<OrderHistoryDetail> mOrderHistoryDetailList;
    private Context mContext;

    public OrderHistoryDetailAdapter(List<OrderHistoryDetail> mOrderHistoryDetailList, Context mContext) {
        this.mOrderHistoryDetailList = mOrderHistoryDetailList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.row_item_order_history_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderHistoryDetail orderHistoryDetail = mOrderHistoryDetailList.get(position);
        if (orderHistoryDetail.getOrderDetailImage() != null && !orderHistoryDetail.getOrderDetailImage().isEmpty()) {
            Picasso.get()
                    .load(orderHistoryDetail.getOrderDetailImage())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.mImgOrderDetail);
        } else {
            holder.mImgOrderDetail.setImageResource(R.mipmap.ic_launcher);
        }
        holder.mTxtTitle.setText(orderHistoryDetail.getOrderDetailTitle());
        holder.mTxtSizeName.setText("Size " + orderHistoryDetail.getSizeName());
        holder.mTxtUnitPrice.setText(orderHistoryDetail.getUnitPrice() + " x");
        holder.mTxtQuantity.setText(orderHistoryDetail.getQuantity());

    }

    @Override
    public int getItemCount() {
        return mOrderHistoryDetailList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImgOrderDetail;
        public TextView mTxtTitle, mTxtSizeName, mTxtUnitPrice, mTxtQuantity;

        public ViewHolder(View itemView) {
            super(itemView);
            mImgOrderDetail = itemView.findViewById(R.id.image_view_order_history_detail);
            mTxtTitle = itemView.findViewById(R.id.text_view_title);
            mTxtSizeName = itemView.findViewById(R.id.text_view_size_name);
            mTxtUnitPrice = itemView.findViewById(R.id.text_view_unit_price);
            mTxtQuantity = itemView.findViewById(R.id.text_view_quantity);
        }
    }
}
