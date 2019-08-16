package com.example.donateit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button signup,forget_password,login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup = findViewById(R.id.singup_login);
        forget_password = findViewById(R.id.forgetPassword);
        login = findViewById(R.id.login);

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
