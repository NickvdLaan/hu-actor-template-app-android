package com.gitgud.actortemplateapp.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.gitgud.actortemplateapp.R;
import com.gitgud.actortemplateapp.model.ProjectEntry;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by martijn on 07/04/17.
 */

public class AddActorsToProjectFragment extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ProjectEntry project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        project = getIntent().getExtras().getParcelable("project");
        if (project == null) {
            Snackbar.make(this.findViewById(android.R.id.content), String.format("Project is niet meegeven aan %s", this.getClass().getSimpleName()), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        Snackbar.make(this.findViewById(android.R.id.content), String.format("Nieuw project: %s aangemaakt", project.getName()), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
