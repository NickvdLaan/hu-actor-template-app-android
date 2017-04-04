package com.gitgud.actortemplateapp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.gitgud.actortemplateapp.fragments.ShowProjectFragment;
import com.gitgud.actortemplateapp.model.ProjectEntry;
import com.google.firebase.database.FirebaseDatabase;

public class EntriesAdapter extends FirebaseRecyclerAdapter<ProjectEntry, EntriesAdapter.MyViewHolder> {
    public EntriesAdapter() {
        super(ProjectEntry.class, R.layout.project_row, EntriesAdapter.MyViewHolder.class,
                FirebaseDatabase.getInstance().getReference().child("projects"));
    }


    @Override
    protected void populateViewHolder(MyViewHolder viewHolder, ProjectEntry model, int position) {
        viewHolder.title.setText(model.getName());
        String createdAt = model.getCreatedAt();
        viewHolder.dateText.setText(createdAt);
        viewHolder.entry = model;
        viewHolder.key = getRef(position).getKey();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, dateText;
        public ProjectEntry entry;
        public String key;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.name);
            dateText = (TextView) view.findViewById(R.id.datetext);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String datedata = entry.getCreatedAt();
            Intent intent = new Intent(v.getContext(), ShowProjectFragment.class);
            intent.putExtra("title", entry.getName());
            intent.putExtra("content", entry.getDescription());
            intent.putExtra("recorddate", datedata);
            intent.putExtra("key", key);
            v.getContext().startActivity(intent);
        }
    }
}
