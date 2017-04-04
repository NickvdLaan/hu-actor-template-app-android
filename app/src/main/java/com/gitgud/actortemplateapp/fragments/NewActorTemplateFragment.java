package com.gitgud.actortemplateapp.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.gitgud.actortemplateapp.R;
import com.gitgud.actortemplateapp.model.ProjectEntry;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by martijn on 04/04/17.
 */

public class NewActorTemplateFragment extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference();
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

        if (id == R.id.save_actor) {
            try {
                TextView naam = (TextView) findViewById(R.id.naam);
                TextView omschrijving = (TextView) findViewById(R.id.omschrijving);

                if (naam.getText().length() > 0 && omschrijving.getText().length() > 0) {
                    ProjectEntry pj = new ProjectEntry();
                    pj.setName(naam.getText().toString());
                    pj.setDescription(omschrijving.getText().toString());
                    pj.setCreatedAt(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));

                    mDatabase.child("projects").push().setValue(pj);

                    Snackbar.make(this.findViewById(android.R.id.content), "Nieuw project aangemaakt", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1000);
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
