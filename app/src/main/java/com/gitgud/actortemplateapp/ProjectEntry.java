package com.gitgud.actortemplateapp;

/**
 * Created by JZuurbier on 23-11-2016.
 */

public class ProjectEntry {

    private String name;
    private String description;
    private String createdAt;
    String key;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
