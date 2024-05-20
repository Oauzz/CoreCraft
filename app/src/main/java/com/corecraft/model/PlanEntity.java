package com.corecraft.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
@Entity(tableName = "plan")
public class PlanEntity {

    public static int ID = 0;
    @PrimaryKey
    int id;
    int workoutId;
    Date date;

    public PlanEntity(int workoutId, Date date) {
        this.id = ID;
        ID++;
        this.workoutId = workoutId;
        this.date = date;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
