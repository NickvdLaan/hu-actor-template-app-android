package com.gitgud.actortemplateapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by martijn on 04/04/17.
 */

public class FirebaseUser implements Parcelable {
    private String username;

    public FirebaseUser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    protected FirebaseUser(Parcel in) {
        username = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<FirebaseUser> CREATOR = new Parcelable.Creator<FirebaseUser>() {
        @Override
        public FirebaseUser createFromParcel(Parcel in) {
            return new FirebaseUser(in);
        }

        @Override
        public FirebaseUser[] newArray(int size) {
            return new FirebaseUser[size];
        }
    };
}
