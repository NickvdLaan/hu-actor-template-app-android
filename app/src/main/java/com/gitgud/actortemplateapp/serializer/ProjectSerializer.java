package com.gitgud.actortemplateapp.serializer;

import com.gitgud.actortemplateapp.model.DeserializedProjectEntry;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by martijn on 04/04/17.
 */

public class ProjectSerializer {
    private FirebaseDatabase database;

    public ProjectSerializer(){
        database = FirebaseDatabase.getInstance();
    }

    public DeserializedProjectEntry getProject(int id){
        return null;
    }
}
