package com.corecraft.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"order","workoutId"})
public class WorkoutExercise {
    int order;
    int workoutId;
    int exerciseId;
    int reps;
    int sets;

    public WorkoutExercise(int order, int workoutId, int exerciseId, int reps, int sets) {
        this.order = order;
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
        this.reps = reps;
        this.sets = sets;
    }

    public int getOrder() {
        return order;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public int getReps() {
        return reps;
    }

    public int getSets() {
        return sets;
    }
}
