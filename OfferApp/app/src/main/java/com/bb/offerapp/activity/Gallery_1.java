package com.bb.offerapp.activity;

/**
 * Created by bb on 2017/3/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.bb.offerapp.R;
import com.bb.offerapp.adapter.ImageAdapter;

public class Gallery_1 extends Activity implements AdapterView.OnItemSelectedListener,ViewSwitcher.ViewFactory {

    private int[] res = { R.drawable.item1, R.drawable.item2, R.drawable.item3,
            R.drawable.item4, R.drawable.item5, R.drawable.item6,
            R.drawable.item7, R.drawable.item8, R.drawable.item9,
            R.drawable.item10, R.drawable.item11, R.drawable.item12 };

    private ImageAdapter adapter;
    private Gallery gallery;
    private ImageSwitcher is;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        gallery = (Gallery) findViewById(R.id.gallery1);
        is=(ImageSwitcher) findViewById(R.id.is);


        adapter = new ImageAdapter(res, this);
        gallery.setAdapter(adapter);
        gallery.setOnItemSelectedListener(this);
        is.setFactory(this);
        is.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        is.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // TODO Auto-generated method stub
        //image.setBackgroundResource(res[position%res.length]);
        is.setBackgroundResource(res[position%res.length]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }

    @Override
    public ImageView makeView() {
        // TODO Auto-generated method stub
        ImageView image=new ImageView(this);
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);

        return image;
    }
}
