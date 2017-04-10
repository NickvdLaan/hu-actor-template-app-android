package com.gitgud.actortemplateapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.gitgud.actortemplateapp.MainActivity;
import com.gitgud.actortemplateapp.R;
import com.gitgud.actortemplateapp.model.Actor;
import com.gitgud.actortemplateapp.model.ProjectEntry;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martijn on 07/04/17.
 */

public class AddActorsToProjectFragment extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ProjectEntry project;
    private String projectKey;

    DatabaseReference actorsRef;
    List<String> selectedKeys = new ArrayList();
    ListView actorListView;
    FirebaseListAdapter actorListAdapter;

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

        actorsRef = mDatabase.child("actors");
        actorListView = (ListView) this.findViewById(R.id.actorList);
        actorListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        actorListView.setItemsCanFocus(false);


        actorListAdapter = new FirebaseListAdapter<Actor>(this,Actor.class,android.R.layout.simple_list_item_multiple_choice, actorsRef) {
            @Override
            protected void populateView(View v, Actor model, int position) {
                TextView actorName = (TextView) v.findViewById(android.R.id.text1);
                // Add actor for list
                actorName.setText(model.getName());
            }
        };
        actorListView.setAdapter(actorListAdapter);


        actorListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SparseBooleanArray sp = actorListView.getCheckedItemPositions();
                selectedKeys.clear();
                if (sp != null) {
                    for (int j=0; j < sp.size(); j++) {
                        if (sp.valueAt(j)) {
                            int position = sp.keyAt(j);
                            selectedKeys.add(actorListAdapter.getRef(position).getKey());
                        }
                    }
                }
                Log.e("start", "start");
            }
        });

        Button saveNewActor = (Button) findViewById(R.id.saveNewActor);
        saveNewActor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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

                name.setText("");
                description.setText("");
                phoneNumber.setText("");
            }
        });

        Snackbar.make(this.findViewById(android.R.id.content), String.format("Nieuw project: %s aangemaakt", project.getName()), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.save_actor) {

            mDatabase.child("projects").child(projectKey).child("ACTOR").setValue(selectedKeys);

            Log.e("start", "start");
            Intent i = new Intent(AddActorsToProjectFragment.this, MainActivity.class);
            startActivity(i);
            return true;
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
