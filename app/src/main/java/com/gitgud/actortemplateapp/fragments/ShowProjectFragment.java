package com.gitgud.actortemplateapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gitgud.actortemplateapp.MainActivity;
import com.gitgud.actortemplateapp.R;
import com.gitgud.actortemplateapp.model.Actor;
import com.gitgud.actortemplateapp.model.ProjectEntry;
import com.gitgud.actortemplateapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowProjectFragment extends AppCompatActivity {
    String key;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        ListView lv = (ListView) findViewById(R.id.actors_show_content);
        final ArrayList<Actor> actorListView = new ArrayList<>();

        final ArrayAdapter<Actor> itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, actorListView);
        lv.setAdapter(itemsAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Actor actor = actorListView.get(position);
                Intent intent = new Intent(view.getContext(), ShowActorFragment.class);
                intent.putExtra("name", actor.getName());
                intent.putExtra("description", actor.getDescription());
                intent.putExtra("phoneNumber", actor.getPhoneNumber());
                view.getContext().startActivity(intent);
            }
        });



        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        mDatabase.child("projects").child(key).addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        TextView tv1 = (TextView) findViewById(R.id.titleView);
                        TextView tv2 = (TextView) findViewById(R.id.contentView);
                        TextView tv3 = (TextView) findViewById(R.id.dateView);

                        ProjectEntry entry = dataSnapshot.getValue(ProjectEntry.class);
                        tv1.setText(entry.getName());
                        tv2.setText(entry.getDescription());
                        tv3.setText(entry.getCreatedAt());

                        if (entry.getUSER() != null) {
                            mDatabase.child("users").child(entry.getUSER()).addListenerForSingleValueEvent(
                                    new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            TextView tv4 = (TextView) findViewById(R.id.user_show_content);
                                            User user = dataSnapshot.getValue(User.class);
                                            if (!user.getName().equals("")) {
                                                tv4.setText(String.format("User: %s", user.getName()));
                                            } else {
                                                Snackbar.make(findViewById(android.R.id.content), "Geen gebruikersnaam aan project toegevoegd",
                                                        Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    }
                            );
                        } else {
                            Snackbar.make(findViewById(android.R.id.content), "Geen gebruiker aan project toegevoegd",
                                    Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }
                        actorListView.clear();

                        for (String actor : entry.getACTOR()) {
                            mDatabase.child("actors").child(actor).addListenerForSingleValueEvent(
                                    new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            itemsAdapter.add(dataSnapshot.getValue(Actor.class));
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
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.deleteitem) {
            mDatabase.child("projects").child(key).removeValue();
            finish();
            return true;
        } else if (id == R.id.edititem) {
            Intent i = new Intent(ShowProjectFragment.this, EditProjectFragment.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


}