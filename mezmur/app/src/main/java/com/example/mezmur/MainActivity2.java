package com.example.mezmur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class MainActivity2 extends AppCompatActivity {
    TextView tv;
    ImageView img;
    ArrayList<ArrayList<String>> mez;
    MediaPlayer player;
    SeekBar seekbar;
    Handler handler;
    Runnable runnable;
    String[][] audioTitle;
    boolean clicked;
    int fam, child;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = findViewById(R.id.tv1);
        img = findViewById(R.id.btn1);
        clicked = true;

        Intent intent = getIntent();


        fam = intent.getIntExtra("family", 0);
        name = intent.getStringExtra("mezmurName");
        child = intent.getIntExtra("Child", 0);
        try {
            Mezmur();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //player=MediaPlayer.create(MainActivity2.this, );
        tv.setText(mez.get(fam).get(child));
        seekbar = findViewById(R.id.pr1);
        handler = new Handler();
        audio();
        String str = "a"+name.substring(0,name.indexOf("."));
        Log.i("NH",name);
        if (false) {
            int resId = getResources().getIdentifier(str, "raw", getPackageName());

            player = MediaPlayer.create(this, resId);
            seekbar.setMax(player.getDuration());
            seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) {
                        player.seekTo(progress);
                    }
                }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                playcycle();
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        seekbar.setMax(player.getDuration());
                        playcycle();

                    }
                });


        } else {
            img.setVisibility(View.GONE);
            seekbar.setVisibility(View.GONE);
        }
    }


    public void playcycle() {
        seekbar.setProgress(player.getCurrentPosition());
        if (player.isPlaying()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    playcycle();
                }
            };
            handler.postDelayed(runnable, 1000);

        }
    }

    public void audio() {
        //Field[] field = R.raw.class.getFields();
        audioTitle = new String[][]{
                {"a131", "a382", "a384", "a385", "a386"},
                {"f", "g", "h"},


        };


    }

    public void play(View v) {
        if (clicked) {
            player.start();
            img.setImageResource(R.drawable.playicon);
            clicked = false;
        } else {
            player.pause();
            img.setImageResource(R.drawable.pauseicon);
            clicked = true;
        }

    }

    @Override
    public void finish() {
        if (player != null) {
            player.release();
        }
        super.finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
        }
        handler.removeCallbacks(runnable);

    }


    public void Mezmur() throws IOException {
        InputStream inputStream = getAssets().open("mezmur2");
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        String str = new String(buffer);
        mez = new ArrayList<>();
        ArrayList<String> titleList;
        String[] arr = str.split(";");
        for (int i = 0; i < arr.length; i++) {
            titleList = new ArrayList(Arrays.asList(arr[i].split(",")));
            mez.add(titleList);
        }

    }
}
