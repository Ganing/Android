package com.bb.offerapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.offerapp.R;
import com.bb.offerapp.bean.ItemInfo;

import java.util.Collections;
import java.util.List;

/**
 *
 * MyRecyclerAdapter
 *
 * Created by bb on 2016/7/26.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    //    监听事件

    private List<ItemInfo> mItemInfoList;
    /**
     * item view 的类型是否是小类型的
     */
    private boolean mIsSmall = false;

    public MyRecyclerAdapter(List<ItemInfo> itemInfoList) {
        this.mItemInfoList = itemInfoList;
    }

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

        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }
        if(mOnItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
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



    /**
     * 移动Item
     *
     */
    public void moveItem(int fromPosition, int toPosition) {
        //做数据的交换
        if (fromPosition < toPosition) {
            for (int index = fromPosition; index < toPosition; index++) {
                Collections.swap(mItemInfoList, index, index + 1);
            }
        } else {
            for (int index = fromPosition; index > toPosition; index--) {
                Collections.swap(mItemInfoList, index, index - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * 滑动Item
     *
     */
    public void removeItem(int position) {
        mItemInfoList.remove(position);//删除数据
        notifyItemRemoved(position);
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


    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

}
