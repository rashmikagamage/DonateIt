package com.example.donateit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ForgetPassword extends AppCompatActivity {

    Button reset_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        reset_password = findViewById(R.id.reset_password);

        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent reset = new Intent(getApplicationContext(), Login.class);
                startActivity(reset);
            }
        });

    }
}
