package com.example.donateit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageList extends ArrayAdapter<CrysisHelp> {

    private Activity context;
    private List<CrysisHelp> messageList;

    public MessageList(Activity context, List<CrysisHelp> messageList) {

        super(context, R.layout.list_layout, messageList);
        this.context = context;
        this.messageList = messageList;

    }


    @Override
    public View getView(int position, View contextView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView message = listViewItem.findViewById(R.id.message_list);
        TextView contactNo = listViewItem.findViewById(R.id.contactNo_list);
        TextView location = listViewItem.findViewById(R.id.location_list);

        CrysisHelp crysisHelp = messageList.get(position);

        message.setText(crysisHelp.getMessage());
        contactNo.setText(crysisHelp.getContactNo());
        location.setText(crysisHelp.getLocation());

        return listViewItem;


    }
}

