package com.gitgud.actortemplateapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.gitgud.actortemplateapp.MainActivity;
import com.gitgud.actortemplateapp.R;
import com.gitgud.actortemplateapp.model.ProjectEntry;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProjectFragment extends AppCompatActivity {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    String key;
    EditText tv1, tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        ProjectEntry projectEntry = intent.getParcelableExtra("project");
        key = intent.getStringExtra("key");

        tv1 = (EditText) findViewById(R.id.titleView);
        tv2 = (EditText) findViewById(R.id.contentView);

        tv1.setText(projectEntry.getName());
        tv2.setText(projectEntry.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_actor_template, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.save_template) {
            DatabaseReference project = mDatabase.child("projects").child(key);

            project.child("name").setValue(tv1.getText().toString());
            project.child("description").setValue(tv2.getText().toString());

            Intent i = new Intent(EditProjectFragment.this, MainActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}