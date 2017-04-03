package com.gitgud.actortemplateapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class DetailFragment extends Fragment {
    String key;
    TextView tv1, tv2, tv3;
    DatabaseReference ref;

    public DetailFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        tv1 = (TextView)v.findViewById(R.id.titleView);
        tv2 = (TextView) v.findViewById(R.id.contentView);
        tv3 = (TextView) v.findViewById(R.id.dateView);
        ref = FirebaseDatabase.getInstance().getReference();
        ref.child("entries").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ProjectEntry entry = dataSnapshot.getValue(ProjectEntry.class);
                if (entry != null) {
                    tv1.setText(entry.getName());
                    tv2.setText(entry.getContent());
                    Date date = new Date(entry.getDate());
                    String datedata = DateFormat.format("MMM dd, yyyy h:mmaa", date).toString();
                    tv3.setText(datedata);
                } else {
                    tv1.setText("leeg");
                    tv2.setText("");
                    tv3.setText("");
                 }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("DetailFragment", databaseError.toString());
            }
        });
        return v;
    }

    public void setKey(String key){
        this.key = key;

    }
}
