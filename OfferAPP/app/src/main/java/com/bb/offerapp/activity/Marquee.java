package com.bb.offerapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.offerapp.R;
import com.bb.offerapp.view.MarqueeView;

import java.util.ArrayList;
import java.util.List;


public class Marquee extends Activity {
    private SeekBar seekBar;
    private MarqueeView test1;
    private MarqueeView test2;
    private MarqueeView test3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marquee);
        seekBar= (SeekBar) findViewById(R.id.seekBar);
        test1 = (MarqueeView) this.findViewById(R.id.textView);
        test2 = (MarqueeView) this.findViewById(R.id.textView2);
        test3 = (MarqueeView) this.findViewById(R.id.textView3);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                test1.setSpeed(progress);
                test2.setSpeed(progress);
                test3.setSpeed(progress);
                test1.startScroll();
                test2.startScroll();
                test3.startScroll();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


}

