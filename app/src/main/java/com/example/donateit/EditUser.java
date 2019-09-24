package com.example.donateit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditUser extends AppCompatActivity {

    /*
     * This is for Update user Account
     * take current logged user
     * show the details
     * And edit data
     * then user can upload agaian
     * */

    EditText name, location, contacNo;
    Button update;
    DatabaseReference dbView;
    DatabaseReference dbUpdate;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String currentUserEmail, currentContactNo, currentLocation, currentName, currentPassword;
    singnup signup;
    User userUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        //Linking object with IDs

        name = findViewById(R.id.name_editUser);
        location = findViewById(R.id.location_editUser);
        contacNo = findViewById(R.id.contactNo_edituser);
        update = findViewById(R.id.update);
        signup = new singnup();
        userUpdate = new User();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        currentUserEmail = firebaseUser.getEmail();


        //initialising db object

        dbView = FirebaseDatabase.getInstance().getReference().child("Users").child(signup.changeEmail(currentUserEmail));


        dbView.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {

                    //Getting Values

                    currentName = dataSnapshot.child("name").getValue().toString();
                    currentLocation = dataSnapshot.child("location").getValue().toString();
                    currentContactNo = dataSnapshot.child("phoneNumber").getValue().toString();
                    currentPassword = dataSnapshot.child("password").getValue().toString();

                    //Set values

                    name.setText(currentName);
                    contacNo.setText(currentContactNo);
                    location.setText(currentLocation);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

    }


    //Method for Update User

    public void updateUser(View view) {

        dbUpdate = FirebaseDatabase.getInstance().getReference().child("Users");
        userUpdate.setEmail(currentUserEmail);
        userUpdate.setPassword(currentPassword);
        userUpdate.setName(name.getText().toString());
        userUpdate.setLocation(location.getText().toString());
        userUpdate.setPhoneNumber(contacNo.getText().toString());

        dbUpdate.child(signup.changeEmail(currentUserEmail)).setValue(userUpdate);
        Toast.makeText(getApplicationContext(), "Updated Succesfully", Toast.LENGTH_SHORT).show();


    }
}
