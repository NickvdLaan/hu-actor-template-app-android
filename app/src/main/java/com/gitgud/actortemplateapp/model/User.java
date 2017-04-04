package com.gitgud.actortemplateapp.model;

/**
 * Created by martijn on 04/04/17.
 */

public class User {
    private String avatar;
    private String email;
    private String image;
    private String name;
    private String provider;

    public User(String avatar, String email, String image, String name, String provider) {
        this.avatar = avatar;
        this.email = email;
        this.image = image;
        this.name = name;
        this.provider = provider;
    }

    public User(String name) {
        this.name = name;
    }

    public User(){

    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
