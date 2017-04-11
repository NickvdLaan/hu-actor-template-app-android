package com.gitgud.actortemplateapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.gitgud.actortemplateapp.R;
import com.gitgud.actortemplateapp.UserAdapter;
import com.gitgud.actortemplateapp.model.Actor;
import com.gitgud.actortemplateapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowActorFragment extends AppCompatActivity {
    String actorKey;
    Actor actor;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private UserAdapter usersAdapter = null;
    final ArrayList<User> userList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_actor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        ListView lv = (ListView) findViewById(R.id.userListView);

        Intent intent = getIntent();
        actorKey = intent.getStringExtra("actorKey");
        mDatabase.child("actors").child(actorKey).addListenerForSingleValueEvent(showActorListener());

        DatabaseReference usersRef = mDatabase.child("users").child(actorKey);


        // get data from the table by the ListAdapter
        usersAdapter = new UserAdapter(this, R.layout.user_row, userList);
        lv.setAdapter(usersAdapter);
    }

    public ValueEventListener showActorListener() {
        return new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TextView tv1 = (TextView) findViewById(R.id.nameView);
                TextView tv2 = (TextView) findViewById(R.id.descriptionView);

                actor = dataSnapshot.getValue(Actor.class);
                tv1.setText(actor.getName());
                tv2.setText(actor.getDescription());

                userList.clear();

                for (String userKey : actor.getUSER()) {
                    mDatabase.child("users").child(userKey).addListenerForSingleValueEvent(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    usersAdapter.add(dataSnapshot.getValue(User.class));
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            }
                    );
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_actor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.edititem) {
            Intent i = new Intent(ShowActorFragment.this, EditActorFragment.class).putExtra("actor", actor).putExtra("key", actorKey);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }


}