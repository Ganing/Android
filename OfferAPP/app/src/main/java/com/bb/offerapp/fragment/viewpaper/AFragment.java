package com.bb.offerapp.fragment.viewpaper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.bb.offerapp.R;
import com.bb.offerapp.activity.BodyTest;
import com.bb.offerapp.activity.FlashLight;
import com.bb.offerapp.activity.Gesture_1;
import com.bb.offerapp.activity.GridView_1;
import com.bb.offerapp.activity.Marquee;
import com.bb.offerapp.activity.OfferAppMainActivity;
import com.bb.offerapp.activity.PressTest;
import com.bb.offerapp.activity.ProgressWebView;
import com.bb.offerapp.activity.Progressbar;
import com.bb.offerapp.activity.Toast_1;
import com.bb.offerapp.activity.cal;
import com.bb.offerapp.activity.wechat;
import com.bb.offerapp.adapter.MyRecyclerAdapter;
import com.bb.offerapp.adapter.ScaleInAnimationAdapter;
import com.bb.offerapp.animator.ScaleInAnimator;
import com.bb.offerapp.bean.ItemInfo;
import com.bb.offerapp.dialog.LWheelDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bb on 2016/11/18.
 */
public class AFragment extends Fragment {
    //声明第一页所用到的组件

    private NotificationManager manager;

    //RecyclerView
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mRecyclerAdapter;

    //RecyclerView动画
    private ScaleInAnimationAdapter scaleAdapter;

    //Item列表
    private List<ItemInfo> mItemInfoList;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initData();//初始化RecyclerView数据
        View view = inflater.inflate(R.layout.recycle_a_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_fragment);
        initView_RecyclerView();//初始化RecyclerView

        return view;
    }


    private void initData() {
        mItemInfoList = new ArrayList<>();
        for(int i=1 ;i<=10;i++) {
            ItemInfo itemInfo0 = new ItemInfo();
            itemInfo0.setMotto("Android基本组建的监听");
            itemInfo0.setNickName("身高体重测试项目");
            itemInfo0.setPortrait(R.mipmap.shengao);
            mItemInfoList.add(itemInfo0);

            ItemInfo itemInfo1 = new ItemInfo();
            itemInfo1.setMotto("ProgressBar的展示");
            itemInfo1.setNickName("进度条的使用");
            itemInfo1.setPortrait(R.mipmap.jindutiao);
            mItemInfoList.add(itemInfo1);

            ItemInfo itemInfo2 = new ItemInfo();
            itemInfo2.setMotto("GridView加载适配器、数据源的配置");
            itemInfo2.setNickName("手机菜单演示");
            itemInfo2.setPortrait(R.mipmap.caidan);
            mItemInfoList.add(itemInfo2);


            ItemInfo itemInfo3 = new ItemInfo();
            itemInfo3.setMotto("实现网页加载时进度条的监听、返回键的捕捉");
            itemInfo3.setNickName("带进度条的WebView");
            itemInfo3.setPortrait(R.mipmap.webview);
            mItemInfoList.add(itemInfo3);

            ItemInfo itemInfo4 = new ItemInfo();
            itemInfo4.setMotto("使用GestureOverlayView加载预设手势");
            itemInfo4.setNickName("自定义手势");
            itemInfo4.setPortrait(R.mipmap.shoushi);
            mItemInfoList.add(itemInfo4);

            ItemInfo itemInfo5 = new ItemInfo();
            itemInfo5.setMotto("自带及自定义Toast的对比");
            itemInfo5.setNickName("Toast演示");
            itemInfo5.setPortrait(R.mipmap.toast);
            mItemInfoList.add(itemInfo5);


            ItemInfo itemInfo6 = new ItemInfo();
            itemInfo6.setMotto("Lyout布局的设置、优化、计算逻辑的实现");
            itemInfo6.setNickName("计算器逻辑及布局");
            itemInfo6.setPortrait(R.mipmap.jisuanqi);
            mItemInfoList.add(itemInfo6);


            ItemInfo itemInfo7 = new ItemInfo();
            itemInfo7.setMotto("修改TextView实现上下左右滚动");
            itemInfo7.setNickName("可控速跑马灯");
            itemInfo7.setPortrait(R.mipmap.paomadeng);
            mItemInfoList.add(itemInfo7);


            ItemInfo itemInfo8 = new ItemInfo();
            itemInfo8.setMotto("自定义view,学习绘制、定义属性");
            itemInfo8.setNickName("时间选择器");
            itemInfo8.setPortrait(R.mipmap.shijian);
            mItemInfoList.add(itemInfo8);


            ItemInfo itemInfo9 = new ItemInfo();
            itemInfo9.setMotto("Fragment的事件的操作");
            itemInfo9.setNickName("微信布局");
            itemInfo9.setPortrait(R.mipmap.wechat);
            mItemInfoList.add(itemInfo9);

            ItemInfo itemInfo10 = new ItemInfo();
            itemInfo10.setMotto("Fragment增删替换等操作项目");
            itemInfo10.setNickName("压力测试项目");
            itemInfo10.setPortrait(R.mipmap.yali);
            mItemInfoList.add(itemInfo10);


            ItemInfo itemInfo11 = new ItemInfo();
            itemInfo11.setMotto("注册权限的配置、调用手机硬件的使用");
            itemInfo11.setNickName("调用闪光灯");
            itemInfo11.setPortrait(R.mipmap.shanguangdeng);
            mItemInfoList.add(itemInfo11);


            ItemInfo itemInfo12 = new ItemInfo();
            itemInfo12.setMotto("调用通知管理系统服务通知管理");
            itemInfo12.setNickName("发送一条通知");
            itemInfo12.setPortrait(R.mipmap.tongzhi);
            mItemInfoList.add(itemInfo12);

        }

    }

    private void initView_RecyclerView() {

        //设置动画： 将mRecyclerAdapter作为参数传入动画Adapter"scaleAdapter"，让mRecyclerView加载动画Adapter
        mRecyclerView.setItemAnimator(new ScaleInAnimator());
        mRecyclerAdapter = new MyRecyclerAdapter(mItemInfoList);
        scaleAdapter = new ScaleInAnimationAdapter(mRecyclerAdapter);
        scaleAdapter.setFirstOnly(false);
        scaleAdapter.setDuration(400);

        //设置线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerAdapter.setSmallType(false);
        mRecyclerView.setAdapter(scaleAdapter);

        //禁止嵌套滑动 可以让其由于惯性滑动
        mRecyclerView.setNestedScrollingEnabled(true);


        //单机点击事件
        mRecyclerAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                switch (position%13) {
                    case 0:
                        Intent intent0 = new Intent(getActivity(), BodyTest.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getActivity(), Progressbar.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getActivity(), GridView_1.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getActivity(), ProgressWebView.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent7 = new Intent(getActivity(), Gesture_1.class);
                        startActivity(intent7);
                        break;
                    case 5:
                        Intent intent8 = new Intent(getActivity(), Toast_1.class);
                        startActivity(intent8);
                        break;
                    case 6:
                        Intent intent10 = new Intent(getActivity(), cal.class);
                        startActivity(intent10);
                        break;
                    case 7:
                        Intent intent11 = new Intent(getActivity(), Marquee.class);
                        startActivity(intent11);
                        break;
                    case 8:
                        LWheel();
                        break;
                    case 9:
                        Intent intent13 = new Intent(getActivity(), wechat.class);
                        startActivity(intent13);
                        break;
                    case 10:
                        Intent intent14 = new Intent(getActivity(), PressTest.class);
                        startActivity(intent14);
                        break;
                    case 11:
                        Intent intent15 = new Intent(getActivity(), FlashLight.class);
                        startActivity(intent15);
                        break;
                    case 12:
                        sendNotification();
                        break;
                }
            }
        });
    }




    //方便在 主页面调用
    public MyRecyclerAdapter getmRecyclerAdapter() {
        return mRecyclerAdapter;
    }

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }


    public ScaleInAnimationAdapter getScaleAdapter() {
        return scaleAdapter;
    }



    //通知
    private void sendNotification() {
        manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(getActivity(), OfferAppMainActivity.class);
        PendingIntent pintent = PendingIntent.getActivity(getActivity(), 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(getActivity());
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


    //引用
    private void LWheel() {
        LWheelDialog dialog = new LWheelDialog(getActivity(), LWheelDialog.LWheelDialogType.ALL, null);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.dialogstyle_vertical);
        dialog.setCancelable(true);
        dialog.show();
        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.4);
        p.width = (int) (d.getWidth());
        dialog.onWindowAttributesChanged(p);

        window.setAttributes(p);
    }







}
