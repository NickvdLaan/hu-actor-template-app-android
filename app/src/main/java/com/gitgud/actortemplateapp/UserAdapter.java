package com.gitgud.actortemplateapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gitgud.actortemplateapp.model.User;

import java.util.List;

/**
 * Created by Nick on 11-4-2017.
 */

public class UserAdapter extends ArrayAdapter<User> {

    public UserAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public UserAdapter(Context context, int resource, List<User> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.user_row, null);
        }

        User user = getItem(position);

        if (user != null) {
            TextView tv1 = (TextView) v.findViewById(R.id.name);
            TextView tv2 = (TextView) v.findViewById(R.id.email);
            TextView tv3 = (TextView) v.findViewById(R.id.phoneView);

            // Add user for list
            tv1.setText(user.getName());
            if (user.getEmail() != null) {
                tv2.setText(user.getEmail());
            } else {
                ImageView email = (ImageView) v.findViewById(R.id.emailIcon);
                email.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
            }
            if (user.getPhoneNumber() != null) {
                tv3.setText(user.getPhoneNumber());
            } else {
                ImageView phone = (ImageView) v.findViewById(R.id.phoneIcon);
                phone.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
            }
        }

        return v;
    }

}