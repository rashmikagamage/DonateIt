package com.example.donateit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Selection extends AppCompatActivity {

    Button donator,donee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection2);

        donator = findViewById(R.id.donator);
        donee = findViewById(R.id.donee);


        donator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent donator = new Intent(getApplicationContext(), Category.class);
                startActivity(donator);
            }
        });

        donee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent donee = new Intent(getApplicationContext(), CrisisView.class);
                startActivity(donee);
            }
        });
    }
}
