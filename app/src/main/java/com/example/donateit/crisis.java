package com.example.donateit;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class crisis extends AppCompatActivity {

    AnimationDrawable crysisAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crisis);

        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        imageView.setBackgroundResource(R.drawable.animationcrysis);
        crysisAnimation = (AnimationDrawable) imageView.getBackground();


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        crysisAnimation.start();
    }

}
