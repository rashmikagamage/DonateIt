package com.example.donateit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CrisisView extends AppCompatActivity {

    ListView messageListView;
    DatabaseReference databaseMessages;
    List<CrysisHelp> messageList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crysis_view);

        databaseMessages = FirebaseDatabase.getInstance().getReference("CrysisHelp");
        messageListView = findViewById(R.id.list_view_messages);
        messageList = new ArrayList<>();


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseMessages.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                messageList.clear();
                for(DataSnapshot messageSnapshot:dataSnapshot.getChildren() ){

                    CrysisHelp crysisHelp = messageSnapshot.getValue(CrysisHelp.class);

                    messageList.add(crysisHelp);

                }
                MessageList adapter = new MessageList(CrisisView.this, messageList);
                messageListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
