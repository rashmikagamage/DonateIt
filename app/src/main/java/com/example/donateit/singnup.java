package com.example.donateit;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class singnup extends AppCompatActivity {

        //DECLARING OBJECT TYPE REFERENCES

        Button signup;
        EditText name,email,location,password1,password2,phoneNumber;
        DatabaseReference db;
        User user;
        String uid;
        FirebaseAuth auth;

        private  void clearControls(){

            name.setText("");
            email.setText("");
            location.setText("");
            password1.setText("");
            password2.setText("");
            phoneNumber.setText("");

        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singnup);

        //get id of instances

        signup = findViewById(R.id.signup_signup);
        name = findViewById(R.id.name_signup);
        email = findViewById(R.id.email_signin);
        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);
        location = findViewById(R.id.location);
        phoneNumber = findViewById(R.id.contactnumber);

        user = new User();



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = FirebaseDatabase.getInstance().getReference().child("Users");
                auth = FirebaseAuth.getInstance();


                try{

                   if (TextUtils.isEmpty(email.getText().toString()) || !isValidEmail(email.getText().toString())) {

                       Toast.makeText(getApplicationContext(), "Please enter an valid  E-mail ", Toast.LENGTH_SHORT).show();

                   }else if(TextUtils.isEmpty(name.getText().toString())){

                           Toast.makeText(getApplicationContext(),"Please enter Name",Toast.LENGTH_SHORT).show();

                    }else if (TextUtils.isEmpty(phoneNumber.getText().toString()) || phoneNumber.length() != 10 ){

                        Toast.makeText(getApplicationContext(),"Please enter a valid Contact Number ",Toast.LENGTH_SHORT).show();

                    }else if (TextUtils.isEmpty(location.getText().toString())){

                        Toast.makeText(getApplicationContext(),"Please enter Location ",Toast.LENGTH_SHORT).show();

                    }else if (TextUtils.isEmpty(password1.getText().toString()) ||  password1.getText().toString().length() < 6){

                        Toast.makeText(getApplicationContext(),"Password should be more than 5 characters",Toast.LENGTH_SHORT).show();

                    }else if (TextUtils.isEmpty(password2.getText().toString())){

                        Toast.makeText(getApplicationContext(),"Please Re-Type password ",Toast.LENGTH_SHORT).show();

                    }else if (!((password1.getText().toString().trim()).equals(password2.getText().toString().trim()))){

                        Toast.makeText(getApplicationContext(),password1.getText().toString(),Toast.LENGTH_SHORT).show();


                        password1.setText("");
                        password2.setText("");

                    }else {

                        user.setName(name.getText().toString().trim());
                        user.setEmail(email.getText().toString().trim());
                        user.setPhoneNumber(phoneNumber.getText().toString().trim());
                        user.setPassword(password2.getText().toString().trim());
                        user.setLocation(location.getText().toString().trim());


                        //Authetication of email and password to FIREBASE

                        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                   @Override
                                   public void onComplete(@NonNull Task<AuthResult> task) {
                                       if (task.isSuccessful()) {
                                           db.child(changeEmail(user.getEmail())).setValue(user);
                                           Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                                           Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                           startActivity(intent);
                                       }
                                       else {

                                           Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();

                                       }
                                   }
                               });

                        clearControls();


                    }



                }catch(NumberFormatException ne){
                    Toast.makeText(getApplicationContext(),"Invalid contact Number",Toast.LENGTH_LONG).show();
                }


            }
        });





    }

    //EMAIL VALIDATION METHOD
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    //CONTACT NUMBER VALIDATION METHOD
    public boolean isValidPhone(CharSequence phone) {
            if (TextUtils.isEmpty(phone)) {
                return false;
            } else {
                return android.util.Patterns.PHONE.matcher(phone).matches();
            }
    }

    public String changeEmail(String email){

           String newEmail1 =  email.replace('@','0');
           String newEmail2 = newEmail1.replace('.','0');
           return newEmail2;




    }
}
