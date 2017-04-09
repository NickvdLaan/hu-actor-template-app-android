package com.gitgud.actortemplateapp.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.gitgud.actortemplateapp.R;
import com.gitgud.actortemplateapp.model.Actor;
import com.gitgud.actortemplateapp.model.ProjectEntry;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by martijn on 07/04/17.
 */

public class AddActorsToProjectFragment extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ProjectEntry project;
    private String projectKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_actor_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        project = getIntent().getExtras().getParcelable("project");
        projectKey = getIntent().getExtras().getString("projectKey");
        if (project == null) {
            Snackbar.make(this.findViewById(android.R.id.content), String.format("Project is niet meegeven aan %s", this.getClass().getSimpleName()), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        Snackbar.make(this.findViewById(android.R.id.content), String.format("Nieuw project: %s aangemaakt", project.getName()), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.save_actor) {
            Actor actor = new Actor();

            EditText name = (EditText) findViewById(R.id.actorName);
            EditText description = (EditText) findViewById(R.id.actorDescription);
            EditText phoneNumber = (EditText) findViewById(R.id.actorPhoneNumber);

            actor.setName(name.getText().toString());
            actor.setDescription(description.getText().toString());
            actor.setPhoneNumber(phoneNumber.getText().toString());
            DatabaseReference actors = mDatabase.child("actors").push();
            String actorsKey = actors.getKey(); // newly generated key
            actors.setValue(actor);

            mDatabase.child("projects").child(projectKey).child("ACTOR").setValue(actorsKey);

            Snackbar.make(this.findViewById(android.R.id.content), String.format("Actor: %s aangemaakt", actor.getName()), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            name.setText("");
            description.setText("");
            phoneNumber.setText("");

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_actor_project, menu);
        return true;
    }
}
