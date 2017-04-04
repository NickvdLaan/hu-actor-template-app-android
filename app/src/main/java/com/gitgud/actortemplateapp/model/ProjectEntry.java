package com.gitgud.actortemplateapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martijn on 04/04/17.
 */

public class ProjectEntry {
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
}
