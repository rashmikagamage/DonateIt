package com.example.donateit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class singnup extends AppCompatActivity {


    Button signup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singnup);



        signup = findViewById(R.id.signup_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup_to_login = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(signup_to_login);
            }
        });





    }
}
