package com.bb.offerapp.fragment.viewpaper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.bb.offerapp.activity.OfferAppMainActivity;
import com.bb.offerapp.R;

import com.bb.offerapp.activity.BodyTest;
import com.bb.offerapp.activity.FlashLight;
import com.bb.offerapp.activity.Gallery_1;
import com.bb.offerapp.activity.Gesture_1;
import com.bb.offerapp.activity.GridView_1;
import com.bb.offerapp.activity.Marquee;
import com.bb.offerapp.activity.MsgActivity;
import com.bb.offerapp.activity.MusicPlayer;
import com.bb.offerapp.activity.PicPlayer;
import com.bb.offerapp.activity.PressTest;
import com.bb.offerapp.activity.ProgressWebView;
import com.bb.offerapp.activity.Progressbar;
import com.bb.offerapp.activity.TableDemoActivity;
import com.bb.offerapp.activity.Toast_1;
import com.bb.offerapp.activity.cal;
import com.bb.offerapp.activity.wechat;
import com.bb.offerapp.adapter.MyRecyclerAdapter;
import com.bb.offerapp.adapter.ScaleInAnimationAdapter;
import com.bb.offerapp.animator.ScaleInAnimator;
import com.bb.offerapp.bean.ItemInfo;
import com.bb.offerapp.dialog.LWheelDialog;
import com.bb.offerapp.util.ActionItem;
import com.bb.offerapp.util.QuickAction;
import com.bb.offerapp.view.CatLoadingView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bb on 2016/11/18.
 */
public class AFragment extends Fragment {
    //声明第一页所用到的组件

    private NotificationManager manager;


    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mRecyclerAdapter;


    private ScaleInAnimationAdapter scaleAdapter;

    private List<ItemInfo> mItemInfoList;

    private CatLoadingView mView;

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
            itemInfo2.setMotto("Gallery、ImageSwitcher控件的结合");
            itemInfo2.setNickName("仿制滑动画廊");
            itemInfo2.setPortrait(R.mipmap.hualang);
            mItemInfoList.add(itemInfo2);

            ItemInfo itemInfo3 = new ItemInfo();
            itemInfo3.setMotto("加载Viewpager实现图片轮播效果");
            itemInfo3.setNickName("图片轮播");
            itemInfo3.setPortrait(R.mipmap.lunbo);
            mItemInfoList.add(itemInfo3);


            ItemInfo itemInfo4 = new ItemInfo();
            itemInfo4.setMotto("GridView加载适配器、数据源的配置");
            itemInfo4.setNickName("手机菜单演示");
            itemInfo4.setPortrait(R.mipmap.caidan);
            mItemInfoList.add(itemInfo4);

            ItemInfo itemInfo5 = new ItemInfo();
            itemInfo5.setMotto("利用listview实现聊天发送消息的效果");
            itemInfo5.setNickName("聊天窗");
            itemInfo5.setPortrait(R.mipmap.liaotian);
            mItemInfoList.add(itemInfo5);

            ItemInfo itemInfo6 = new ItemInfo();
            itemInfo6.setMotto("实现网页加载时进度条的监听、返回键的捕捉");
            itemInfo6.setNickName("带进度条的WebView");
            itemInfo6.setPortrait(R.mipmap.webview);
            mItemInfoList.add(itemInfo6);

            ItemInfo itemInfo7 = new ItemInfo();
            itemInfo7.setMotto("使用GestureOverlayView加载预设手势");
            itemInfo7.setNickName("自定义手势");
            itemInfo7.setPortrait(R.mipmap.shoushi);
            mItemInfoList.add(itemInfo7);

            ItemInfo itemInfo8 = new ItemInfo();
            itemInfo8.setMotto("自带及自定义Toast的对比");
            itemInfo8.setNickName("Toast演示");
            itemInfo8.setPortrait(R.mipmap.toast);
            mItemInfoList.add(itemInfo8);


            ItemInfo itemInfo9 = new ItemInfo();
            itemInfo9.setMotto("Lyout布局的设置、优化");
            itemInfo9.setNickName("课程表布局");
            itemInfo9.setPortrait(R.mipmap.kechengbiao);
            mItemInfoList.add(itemInfo9);

            ItemInfo itemInfo10 = new ItemInfo();
            itemInfo10.setMotto("Lyout布局的设置、优化、计算逻辑的实现");
            itemInfo10.setNickName("计算器逻辑及布局");
            itemInfo10.setPortrait(R.mipmap.jisuanqi);
            mItemInfoList.add(itemInfo10);


            ItemInfo itemInfo11 = new ItemInfo();
            itemInfo11.setMotto("修改TextView实现上下左右滚动");
            itemInfo11.setNickName("可控速跑马灯");
            itemInfo11.setPortrait(R.mipmap.paomadeng);
            mItemInfoList.add(itemInfo11);


            ItemInfo itemInfo12 = new ItemInfo();
            itemInfo12.setMotto("完全自定义view,学习绘制、定义属性");
            itemInfo12.setNickName("时间选择器");
            itemInfo12.setPortrait(R.mipmap.shijian);
            mItemInfoList.add(itemInfo12);


            ItemInfo itemInfo13 = new ItemInfo();
            itemInfo13.setMotto("Fragment的事件的操作");
            itemInfo13.setNickName("微信布局");
            itemInfo13.setPortrait(R.mipmap.wechat);
            mItemInfoList.add(itemInfo13);

            ItemInfo itemInfo14 = new ItemInfo();
            itemInfo14.setMotto("Fragment增删替换等操作项目");
            itemInfo14.setNickName("压力测试项目");
            itemInfo14.setPortrait(R.mipmap.yali);
            mItemInfoList.add(itemInfo14);


            ItemInfo itemInfo15 = new ItemInfo();
            itemInfo15.setMotto("注册权限的配置、调用手机硬件的使用");
            itemInfo15.setNickName("调用闪光灯");
            itemInfo15.setPortrait(R.mipmap.shanguangdeng);
            mItemInfoList.add(itemInfo15);


            ItemInfo itemInfo16 = new ItemInfo();
            itemInfo16.setMotto("调用通知管理系统服务通知管理");
            itemInfo16.setNickName("发送一条通知");
            itemInfo16.setPortrait(R.mipmap.tongzhi);
            mItemInfoList.add(itemInfo16);

            ItemInfo itemInfo17 = new ItemInfo();
            itemInfo17.setMotto("AudioManager.STREAM_MUSIC、MediaPlayer");
            itemInfo17.setNickName("播放预设文件");
            itemInfo17.setPortrait(R.mipmap.music);
            mItemInfoList.add(itemInfo17);


            ItemInfo itemInfo18 = new ItemInfo();
            itemInfo18.setMotto("继承DialogFragment实现动态效果");
            itemInfo18.setNickName("小猫加载动画");
            itemInfo18.setPortrait(R.mipmap.xiaomao);
            mItemInfoList.add(itemInfo18);

            ItemInfo itemInfo19 = new ItemInfo();
            itemInfo19.setMotto("WINDOW_SERVICE设计(引用项目)");
            itemInfo19.setNickName("点击工具列表项");
            itemInfo19.setPortrait(R.mipmap.gongju);
            mItemInfoList.add(itemInfo19);
        }

    }

    private void initView_RecyclerView() {

        mRecyclerView.setItemAnimator(new ScaleInAnimator());

        mRecyclerAdapter = new MyRecyclerAdapter(mItemInfoList);
        scaleAdapter = new ScaleInAnimationAdapter(mRecyclerAdapter);
        scaleAdapter.setFirstOnly(false);
        scaleAdapter.setDuration(400);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerAdapter.setSmallType(false);
        mRecyclerView.setAdapter(scaleAdapter);

        //        单机点击时间
        mRecyclerAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                switch (position%20) {
                    case 0:
                        Intent intent0 = new Intent(getActivity(), BodyTest.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getActivity(), Progressbar.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getActivity(), Gallery_1.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getActivity(), PicPlayer.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(getActivity(), GridView_1.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(getActivity(), MsgActivity.class);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(getActivity(), ProgressWebView.class);
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent(getActivity(), Gesture_1.class);
                        startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8 = new Intent(getActivity(), Toast_1.class);
                        startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9 = new Intent(getActivity(), TableDemoActivity.class);
                        startActivity(intent9);
                        break;
                    case 10:
                        Intent intent10 = new Intent(getActivity(), cal.class);
                        startActivity(intent10);
                        break;
                    case 11:
                        Intent intent11 = new Intent(getActivity(), Marquee.class);
                        startActivity(intent11);
                        break;
                    case 12:
                        LWheel();
                        break;
                    case 13:
                        Intent intent13 = new Intent(getActivity(), wechat.class);
                        startActivity(intent13);
                        break;
                    case 14:
                        Intent intent14 = new Intent(getActivity(), PressTest.class);
                        startActivity(intent14);
                        break;

                    case 15:
                        Intent intent15 = new Intent(getActivity(), FlashLight.class);
                        startActivity(intent15);
                        break;

                    case 16:
                        sendNotification();
                        break;
                    case 17:
                        Intent intent17 = new Intent(getActivity(), MusicPlayer.class);
                        startActivity(intent17);
                        break;
                    case 18:
                        CatLoadingView mView;
                        mView = new CatLoadingView();
                        mView.show(getActivity().getSupportFragmentManager(), "");
                        break;
                    case 19:
                        init_tools(view);
                        break;

                }
            }
        });
        //        长按点击时间
        mRecyclerAdapter.setOnItemLongClickListener(new MyRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getActivity(), "长按可移动", Toast.LENGTH_SHORT).show();
            }
        });


        //ItemTouchHelper 用于实现 RecyclerView Item 拖曳效果的类
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //actionState : action状态类型，有三类 ACTION_STATE_DRAG （拖曳），ACTION_STATE_SWIPE（滑动），ACTION_STATE_IDLE（静止）
                int dragFlags = makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.UP | ItemTouchHelper.DOWN
                        | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);//支持上下左右的拖曳
                int swipeFlags = makeMovementFlags(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);//表示支持左右的滑动
                return makeMovementFlags(dragFlags, 0);//直接返回0表示不支持拖曳和滑动
            }

            /**
             * @param viewHolder 拖动的Item
             * @param target 放置Item的目标位置
             * @return
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();//要拖曳的位置
                int toPosition = target.getAdapterPosition();//要放置的目标位置
                mRecyclerAdapter.moveItem(fromPosition, toPosition);
                return true;
            }

            /**
             * @param viewHolder 滑动移除的Item
             * @param direction
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();//获取要滑动删除的Item位置
                mRecyclerAdapter.removeItem(position);
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return super.isLongPressDragEnabled();//不支持长按拖曳效果直接返回false
            }


            @Override
            public boolean isItemViewSwipeEnabled() {
                return false;//不支持滑动效果直接返回false
            }
        });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        //很重要，禁止嵌套滑动 可以让其由于惯性滑动
        mRecyclerView.setNestedScrollingEnabled(true);

    }

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


    public MyRecyclerAdapter getmRecyclerAdapter() {
        return mRecyclerAdapter;
    }

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

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
        //builder.getNotification();
        manager.notify(1, notification);

    }

    private void init_tools(View view) {

        int ID_UP = 1;
        int ID_DOWN = 2;
        int ID_SEARCH = 3;
        int ID_INFO = 4;
        int ID_ERASE = 5;
        int ID_OK = 6;


        ActionItem nextItem = new ActionItem(ID_DOWN, "Next", getResources().getDrawable(R.drawable.menu_down_arrow));
        ActionItem prevItem = new ActionItem(ID_UP, "Prev", getResources().getDrawable(R.drawable.menu_up_arrow));
        ActionItem searchItem = new ActionItem(ID_SEARCH, "Find", getResources().getDrawable(R.drawable.menu_search));
        ActionItem infoItem = new ActionItem(ID_INFO, "Info", getResources().getDrawable(R.drawable.menu_info));
        ActionItem eraseItem = new ActionItem(ID_ERASE, "Clear", getResources().getDrawable(R.drawable.menu_eraser));
        ActionItem okItem = new ActionItem(ID_OK, "OK", getResources().getDrawable(R.drawable.menu_ok));


        prevItem.setSticky(true);
        nextItem.setSticky(true);


        final QuickAction quickAction = new QuickAction(getActivity(), QuickAction.VERTICAL);


        quickAction.addActionItem(nextItem);
        quickAction.addActionItem(prevItem);
        quickAction.addActionItem(searchItem);
        quickAction.addActionItem(infoItem);
        quickAction.addActionItem(eraseItem);
        quickAction.addActionItem(okItem);


        quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickAction source, int pos, int actionId) {
                ActionItem actionItem = quickAction.getActionItem(pos);


                if (actionId == 3) {
                    Toast.makeText(getActivity(), "Let's do some search action", Toast.LENGTH_SHORT).show();
                } else if (actionId == 4) {
                    Toast.makeText(getActivity(), "I have no info this time", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), actionItem.getTitle() + " selected", Toast.LENGTH_SHORT).show();
                }
            }
        });


        quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {
            @Override
            public void onDismiss() {
                Toast.makeText(getActivity(), "Dismissed", Toast.LENGTH_SHORT).show();
            }
        });


        quickAction.show(view);
    }


    public ScaleInAnimationAdapter getScaleAdapter() {
        return scaleAdapter;
    }
}
