package com.gitgud.actortemplateapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.gitgud.actortemplateapp.model.Actor;
import com.gitgud.actortemplateapp.model.ProjectEntry;
import com.google.firebase.database.FirebaseDatabase;

public class ActorAdapter extends FirebaseRecyclerAdapter<Actor, ActorAdapter.MyViewHolder> {
    public ActorAdapter() {
        super(Actor.class, R.layout.actor_row, ActorAdapter.MyViewHolder.class,
                FirebaseDatabase.getInstance().getReference().child("actors"));
    }


    @Override
    protected void populateViewHolder(MyViewHolder viewHolder, Actor model, int position) {
        viewHolder.name.setText(model.getName());
        viewHolder.description.setText(model.getDescription());
        viewHolder.key = getRef(position).getKey();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, description;
        public ProjectEntry entry;
        public String key;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
