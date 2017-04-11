package com.gitgud.actortemplateapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martijn on 04/04/17.
 */

public class Actor {
    private String name;
    private String description;
    private String image;
    private List<String> USER = new ArrayList<>();

    public Actor(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
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
}
