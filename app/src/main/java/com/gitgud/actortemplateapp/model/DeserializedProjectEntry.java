package com.gitgud.actortemplateapp.model;

import java.util.ArrayList;

/**
 * Created by martijn on 04/04/17.
 */

public class DeserializedProjectEntry extends ProjectEntry {
    private ArrayList<Actor> ACTOR;
    private User USER;

    public DeserializedProjectEntry(ArrayList<Actor> actor, User user) {
        ACTOR = actor;
        USER = user;
    }

    public DeserializedProjectEntry() {
    }

    public ArrayList<Actor> getACTOR() {
        return ACTOR;
    }

    public void setACTOR(ArrayList<Actor> ACTOR) {
        this.ACTOR = ACTOR;
    }

    public User getUSER() {
        return USER;
    }

    public void setUSER(User USER) {
        this.USER = USER;
    }
}
