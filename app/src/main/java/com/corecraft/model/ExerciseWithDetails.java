package com.corecraft.model;

import androidx.room.Embedded;

public class ExerciseWithDetails {
    final ExerciseEntity exercise;
    final int sets;
    final int reps;

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