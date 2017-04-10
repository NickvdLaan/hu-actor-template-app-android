package com.gitgud.actortemplateapp.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.gitgud.actortemplateapp.ActorAdapter;
import com.gitgud.actortemplateapp.R;
import com.gitgud.actortemplateapp.model.Actor;
import com.gitgud.actortemplateapp.model.ProjectEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martijn on 07/04/17.
 */

public class AddActorsToProjectFragment extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ProjectEntry project;
    private String projectKey;
    private FirebaseListAdapter<Actor> mAdapter;
    private RecyclerView recyclerView;

    DatabaseReference actorsRef;
    List<Actor> listOfActors = new ArrayList<Actor>();
    ListView actorListView;
    ListAdapter actorListAdapter;

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
//        actorsRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot actorSnapshot: dataSnapshot.getChildren()) {
//                    Object value = actorSnapshot.getValue();
//                    Actor actor = (Actor) actorSnapshot.getValue(); //Updated line
//                    listOfActors.add(actor);
//                }
//                ArrayAdapter<Actor> adapter = new ArrayAdapter<>(AddActorsToProjectFragment.this,android.R.layout.simple_list_item_multiple_choice,  listOfActors);
//                actorListView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


        // mAdapter = new ActorAdapter();

        actorListAdapter = new FirebaseListAdapter<Actor>(this,Actor.class,android.R.layout.simple_list_item_multiple_choice, actorsRef) {
            @Override
            protected void populateView(View v, Actor model, int position) {
                TextView actorName = (TextView) v.findViewById(android.R.id.text1);
                actorName.setText(model.getName());
                listOfActors.add(position,model);
            }
        };

        actorListView.setAdapter(actorListAdapter);


        actorListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });





//        mAdapter = new FirebaseListAdapter<Actor>(this,Actor.class,android.R.layout.simple_list_item_1,mDatabase) {
//            @Override
//            protected void populateView(View view, Actor model, int position) {
//                TextView text = (TextView) view.findViewById(android.R.id.text1);
//                text.setText(a.getName());
//            }
//        };
//        final ListView lv = (ListView) findViewById(R.id.actorList);
//        lv.setAdapter(mAdapter);

//        Button addBtn = (Button) findViewById(R.id.add_button);
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mRef.push().setValue("test123");
//            }
//        });

        // TODO: http://stackoverflow.com/questions/36369913/how-to-implement-multi-select-in-recyclerview
//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);

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

            // TODO: Hier moet met de projectKey en actorsKey actoren worden toegevoegd aan het project
            //mDatabase.child("projects").child(projectKey).child("ACTOR").setValue().setValue(true);

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
