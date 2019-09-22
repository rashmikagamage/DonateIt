package com.example.donateit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class category extends AppCompatActivity {

    Button crisis, SocialService;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.usermenu,menu);
        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.signout){

            Toast.makeText(getApplicationContext(),"signpit",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}

