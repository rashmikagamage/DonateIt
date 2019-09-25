package com.example.donateit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategorySelect extends AppCompatActivity {


    /********************************** Creating Objects ****************************************************/

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView nav_view;
    Button crisis, SocialService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* ********************************  Attaching side bar****************************************************/

        setContentView(R.layout.activity_category_select);

        /* ********************************* Initializing objects ****************************************************/

        drawerLayout = findViewById(R.id.dl);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        crisis = findViewById(R.id.crisis_help);
        SocialService = findViewById(R.id.social_servicebtn);


        /* ********************************* AddCrisisHelp page  ****************************************************/


        crisis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent crisis = new Intent(getApplicationContext(), Selection.class);
                startActivity(crisis);
            }
        });

        /* ********************************* Social service page ****************************************************/

        SocialService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SocialService;
                SocialService = new Intent(getApplicationContext(), SocialService.class);
                startActivity(SocialService);
            }
        });


        /* ********************************* Navigation layout reference  ****************************************************/

        nav_view = findViewById(R.id.nav_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                if (id == R.id.edit_profile_navi) {

                    Intent update = new Intent(getApplicationContext(), EditUser.class);      //User will ne redirect to edit Pahe
                    startActivity(update);
                } else if (id == R.id.delete_navi) {

                    deleteUser();       //CALL DELETE USER

                } else if (id == R.id.logout_navi) {

                    signout();          //CALL SIGN OUT

                }


                return true;
            }
        });


    }

    /* ********************************* method to select item in the navigator ****************************************************/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


    /* ********************************* Sign out method****************************************************/

    public void signout() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure?");
        alertDialogBuilder.setPositiveButton("yes",         //IF USER ENTER YES ON THE DIALOG BOX
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //FIRE BASE OBJECTS
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        firebaseAuth.signOut();

                        //REDIRECT TO LOG IN PAGE

                        Intent signout = new Intent(getApplicationContext(), Login.class);
                        Toast.makeText(getApplicationContext(), "Sign Out Succesfully ", Toast.LENGTH_SHORT).show();
                        startActivity(signout);

                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() { //IF USER ENTER NO ON THE DIALOG BOX
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


    /* ********************************* delete user method****************************************************/

    private void deleteUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //FIRE BASE OBJECTS

                        Singnup singnup = new Singnup();
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        String currentUserEmail = firebaseUser.getEmail();

                        //DELETING USER

                        firebaseUser.delete();
                        DatabaseReference deleteUser = FirebaseDatabase.getInstance().getReference().child("Users").child(singnup.changeEmail(currentUserEmail));
                        deleteUser.removeValue();

                        //REDIRECT TO LOG IN PAGE

                        Intent delete = new Intent(getApplicationContext(), Login.class);
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