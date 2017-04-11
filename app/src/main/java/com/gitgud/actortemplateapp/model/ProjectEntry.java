package com.gitgud.actortemplateapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by martijn on 04/04/17.
 */

public class ProjectEntry implements Parcelable {
    private String name;
    private String description;
    private String createdAt;
    private String ACTOR;
    private String USER;

    public ProjectEntry() {
    }

    public String getACTOR() {
        return ACTOR;
    }

    public void setACTOR(String ACTOR) {
        this.ACTOR = ACTOR;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    protected ProjectEntry(Parcel in) {
        name = in.readString();
        description = in.readString();
        createdAt = in.readString();
        ACTOR = in.readString();
        USER = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(createdAt);
        dest.writeString(ACTOR);
        dest.writeString(USER);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ProjectEntry> CREATOR = new Parcelable.Creator<ProjectEntry>() {
        @Override
        public ProjectEntry createFromParcel(Parcel in) {
            return new ProjectEntry(in);
        }

        @Override
        public ProjectEntry[] newArray(int size) {
            return new ProjectEntry[size];
        }
    };
}