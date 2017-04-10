package com.gitgud.actortemplateapp.helper;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by martijn on 10/04/17.
 */

public class ErrorHelper extends AppCompatActivity {
    private String tag;

    public ErrorHelper(String tag){
        this.tag = tag;
    }

    public void handleException(Exception e) {
        Log.e(tag, e.getMessage());
        Snackbar.make(findViewById(android.R.id.content), "Er is iets foutgegaan, controleer log bestanden", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void handleInfo(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
