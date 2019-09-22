package com.example.donateit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class category extends AppCompatActivity {

    Button crisis, SocialService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        crisis = findViewById(R.id.crisis_help);
        SocialService = findViewById(R.id.social_servicebtn);

        crisis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent crisis = new Intent(getApplicationContext(), com.example.donateit.crisis.class);
                startActivity(crisis);
            }
        });

        SocialService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SocialService;
                SocialService = new Intent(getApplicationContext(), SocialService.class);
                startActivity(SocialService);
            }
        });

    }


    }

