package com.example.donateit;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button signup,forget_password,login;
    private VideoView mVideoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.upup);
        signup = findViewById(R.id.singup_login);
        forget_password = findViewById(R.id.forgetPassword);
        login = findViewById(R.id.login);

            //background video
        mVideoView = findViewById(R.id.videoView);
        mVideoView.setVideoURI(uri);
        mVideoView.start();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }

       });//background video

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(getApplicationContext(),singnup.class);
                startActivity(signup);

            }
        });

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forget = new Intent(getApplicationContext(),forget_password.class);
                startActivity(forget);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(getApplicationContext(),selection.class);
                startActivity(login);

            }
        });



    }
}
