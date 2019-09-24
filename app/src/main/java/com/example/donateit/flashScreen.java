package com.example.donateit;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class flashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

Handler handler;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_screeen);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(flashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
