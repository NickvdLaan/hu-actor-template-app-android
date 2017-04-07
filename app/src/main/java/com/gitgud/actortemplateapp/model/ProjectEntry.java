package com.gitgud.actortemplateapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martijn on 04/04/17.
 */

public class ProjectEntry implements Parcelable {
    private String name;
    private String description;
    private String createdAt;
    private List<String> ACTOR = new ArrayList<>();
    private String USER;

    public ProjectEntry() {
    }

    public List<String> getACTOR() {
        return ACTOR;
    }

    public void setACTOR(List<String> ACTOR) {
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
        if (in.readByte() == 0x01) {
            ACTOR = new ArrayList<String>();
            in.readList(ACTOR, String.class.getClassLoader());
        } else {
            ACTOR = null;
        }
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
        if (ACTOR == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(ACTOR);
        }
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