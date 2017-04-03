package com.gitgud.actortemplateapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContentPager extends AppCompatActivity {

    ViewPager vp;
    DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
    MyPagerAdapter adapter;
    int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_pager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        vp = (ViewPager) findViewById(R.id.pager);
        adapter = new MyPagerAdapter(this.getSupportFragmentManager());
        vp.setAdapter(adapter);
        Intent intent = getIntent();
        pos = intent.getIntExtra("pos", 0);
        vp.setCurrentItem(pos);
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
            Log.v("ContentPager delete", "deleting..." );
            int i = vp.getCurrentItem();
            String key = adapter.keyList.get(i);
            Log.v("ContentPager delete", "De key is" + key);
            mFirebaseDatabaseReference.child("entries").child(key).removeValue();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {
        public  List<String> keyList;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            keyList = new ArrayList<>();

            mFirebaseDatabaseReference.child("entries").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    keyList.add(dataSnapshot.getKey());
                    notifyDataSetChanged();
                    vp.setCurrentItem(pos);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Log.d("PagerAdapter", "remove");
                    keyList.remove( dataSnapshot.getKey());
                    notifyDataSetChanged();
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("ContentPager", databaseError.toString());
                }
            });
        }

        @Override
        public Fragment getItem(int i) {
            DetailFragment f = new DetailFragment();
            f.setKey(keyList.get(i));
            return f;

        }

        @Override
        public int getCount(){
            return keyList.size();
        }

    }

}
