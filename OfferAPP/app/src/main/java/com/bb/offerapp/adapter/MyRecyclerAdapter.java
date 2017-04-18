package com.bb.offerapp.adapter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.offerapp.R;
import com.bb.offerapp.activity.BodyTest;
import com.bb.offerapp.activity.FlashLight;
import com.bb.offerapp.activity.Gesture_1;
import com.bb.offerapp.activity.GridView_1;
import com.bb.offerapp.activity.Marquee;
import com.bb.offerapp.activity.MyView;
import com.bb.offerapp.activity.OfferAppMainActivity;
import com.bb.offerapp.activity.PressTest;
import com.bb.offerapp.activity.ProgressWebView;
import com.bb.offerapp.activity.Progressbar;
import com.bb.offerapp.activity.Toast_1;
import com.bb.offerapp.activity.cal;
import com.bb.offerapp.activity.wechat;
import com.bb.offerapp.bean.ItemInfo;

import java.util.List;

/**
 * MyRecyclerAdapter
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private List<ItemInfo> mItemInfoList;
    private Context context;

    //设置Item形状
    private boolean mIsSmall = false;

    //构造函数
    public MyRecyclerAdapter(List<ItemInfo> itemInfoList,Context context) {
        this.mItemInfoList = itemInfoList;
        this.context=context;
    }

    public void setSmallType(boolean isSmall) {
        this.mIsSmall = isSmall;
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        View item;//最外层子view

        ImageView mPortraitView;
        TextView mNickNameView;
        TextView mMottoView;

        public MyViewHolder(View itemView) {
            super(itemView);
            item = itemView;//将itemView赋给item拿去做监听
            mPortraitView = (ImageView) itemView.findViewById(R.id.iv_portrait);
            mNickNameView = (TextView) itemView.findViewById(R.id.tv_nickname);
            mMottoView = (TextView) itemView.findViewById(R.id.tv_motto);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if (mIsSmall) {
            itemView = inflater.inflate(R.layout.author_small_card_layout, parent, false);
        } else {
            itemView = inflater.inflate(R.layout.author_card_layout, parent, false);
        }
        final MyViewHolder viewHolder = new MyViewHolder(itemView);
        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                switch (position%13){
                    case 0:
                        Intent intent0 = new Intent(context, BodyTest.class);
                        context.startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(context, Progressbar.class);
                        context.startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(context, GridView_1.class);
                        context.startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(context, ProgressWebView.class);
                        context.startActivity(intent3);
                        break;
                    case 4:
                        Intent intent7 = new Intent(context, Gesture_1.class);
                        context.startActivity(intent7);
                        break;
                    case 5:
                        Intent intent8 = new Intent(context, Toast_1.class);
                        context.startActivity(intent8);
                        break;
                    case 6:
                        Intent intent10 = new Intent(context, cal.class);
                        context.startActivity(intent10);
                        break;
                    case 7:
                        Intent intent11 = new Intent(context, Marquee.class);
                        context.startActivity(intent11);
                        break;
                    case 8:
                        Intent intent13 = new Intent(context, wechat.class);
                        context.startActivity(intent13);
                        break;
                    case 9:
                        Intent intent14 = new Intent(context, PressTest.class);
                        context.startActivity(intent14);
                        break;
                    case 10:
                        Intent intent15 = new Intent(context, FlashLight.class);
                        context.startActivity(intent15);
                        break;
                    case 11:
                        sendNotification();
                        break;
                    case 12:
                        Intent intent16 = new Intent(context, MyView.class);
                        context.startActivity(intent16);
                        break;

                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        ItemInfo itemInfo = mItemInfoList.get(position);
        viewHolder.mPortraitView.setImageResource(itemInfo.getPortrait());
        viewHolder.mNickNameView.setText(itemInfo.getNickName());
        viewHolder.mMottoView.setText(itemInfo.getMotto());
    }

    @Override
    public int getItemCount() {
        if (mItemInfoList == null) {
            return 0;
        }
        return mItemInfoList.size();
    }




    //通知
    public void sendNotification() {
        NotificationManager manager;
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(context, OfferAppMainActivity.class);
        PendingIntent pintent = PendingIntent.getActivity(context, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置图标
        builder.setTicker("hello");//手机状态栏的提示；
        builder.setWhen(System.currentTimeMillis());//设置时间
        builder.setContentTitle("通知栏通知");//设置标题
        builder.setContentText("我来自NotificationDemo");//设置通知内容
        builder.setContentIntent(pintent);//点击后的意图
        builder.setDefaults(Notification.DEFAULT_ALL);//设置震动
        Notification notification = builder.build();//4.1以上
        manager.notify(1, notification);

    }
}
