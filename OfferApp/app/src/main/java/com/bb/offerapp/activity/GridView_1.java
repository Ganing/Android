package com.bb.offerapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bb.offerapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridView_1 extends Activity implements AdapterView.OnItemClickListener{
    private GridView gridView;
    private SimpleAdapter sim_adapter;
    private List<Map<String, Object>> data_list;
    // 图片封装为一个数组
    private int[] icon = {R.mipmap.address_book, R.mipmap.calendar,
            R.mipmap.camera, R.mipmap.clock, R.mipmap.games_control,
            R.mipmap.messenger, R.mipmap.ringtone, R.mipmap.settings,
            R.mipmap.speech_balloon, R.mipmap.weather, R.mipmap.world,
            R.mipmap.youtube};
    private String[] iconName = {"通讯录", "日历", "照相机", "时钟", "游戏", "短信", "铃声",
            "设置", "语音", "天气", "浏览器", "视频"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);
        gridView = (GridView) findViewById(R.id.gridView);
        data_list = new ArrayList<>();
        //获取数据
        getData();
        //新建适配器
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.gridview_item
                , new String[]{"image", "text"}, new int[]{R.id.image, R.id.text});
        //试图加载适配器
        gridView.setAdapter(sim_adapter);
        gridView.setOnItemClickListener(this);


    }

    private List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以

        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        return data_list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"我是"+iconName[position],Toast.LENGTH_SHORT).show();
    }
}
