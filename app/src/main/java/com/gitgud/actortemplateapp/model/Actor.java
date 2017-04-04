package com.gitgud.actortemplateapp.model;

/**
 * Created by martijn on 04/04/17.
 */

public class Actor {
    private String name;
    private String description;

    public Actor(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Actor() {
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
}
