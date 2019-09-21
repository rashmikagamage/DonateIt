package com.example.donateit;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;


import com.google.android.gms.common.ConnectionResult;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button signup,forget_password,login;
    EditText email,password;
    private VideoView mVideoView;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.upup);
        signup = findViewById(R.id.singup_login);
        forget_password = findViewById(R.id.forgetPassword);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email_signin);
        password = findViewById(R.id.password_signin);
        firebaseAuth =FirebaseAuth.getInstance();


        //background video
        mVideoView = findViewById(R.id.videoView);
        mVideoView.setVideoURI(uri);
        mVideoView.start();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }

       });//background video

        //SIGNUP PAGE

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(getApplicationContext(),singnup.class);
                startActivity(signup);

            }
        });

        ///////////////         LOGIN         ///////////////////////////////////

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               loginUserAccount();

            }
        });


        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forget = new Intent(getApplicationContext(),forget_password.class);
                startActivity(forget);

            }
        });





    }

   //loginUser Acoount

    private void loginUserAccount() {



        if (TextUtils.isEmpty(email.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();


                            Intent intent = new Intent(getApplicationContext(), category.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Login failed!", Toast.LENGTH_LONG).show();

                        }
                        //hel
                    }
                });
    }





}
