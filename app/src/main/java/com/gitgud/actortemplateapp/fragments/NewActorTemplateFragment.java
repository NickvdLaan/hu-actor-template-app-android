package com.gitgud.actortemplateapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.gitgud.actortemplateapp.ActorAdapter;
import com.gitgud.actortemplateapp.MainActivity;
import com.gitgud.actortemplateapp.R;
import com.gitgud.actortemplateapp.model.Actor;
import com.gitgud.actortemplateapp.model.ProjectEntry;
import com.gitgud.actortemplateapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by martijn on 04/04/17.
 */

public class NewActorTemplateFragment extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ActorAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAdapter = new ActorAdapter();


        // TODO: http://stackoverflow.com/questions/36369913/how-to-implement-multi-select-in-recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_actor_template, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.save_template) {
            try {
                TextView naam = (TextView) findViewById(R.id.naam);
                TextView omschrijving = (TextView) findViewById(R.id.omschrijving);

                if (naam.getText().length() > 0 && omschrijving.getText().length() > 0) {
                    ProjectEntry pj = new ProjectEntry();
                    pj.setUSER(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    pj.setName(naam.getText().toString());
                    pj.setDescription(omschrijving.getText().toString());
                    pj.setCreatedAt(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));

                    DatabaseReference projects = mDatabase.child("projects").push();
                    String newProjectKey = projects.getKey();
                    projects.setValue(pj);

                    Intent i = new Intent(NewActorTemplateFragment.this, AddActorsToProjectFragment.class).putExtra("project", pj).putExtra("projectKey", newProjectKey);
                    startActivity(i);
                    return true;
                } else {
                    Snackbar.make(this.findViewById(android.R.id.content), "Niet alle velden zijn ingevuld", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return false;
                }
            } catch (Exception e) {
                Snackbar.make(this.findViewById(android.R.id.content), String.format("Er is iets misgegaan met het aanmaken van het project, %s", e.getMessage()), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
