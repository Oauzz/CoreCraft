package com.corecraft.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout")
public class WorkoutEntity {
    public static int ID = 0;
    @PrimaryKey
    int id;
    String name;

    public WorkoutEntity(String name) {
        this.id = ID;
        ID++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
