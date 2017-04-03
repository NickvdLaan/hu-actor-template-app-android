package com.gitgud.actortemplateapp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class EntriesAdapter extends FirebaseRecyclerAdapter<ProjectEntry, EntriesAdapter.MyViewHolder> {
    public EntriesAdapter() {
        super(ProjectEntry.class, R.layout.project_row, EntriesAdapter.MyViewHolder.class,
                FirebaseDatabase.getInstance().getReference().child("projects"));
    }


    @Override
    protected void populateViewHolder(MyViewHolder viewHolder, ProjectEntry model, int position) {
        viewHolder.title.setText(model.getName());
        Date date = new Date(model.getDate());
        String datedata = DateFormat.format("MMM dd, yyyy h:mmaa", date).toString();
        viewHolder.dateText.setText(datedata);
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
            Intent intent = new Intent(v.getContext(), ContentPager.class);

            intent.putExtra("pos", getAdapterPosition());
            v.getContext().startActivity(intent);
        }
    }
}
