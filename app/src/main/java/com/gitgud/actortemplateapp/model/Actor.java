package com.gitgud.actortemplateapp.model;

/**
 * Created by martijn on 04/04/17.
 */

public class Actor {
    private String name;
    private String description;
    private String phoneNumber;
    private String image;

    public Actor(String name, String description, String phoneNumber, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.phoneNumber = phoneNumber;
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

    @Override
    public String toString() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
