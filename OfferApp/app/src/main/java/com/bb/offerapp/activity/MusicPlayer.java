package com.bb.offerapp.activity;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bb.offerapp.R;

import java.util.HashMap;

/**
 * Created by bb on 2017/3/16.
 */

public class MusicPlayer extends Activity implements View.OnClickListener
{
    // 引用textView和四个按钮Button
    TextView textView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    // 引用mideaPlayer和SoundPool
    MediaPlayer mMediaPlayer;
    SoundPool soundPool;
    HashMap<Integer, Integer> soundPoolMap;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // 初始化声音
        initSounds();
        setContentView(R.layout.music_main);

        // 得到TextView和Button的引用
        textView = (TextView) this.findViewById(R.id.textView);
        button1 = (Button) this.findViewById(R.id.button1);
        button2 = (Button) this.findViewById(R.id.button2);
        button3 = (Button) this.findViewById(R.id.button3);
        button4 = (Button) this.findViewById(R.id.button4);

        // 设置监听事件
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    /**
     * 初始化声音的方法
     */
    public void initSounds()
    {
        mMediaPlayer = MediaPlayer.create(this, R.raw.music);
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap<Integer, Integer>();
        soundPoolMap.put(1, soundPool.load(this, R.raw.effect, 1));
        soundPoolMap.put(2, soundPool.load(this, R.raw.bird, 1));
    }

    int mPresentPlayId;

    /**
     * 播放音效的方法
     */
    public void playSound(int sound, int loop)
    {
        AudioManager mgr = (AudioManager) this
                .getSystemService(Context.AUDIO_SERVICE);
        float streamVolumeCurrent = mgr
                .getStreamVolume(AudioManager.STREAM_MUSIC);
        float streamVolumeMax = mgr
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = streamVolumeCurrent / streamVolumeMax;
        mPresentPlayId=soundPool.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);
    }

    /**
     * 点击事件处理
     */
    public void onClick(View v)
    {
        if (v == button1)
        {
            textView.setText("使用MediaPlayer播放声音");
            if (!mMediaPlayer.isPlaying())
            {
                mMediaPlayer.setLooping(true);
                mMediaPlayer.start();
            }
        } else if (v == button2)
        {
            textView.setText("暂停了MediaPlayer播放声音");
            if (mMediaPlayer.isPlaying())
            {
                mMediaPlayer.pause();
            }
        } else if (v == button3)
        {
            textView.setText("使用SoundPool播放声音");
            // 2为用播放音效的名字，0为不循环播放
            this.playSound(2, 3);
        } else if (v == button4)
        {
            textView.setText("暂停了SoundPool播放声音");
            soundPool.pause(mPresentPlayId);
//			Toast.makeText(MediaPlayDemoActivity.this, mPresentPlayId+"", Toast.LENGTH_SHORT).show();
        }
    }
}
