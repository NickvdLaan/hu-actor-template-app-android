package com.gitgud.actortemplateapp;

/**
 * Created by JZuurbier on 23-11-2016.
 */

public class ProjectEntry {

    private String name;
    private String content;
    private long date;
    String key;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

}
