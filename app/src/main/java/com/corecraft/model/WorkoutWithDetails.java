package com.corecraft.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class WorkoutWithDetails {
    final WorkoutEntity workout;
    final List<ExerciseWithDetails> exercises;

    public WorkoutWithDetails(WorkoutEntity workout, List<ExerciseWithDetails> exercises) {
        this.workout = workout;
        this.exercises = exercises;
    }

    public WorkoutEntity getWorkout() {
        return workout;
    }

    public List<ExerciseWithDetails> getExercises() {
        return exercises;
    }
}
