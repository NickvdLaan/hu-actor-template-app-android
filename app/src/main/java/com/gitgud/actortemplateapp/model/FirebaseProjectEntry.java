package com.gitgud.actortemplateapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JZuurbier on 23-11-2016.
 */

public class FirebaseProjectEntry extends ProjectEntry {
    private List<String> ACTOR = new ArrayList<>();
    private String USER;

    public FirebaseProjectEntry() {
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
}
