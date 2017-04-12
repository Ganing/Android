package com.bb.offerapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.offerapp.R;
import com.bb.offerapp.bean.ItemInfo;

import java.util.List;

/**
 * MyRecyclerAdapter
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private List<ItemInfo> mItemInfoList;


    //构造函数
    public MyRecyclerAdapter(List<ItemInfo> itemInfoList) {
        this.mItemInfoList = itemInfoList;
    }


    //    监听事件
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    //设置Item形状
    private boolean mIsSmall = false;
    public void setSmallType(boolean isSmall) {
        this.mIsSmall = isSmall;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = null;
        if (mIsSmall) {
            itemView = inflater.inflate(R.layout.author_small_card_layout, parent, false);
        } else {
            itemView = inflater.inflate(R.layout.author_card_layout, parent, false);
        }
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ItemInfo itemInfo = mItemInfoList.get(position);
        holder.mPortraitView.setImageResource(itemInfo.getPortrait());
        holder.mNickNameView.setText(itemInfo.getNickName());
        holder.mMottoView.setText(itemInfo.getMotto());

        if (mOnItemClickListener != null) {
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mItemInfoList == null) {
            return 0;
        }
        return mItemInfoList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mPortraitView;
        TextView mNickNameView;
        TextView mMottoView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mPortraitView = (ImageView) itemView.findViewById(R.id.iv_portrait);
            mNickNameView = (TextView) itemView.findViewById(R.id.tv_nickname);
            mMottoView = (TextView) itemView.findViewById(R.id.tv_motto);

        }
    }

}
