package com.example.donateit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HowTo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);
    }

    public void browser1(View view){
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.socialservices.gov.lk/web/index.php?lang=en"));
        startActivity(browserIntent);
    }
    public void goToAddVolunteer( View view){
        Intent intent = new Intent(this,addVolunteer.class);
        startActivity(intent);
    }


}
