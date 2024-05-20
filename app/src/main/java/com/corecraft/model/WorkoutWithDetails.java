package com.corecraft.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkoutWithDetails {

    public static final List<WorkoutWithDetails> WORKOUTS = new ArrayList<>();

    static {
        WORKOUTS.add(new WorkoutWithDetails(
                new WorkoutEntity("Workout 1"),
                new ArrayList<>(Arrays.asList(
                        new ExerciseWithDetails(
                                ExerciseEntity.EXERCISES.get(0),4,8
                        ),
                        new ExerciseWithDetails(
                                ExerciseEntity.EXERCISES.get(1),5,5
                        )
                ))
        ));
    }

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
