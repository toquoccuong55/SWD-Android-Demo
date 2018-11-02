package com.shoesshop.groupassignment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.model.Size;

import java.util.List;

public class SizeListAdapter extends RecyclerView.Adapter<SizeListAdapter.ViewHolder> {
    private List<Size> mSizeList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public SizeListAdapter(List<Size> mSizeList, Context mContext) {
        this.mSizeList = mSizeList;
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
        View view = layoutInflater.inflate(R.layout.row_item_size, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Size size = mSizeList.get(i);
        viewHolder.mTxtSizeName.setText(size.getName());
        viewHolder.mLnlSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.setOnItemClickListener(i);
            }
        });
        if (size.isChecked()) {
            viewHolder.mLnlSize.setBackground(mContext.getResources().getDrawable(R.drawable.background_item_size_on));
            viewHolder.mTxtSizeName.setTextColor(mContext.getResources().getColor(R.color.white));
        }else{
            viewHolder.mLnlSize.setBackground(mContext.getResources().getDrawable(R.drawable.background_item_size_off));
            viewHolder.mTxtSizeName.setTextColor(mContext.getResources().getColor(R.color.gray));
        }
    }

    @Override
    public int getItemCount() {
        return mSizeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mLnlSize;
        public TextView mTxtSizeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLnlSize = itemView.findViewById(R.id.linear_layout_color_item);
            mTxtSizeName = itemView.findViewById(R.id.text_view_size_name);
        }
    }
}
