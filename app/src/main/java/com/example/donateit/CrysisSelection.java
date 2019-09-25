package com.example.donateit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CrysisSelection extends AppCompatActivity {

    Button sendHelp, getHelp; //Button objects

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crysis_selection);

        //getting Referneces

        sendHelp = findViewById(R.id.sendhelp);
        getHelp = findViewById(R.id.gethelp);

        //Onclick method for Send Help

        sendHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddCrisisHelp.class);
                startActivity(intent);
            }
        });

        getHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CrisisView.class);
                startActivity(intent);
            }
        });


    }
}
