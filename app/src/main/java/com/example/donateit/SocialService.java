package com.example.donateit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SocialService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_service);
    }

    public void goToSocialSer(View view){
        Intent intent = new Intent(this, Intro.class);
        startActivity(intent);
    }

    public void goToHowTo(View view){
        Intent intent = new Intent(this, HowTo.class);
        startActivity(intent);

    }

    public void goToCounselling(View view){
        Intent intent = new Intent(this, counselling.class);
        startActivity(intent);

    }


}
