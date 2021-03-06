package com.example.donateit;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
<<<<<<< HEAD:app/src/main/java/com/example/donateit/crisis.java
import android.support.v7.widget.RecyclerView;
=======
import android.support.v7.app.AppCompatActivity;
>>>>>>> 6b9bdf77e3ceb3227ac1249077471611fab6f5c4:app/src/main/java/com/example/donateit/AddCrisisHelp.java
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCrisisHelp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AnimationDrawable crysisAnimation;
    EditText message,contactNo,location;
    Spinner spinner;
    DatabaseReference db;
    CrysisHelp crysisHelp;
    Button sendthem;
    String text;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String currentUserEmail;
    Button viewhelp;
    static int ID=0;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_crisis_help);

        //Animated alert button

        ImageView imageView = findViewById(R.id.imageView2);
        imageView.setBackgroundResource(R.drawable.animationcrysis);
        crysisAnimation = (AnimationDrawable) imageView.getBackground();
        spinner = findViewById(R.id.helptype);
        message = findViewById(R.id.crysis_message);
        sendthem =findViewById(R.id.send_them);
        location = findViewById(R.id.location_crysis);
        contactNo = findViewById(R.id.phoneNo_Crysis);

        crysisHelp = new CrysisHelp();
        firebaseAuth =FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        currentUserEmail = firebaseUser.getEmail();

        //Spinner

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.helptype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        sendthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = FirebaseDatabase.getInstance().getReference().child("CrysisHelp"); //Firebase instance

                crysisHelp.setHelpType(text);
                crysisHelp.setMessage(message.getText().toString());
                crysisHelp.setContactNo(contactNo.getText().toString());
                crysisHelp.setLocation(location.getText().toString());
                String sid = Integer.toString(ID);
                crysisHelp.setEmail(currentUserEmail);

                db.child(sid).setValue(crysisHelp);
                ID=ID+1;
                Toast.makeText(getApplicationContext(),"Thank you!!",Toast.LENGTH_SHORT).show();
                message.setText("");
                location.setText("");
                contactNo.setText("");

            }
        });


        viewhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CrisisView.class);
                startActivity(intent);

            }
        });

    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecycler<Model, ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class,
                        mref
                ){
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder,Model model, int position){
                        viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getDescripction(), model.getImage());

                    }
                };
                  mRecycleView.setAdapter(firebaseRecyclerAdapter);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        crysisAnimation.start();
    }

    //Change email method
    public String changeEmail(String email){

        String newEmail1 =  email.replace('@','0');
        String newEmail2 = newEmail1.replace('.','0');
        return newEmail2;

    }






}
