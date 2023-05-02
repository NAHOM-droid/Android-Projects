package com.example.mezmur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Activitysplash extends AppCompatActivity {

    private static int Splash_Screen=5000;

    Animation topAnim,bottomAnim;
    ImageView image;
    TextView logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainsplash);

        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        topAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);


        image=findViewById(R.id.img);
        logo=findViewById(R.id.textView);


        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Activitysplash.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },Splash_Screen);





    }
}