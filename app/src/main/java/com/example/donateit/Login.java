package com.example.donateit;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    /********************************** Creating Objects ****************************************************/

    private Button signup, forgetPassword, login;
    private EditText email, password;
    private VideoView mVideoView;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    /********************************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //HIDING TOOLBAR

        getSupportActionBar().hide();

        //Initializing objects

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ab);
        signup = findViewById(R.id.singup_login);
        forgetPassword = findViewById(R.id.forgetPassword);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email_signin);
        password = findViewById(R.id.password_signin);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        /****************************  background video****************************************************/

        mVideoView = findViewById(R.id.videoView);
        mVideoView.setVideoURI(uri);
        mVideoView.start();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }

        });

        /****************************  background video****************************************************/


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(getApplicationContext(), Singnup.class);
                startActivity(signup);

            }
        });

        /****************************  Login method is called   ****************************************************/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUserAccount();

            }
        });

        /****************************  Forget password method is called   ****************************************************/

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forget = new Intent(getApplicationContext(), ForgetPassword.class);
                startActivity(forget);

            }
        });


    }

    /****************************  Login method ****************************************************/

    private void loginUserAccount() {

        //Validating form
        if (TextUtils.isEmpty(email.getText().toString()) || !Singnup.isValidEmail(email.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter A valid email...", Toast.LENGTH_LONG).show();
            return;

        } else if (TextUtils.isEmpty(password.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;

        } else {
            progressBar.setVisibility(View.VISIBLE);

            firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override

                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                //Display a toast to wrong inputs
                                Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);

                                Intent intent = new Intent(getApplicationContext(), CategorySelect.class);
                                startActivity(intent);


                            } else {

                                //Display a toast to wrong inputs

                                Toast.makeText(getApplicationContext(), "Invalid Cardential!", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }


                        }
                    });
        }
    }

    /**************************** Stopping progress Bar ****************************************************/

    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
