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
import com.shoesshop.groupassignment.model.OrderHistory;
import com.shoesshop.groupassignment.utils.ConstantDataManager;
import com.shoesshop.groupassignment.utils.CurrencyManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {
    private List<OrderHistory> mOrderHistoryList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public OrderHistoryAdapter(List<OrderHistory> mOrderHistoryList, Context mContext) {
        this.mOrderHistoryList = mOrderHistoryList;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.row_item_order_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        OrderHistory orderHistory = mOrderHistoryList.get(position);
        holder.mTxtOrderId.setText("Order ID #" + orderHistory.getOrderID());
        holder.mTxtOrderTime.setText("Order Time : " + orderHistory.getOrderTime());
        holder.mTxtOrderTotal.setText(CurrencyManager.getPrice(orderHistory.getPaymentAmount(), "đ"));
//        if (orderHistory.getOrderDetailImage() != null && !orderHistory.getOrderDetailImage().isEmpty()) {
//            Picasso.get()
//                    .load(orderHistory.getOrderDetailImage())
//                    .placeholder(R.mipmap.ic_launcher)
//                    .error(R.mipmap.ic_launcher)
//                    .into(holder.mImgOrderDetail);
//        } else {
//            holder.mImgOrderDetail.setImageResource(R.mipmap.ic_launcher);
//        }
//        holder.mtxtOrderDetailName.setText(orderHistory.getOrderDetailName());
//        holder.mTxtOrderDetailPrice.setText(orderHistory.getUnitPriceQuantity());
        switch (orderHistory.getOrderStatus()) {
            case 0:
                holder.mTxtOrderStatus.setText("Đang chờ comfirm");
                break;
            case 1:
                holder.mTxtOrderStatus.setText("Đã thanh toán");
                break;
            case 2:
                holder.mTxtOrderStatus.setText("Đơn hàng bị hủy");
                break;
        }
//        holder.mTxtOrderStatus.setText(orderHistory.getOrderStatus());
        holder.mLnlViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.setOnItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mOrderHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTxtOrderId, mTxtOrderTime, mTxtOrderTotal,
                mtxtOrderDetailName, mTxtOrderDetailPrice, mTxtOrderStatus;
        public ImageView mImgOrderDetail;
        public LinearLayout mLnlViewDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            mTxtOrderId = itemView.findViewById(R.id.text_view_order_id);
            mTxtOrderTime = itemView.findViewById(R.id.text_view_order_time);
            mTxtOrderTotal = itemView.findViewById(R.id.text_view_order_total);
//            mImgOrderDetail = itemView.findViewById(R.id.image_view_order_detail);
//            mtxtOrderDetailName = itemView.findViewById(R.id.text_view_order_detail_name);
//            mTxtOrderDetailPrice = itemView.findViewById(R.id.text_view_order_detail_price);
            mTxtOrderStatus = itemView.findViewById(R.id.text_view_order_status);
            mLnlViewDetail = itemView.findViewById(R.id.linear_layout_view_detail);


        }
    }
}
