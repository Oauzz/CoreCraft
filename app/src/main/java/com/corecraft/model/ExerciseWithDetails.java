package com.corecraft.model;

import androidx.room.Embedded;

public class ExerciseWithDetails {
    ExerciseEntity exercise;
    int sets;
    int reps;

    public void setExercise(ExerciseEntity exercise) {
        this.exercise = exercise;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public ExerciseWithDetails(ExerciseEntity exercise, int sets, int reps) {
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
    }

    public ExerciseEntity getExercise() {
        return exercise;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }
}