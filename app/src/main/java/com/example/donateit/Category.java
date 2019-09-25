package com.example.donateit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Category extends AppCompatActivity {

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

        //Crisis page is called

        crisis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent crisis = new Intent(getApplicationContext(), CrysisSelection.class);
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

    // When tool bar selected this  method is called

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.signout) {

            signout();

        } else if (id == R.id.editProfile) {

            Intent update = new Intent(getApplicationContext(), EditUser.class);      //User will ne redirect to edit Pahe
            startActivity(update);
        } else if (id == R.id.delete) {

            deleteUser();

        }
        return super.onOptionsItemSelected(item);
    }



    //Sign out methhod for Signouting

    public void signout(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure?");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                //Creating objects
                                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                                firebaseAuth.signOut();
                                Intent signout = new Intent(getApplicationContext(), MainActivity.class);
                                Toast.makeText(getApplicationContext(), "Sign Out Succesfully ", Toast.LENGTH_SHORT).show();
                                startActivity(signout);

                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /*
     * When user select delete account
     * this method will be called
     */


    private void deleteUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Creating objects

                        Singnup singnup = new Singnup();
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        String currentUserEmail = firebaseUser.getEmail();
                        firebaseUser.delete();
                        DatabaseReference deleteUser = FirebaseDatabase.getInstance().getReference().child("Users").child(singnup.changeEmail(currentUserEmail));
                        deleteUser.removeValue();

                        Intent delete = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(delete);

                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}

