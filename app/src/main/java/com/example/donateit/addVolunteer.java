package com.example.donateit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addVolunteer extends AppCompatActivity {

    EditText txtName ,txtPhone, txtEmail;
    Button btnAdd;
    String emailPattern ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Volunteer volObj;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_volunteer);

        txtName = (EditText) findViewById(R.id.editName);

        txtEmail = (EditText) findViewById(R.id.editEmail);

        txtPhone = (EditText) findViewById(R.id.editPhoneNumber);
        btnAdd = (Button) findViewById(R.id.btnadd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVolunteer();
            }
        });
    }


    public void addVolunteer(){
        dbRef = FirebaseDatabase.getInstance().getReference("/Volunteer");
        volObj = new Volunteer();

        try {
            if(TextUtils.isEmpty(txtName.toString()))
                Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
            if(TextUtils.isEmpty(txtPhone.toString()))
                Toast.makeText(getApplicationContext(), "Enter Volunteer phone number", Toast.LENGTH_SHORT).show();
            if(TextUtils.isEmpty(txtEmail.toString()))
                Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
            if (!(txtEmail.getText().toString()).matches(emailPattern))
                Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
            else{

                volObj.setVolEmail(txtEmail.getText().toString().trim());
                volObj.setVolName(txtName.getText().toString().trim());
                volObj.setVolPhone(Integer.parseInt(txtPhone.getText().toString().trim()));

                String path = txtEmail.getText().toString();
                dbRef.child(path).setValue(volObj);
            }
        }
        catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid Phone number", Toast.LENGTH_SHORT).show();
        }

    }
}
