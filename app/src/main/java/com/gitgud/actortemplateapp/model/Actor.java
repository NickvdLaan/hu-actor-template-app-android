package com.gitgud.actortemplateapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martijn on 04/04/17.
 */

public class Actor implements Parcelable {
    private String name;
    private String description;
    private String image;
    private List<String> USER = new ArrayList<>();

    public Actor(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    protected Actor(Parcel in) {
        name = in.readString();
        description = in.readString();
        image = in.readString();
    }

    public Actor() {
    }

    public List<String> getUSER() {
        return USER;
    }

    public void setUSER(List<String> USER) {
        this.USER = USER;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(image);
        dest.writeList(USER);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Actor> CREATOR = new Parcelable.Creator<Actor>() {
        @Override
        public Actor createFromParcel(Parcel in) {
            return new Actor(in);
        }

        @Override
        public Actor[] newArray(int size) {
            return new Actor[size];
        }
    };
}
