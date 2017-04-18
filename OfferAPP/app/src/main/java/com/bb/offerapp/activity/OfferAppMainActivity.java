package com.bb.offerapp.activity;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.offerapp.R;
import com.bb.offerapp.adapter.MyViewPaperAdapter;
import com.bb.offerapp.dialog.CityNameDialog;
import com.nineoldandroids.view.ViewHelper;
import com.thinkpage.lib.api.TPCity;
import com.thinkpage.lib.api.TPListeners;
import com.thinkpage.lib.api.TPWeatherDaily;
import com.thinkpage.lib.api.TPWeatherManager;
import com.thinkpage.lib.api.TPWeatherNow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import me.grantland.widget.AutofitTextView;


public class OfferAppMainActivity extends AppCompatActivity{
    //两次推出程序
    private static boolean isExit = false;
    Bitmap photo;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    //悬浮按钮
    private FloatingActionButton fab;
    //欢迎
    private AppBarLayout app_bar;
    //tab菜单
    private TabLayout tabLayout;
    private String[] tabTittle = {"功能", "项目"};
    //工具栏
    private Toolbar toolbar;
    //Viewpager
    private ViewPager viewPager;
    private MyViewPaperAdapter myViewPaperAdapter;
    //侧滑菜单
    private DrawerLayout drawer;
    private NavigationView navigationView;
    //首页壁纸
    private ImageView index_image;
    //天气
    private ImageView weather_icon;
    private AutofitTextView weather_city;
    private AutofitTextView weather_case;
    private TextView weather_degree;
    private TextView weather_wind;
    private String temperature;//实时温度
    //改变天气位置dailog
    private CityNameDialog cityNameDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView_ImageView();//初始化首页壁纸
        initView_NavigationView();//初始化DrawerLayout侧滑菜单导航
        initView_AppBarLayout();//初始化AppBarLayout
        initView_Toolbar();//初始化Toolbar
        initView_TabLayout();//初始化TabLayout
        initView_ViewPager();//初始化ViewPaper
        initView_FloatingActionButton();//初始化FloatingActionButton(悬浮按钮)
        initView_WeatherCard();//初始化天气状况
        initView_WeatherData("北京");//从"心知天气"获得3天天气api接口数据
        initView_NowWeatherData("北京");//获得当天实时天气
        initView_DrawerLayout();//DrawerLayout

    }



    private void initView_DrawerLayout() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setScrimColor(Color.TRANSPARENT);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerClosed(View v) {

            }

            @Override
            public void onDrawerOpened(View v) {

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // 主体窗口
                View mainFrame = drawer.getChildAt(0);
                // 这个就是隐藏起来的边侧滑菜单栏
                View leftMenu = drawerView;

                addQQStyleSlide(mainFrame, leftMenu, slideOffset);
            }

            @Override
            public void onDrawerStateChanged(int arg0) {

            }
        });
    }


//    //右侧菜单
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.recyclerview_option, menu);
//        return true;
//    }
//
//    //右侧菜单点击事件
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int itemId = item.getItemId();
//        if (itemId == R.id.lin) {
//
//        } else if (itemId == R.id.grid) {
//
//        } else if (itemId == R.id.staggered) {
//
//        }
//        return super.onOptionsItemSelected(item);
//    }


    //两次退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }


    private void initView_ImageView() {
        index_image = (ImageView) findViewById(R.id.imageview);
    }

    private void initView_NavigationView() {

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        setNavigationViewSize(navigationView, 0.65f, 0.95f);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_changepic) {

                    Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    openAlbumIntent.setType("image/*");
                    startActivityForResult(openAlbumIntent, 1);
                } else if (id == R.id.nav_changecity) {
                    cityNameDialog = new CityNameDialog(OfferAppMainActivity.this);
                    cityNameDialog.show();
                    cityNameDialog.getOk().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            weather_city.setText(cityNameDialog.getEditText().getText().toString());
                            initView_WeatherData(cityNameDialog.getEditText().getText().toString());
                            initView_NowWeatherData(cityNameDialog.getEditText().getText().toString());
                            cityNameDialog.cancel();
                        }
                    });


                } else if (id == R.id.nav_slideshow) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(OfferAppMainActivity.this, 2);
                    gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                    myViewPaperAdapter.getaFragment().getmRecyclerView().setLayoutManager(gridLayoutManager);
                    myViewPaperAdapter.getaFragment().getmRecyclerAdapter().setSmallType(true);
                    myViewPaperAdapter.getaFragment().getmRecyclerView().setAdapter(myViewPaperAdapter.getaFragment().getmRecyclerAdapter());

                } else if (id == R.id.nav_wechat) {
                    Intent intent2 = new Intent(OfferAppMainActivity.this, WechatCard.class);
                    startActivity(intent2);
                } else if (id == R.id.nav_about) {

                    Intent intent = new Intent(OfferAppMainActivity.this, About.class);
                    startActivity(intent);

                } else if (id == R.id.nav_lineshow) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OfferAppMainActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    myViewPaperAdapter.getaFragment().getmRecyclerView().setLayoutManager(linearLayoutManager);
                    myViewPaperAdapter.getaFragment().getmRecyclerAdapter().setSmallType(false);
                    myViewPaperAdapter.getaFragment().getmRecyclerView().setAdapter(myViewPaperAdapter.getaFragment().getmRecyclerAdapter());

                }


                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        else {
            try {
                Uri uri = data.getData();
                photo = MediaStore.Images.Media.getBitmap(getContentResolver(),
                        uri);
                index_image.setImageBitmap(photo);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    private void initView_AppBarLayout() {
        app_bar = (AppBarLayout) findViewById(R.id.app_bar);
        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("");//设置收缩之前标题
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    collapsingToolbarLayout.setTitle(weather_case.getText().toString() + " " + temperature + "°");//设置收缩之后标题
                } else {
                    collapsingToolbarLayout.setTitle("");//设置收缩中标题
                }
            }
        });
    }


    private void initView_Toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private void initView_TabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，MODE_FIXED是固定的,不能超出屏幕，MODE_SCROLLABLE可超出屏幕范围滚动的
        tabLayout.addTab(tabLayout.newTab().setText("杂货"));
        tabLayout.addTab(tabLayout.newTab().setText("音乐"));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //关联viewPager
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initView_ViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpaper);
        viewPager.setAdapter(myViewPaperAdapter = new MyViewPaperAdapter(getSupportFragmentManager(), tabTittle));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private void initView_FloatingActionButton() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.argb(222, 208, 166, 80)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                OfferAppMainActivity.this.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Snackbar.make(view, "3秒后将跳转到浏览器", 1000)
                                .setAction("Action", null).show();
                    }
                }, 0);
                OfferAppMainActivity.this.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Snackbar.make(view, "2秒后将跳转到浏览器", 1000)
                                .setAction("Action", null).show();
                    }
                }, 1000);
                OfferAppMainActivity.this.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Snackbar.make(view, "1秒后将跳转到浏览器", 1000)
                                .setAction("Action", null).show();
                    }
                }, 2000);
                OfferAppMainActivity.this.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Uri uri = Uri.parse("https://github.com/JayKuzzi");
                        Intent it = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(it);
                    }
                }, 3000);
            }
        });
    }

    private void initView_WeatherCard() {

        weather_icon = (ImageView) findViewById(R.id.weather_icon);
        weather_city = (AutofitTextView) findViewById(R.id.weather_city);
        weather_case = (AutofitTextView) findViewById(R.id.weather_case);
        weather_degree = (TextView) findViewById(R.id.weather_degree);
        weather_wind = (TextView) findViewById(R.id.weather_wind);

        weather_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cityNameDialog = new CityNameDialog(OfferAppMainActivity.this);
                cityNameDialog.show();
                cityNameDialog.getOk().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        weather_city.setText(cityNameDialog.getEditText().getText().toString());
                        initView_WeatherData(cityNameDialog.getEditText().getText().toString());
                        initView_NowWeatherData(cityNameDialog.getEditText().getText().toString());
                        cityNameDialog.cancel();
                    }
                });


            }
        });

    }

    private void initView_WeatherData(String cityName) {
        final TPWeatherManager weatherManager = TPWeatherManager.sharedWeatherManager();
        //使用心知天气官网获取的key和用户id初始化WeatherManager
        weatherManager.initWithKeyAndUserId("fyajwhj499drrlob", "U041DF6FDF");
        weatherManager.getWeatherDailyArray(new TPCity(cityName)
                , TPWeatherManager.TPWeatherReportLanguage.kSimplifiedChinese
                , TPWeatherManager.TPTemperatureUnit.kCelsius
                , new Date(), 3
                , new TPListeners.TPWeatherDailyListener() {

                    @Override
                    public void onTPWeatherDailyAvailable(TPWeatherDaily[] tpWeatherDailies, String s) {
                        if (tpWeatherDailies != null) {

                            String textDay = tpWeatherDailies[0].textDay;
                            switch (textDay) {
                                case "晴":
                                    weather_icon.setImageResource(R.drawable.sun);
                                    break;
                                case "多云":
                                    weather_icon.setImageResource(R.drawable.cloudy);
                                    break;
                                case "晴间多云":
                                    weather_icon.setImageResource(R.drawable.suncloudy);
                                    break;
                                case "大部多云":
                                    weather_icon.setImageResource(R.drawable.mostcloudy);
                                    break;
                                case "阴":
                                    weather_icon.setImageResource(R.drawable.overcast);
                                    break;
                                case "阵雨":
                                    weather_icon.setImageResource(R.drawable.shower);
                                    break;
                                case "雷阵雨":
                                    weather_icon.setImageResource(R.drawable.storm);
                                    break;
                                case "雷阵雨伴有冰雹":
                                    weather_icon.setImageResource(R.drawable.showersnow);
                                    break;
                                case "小雨":
                                    weather_icon.setImageResource(R.drawable.shower);
                                    break;
                                case "中雨":
                                    weather_icon.setImageResource(R.drawable.midshower);
                                    break;
                                case "大雨":
                                    weather_icon.setImageResource(R.drawable.heavyshower);
                                    break;
                                case "暴雨":
                                    weather_icon.setImageResource(R.drawable.heavyshower);
                                    break;
                                case "大暴雨":
                                    weather_icon.setImageResource(R.drawable.heavyshower);
                                    break;
                                case "特大暴雨":
                                    weather_icon.setImageResource(R.drawable.heavyshower);
                                    break;
                                case "冻雨":
                                    weather_icon.setImageResource(R.drawable.heavyshower);
                                    break;
                                case "雨夹雪":
                                    weather_icon.setImageResource(R.drawable.showersnow);
                                    break;
                                case "阵雪":
                                    weather_icon.setImageResource(R.drawable.snow);
                                    break;
                                case "小雪":
                                    weather_icon.setImageResource(R.drawable.snow);
                                    break;
                                case "中雪":
                                    weather_icon.setImageResource(R.drawable.midshower);
                                    break;
                                case "大雪":
                                    weather_icon.setImageResource(R.drawable.heavysnow);
                                    break;
                                case "暴雪":
                                    weather_icon.setImageResource(R.drawable.heavysnow);
                                    break;
                                case "浮尘":
                                    weather_icon.setImageResource(R.drawable.dust);
                                    break;
                                case "扬沙":
                                    weather_icon.setImageResource(R.drawable.dust);
                                    break;
                                case "沙尘暴":
                                    weather_icon.setImageResource(R.drawable.dust);
                                    break;
                                case "强沙尘暴":
                                    weather_icon.setImageResource(R.drawable.dust);
                                    break;
                                case "雾":
                                    weather_icon.setImageResource(R.drawable.foggy);
                                    break;
                                case "霾":
                                    weather_icon.setImageResource(R.drawable.haze);
                                    break;
                                case "风":
                                    weather_icon.setImageResource(R.drawable.windy);
                                    break;
                                case "大风":
                                    weather_icon.setImageResource(R.drawable.windy);
                                    break;
                                case "飓风":
                                    weather_icon.setImageResource(R.drawable.hurricane);
                                    break;
                                case "热带风暴":
                                    weather_icon.setImageResource(R.drawable.hurricane);
                                    break;
                                case "龙卷风":
                                    weather_icon.setImageResource(R.drawable.hurricane);
                                    break;
                                case "冷":
                                    weather_icon.setImageResource(R.drawable.cold);
                                    break;
                                case "热":
                                    weather_icon.setImageResource(R.drawable.hot);
                                    break;
                                case "未知":
                                    weather_icon.setImageResource(R.drawable.na);
                                    break;

                            }
                            weather_case.setText(textDay);
                            weather_degree.setText(tpWeatherDailies[0].lowTemperature + "°~" + tpWeatherDailies[0].highTemperature + "°");
                            weather_wind.setText(tpWeatherDailies[0].windDirection + "风 " + (int) tpWeatherDailies[0].windScale + "级");
                        } else {
                            System.out.println(s); //错误信息
                        }
                    }

                });
    }


    private void initView_NowWeatherData(String cityNamee) {
        TPWeatherManager weatherManager = TPWeatherManager.sharedWeatherManager();
        weatherManager.initWithKeyAndUserId("fyajwhj499drrlob", "U041DF6FDF");
        weatherManager.getWeatherNow(new TPCity(cityNamee)
                , TPWeatherManager.TPWeatherReportLanguage.kSimplifiedChinese
                , TPWeatherManager.TPTemperatureUnit.kCelsius
                , new TPListeners.TPWeatherNowListener() {
                    @Override
                    public void onTPWeatherNowAvailable(TPWeatherNow weatherNow, String errorInfo) {
                        if (weatherNow != null) {
                            temperature = weatherNow.temperature + "";
                        } else {
                        }
                    }
                });
    }


    //此处将控制NavigationView侧滑出的高度、宽度已经重心位置（居中？靠上？靠下？）
    private void setNavigationViewSize(NavigationView nv, float w_percent, float h_percent) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        //宽度默认是MATCH_PARENT，
        //NavigationView的宽度
        int width = (int) (displayMetrics.widthPixels * w_percent);

        //NavigationView的高度
        int height = (int) (displayMetrics.heightPixels * h_percent);

        //高度默认是MATCH_PARENT，如果不打算打满屏幕高度：DrawerLayout.LayoutParams.MATCH_PARENT，
        // 那么比如可以设置成屏幕高度的80%(即0.8f)
        DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(width, height);

        //主要要设置center，否则侧滑出来的菜单栏将从下往上绘制相应高度和宽度而不是居中
        params.gravity = Gravity.START | Gravity.CENTER_VERTICAL;

        nv.setLayoutParams(params);
    }

    // 实现边侧滑的核心代码
    private void addQQStyleSlide(View mainFrame, View leftMenu, float slideOffset) {
        //GAP的值决定左边侧滑出来的宽度和右边的主界面之间在侧滑过程以及侧滑结束后的间距。
        //如果不设置此值或者设置为0，则将恢复成Android系统默认的样式，即侧滑出来的界面和主界面之间紧密贴在一起。
        int GAP = 100;

        float leftScale = 0.5f + 0.5f * slideOffset;
        float rightScale = 1 - 0.2f * slideOffset;

        ViewHelper.setScaleX(leftMenu, leftScale);
        ViewHelper.setScaleY(leftMenu, leftScale);
        ViewHelper.setAlpha(leftMenu, 0.5f + 0.5f * slideOffset);
        ViewHelper.setTranslationX(mainFrame, (leftMenu.getMeasuredWidth() + GAP) * slideOffset);
        ViewHelper.setPivotX(mainFrame, 0);
        ViewHelper.setPivotY(mainFrame, mainFrame.getMeasuredHeight() / 2);
        mainFrame.invalidate();
        ViewHelper.setScaleX(mainFrame, rightScale);
        ViewHelper.setScaleY(mainFrame, rightScale);

        // 该处主要是为了使背景的颜色渐变过渡。
        // 如果失效，则可能是因为Android DrawerLayout的NavigationView绘制背景的图层互相之间遮盖导致。
        //此处不关乎重点实现，作为代码在未来的复用，仍然保留，当然也可以删掉！
        getWindow().getDecorView().getBackground().setColorFilter(evaluate(slideOffset, Color.BLACK, Color.TRANSPARENT),
                PorterDuff.Mode.SRC_OVER);
    }

    private Integer evaluate(float fraction, Object startValue, Integer endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;
        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;
        return (int) ((startA + (int) (fraction * (endA - startA))) << 24)
                | (int) ((startR + (int) (fraction * (endR - startR))) << 16)
                | (int) ((startG + (int) (fraction * (endG - startG))) << 8)
                | (int) ((startB + (int) (fraction * (endB - startB))));
    }
}

