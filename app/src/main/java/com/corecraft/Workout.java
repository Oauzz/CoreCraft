package com.corecraft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Workout {
    public static final List<Workout> WORKOUTS = new ArrayList<>();
    static {
        WORKOUTS.add(new Workout(1,"first workout", Arrays.asList(
                new ExerciseDetails(Exercise.EXERCISES.get(0),4,8),
                new ExerciseDetails(Exercise.EXERCISES.get(2),3,12)
        )));
        WORKOUTS.add(new Workout(2,"upper body workout", Arrays.asList(
                new ExerciseDetails(Exercise.EXERCISES.get(0),4,10),
                new ExerciseDetails(Exercise.EXERCISES.get(1),4,8)
        )));
    }
    int id;
    String name;
    List<ExerciseDetails> exerciseDetails;

    public Workout(int id, String name, List<ExerciseDetails> exerciseDetails) {
        this.id = id;
        this.name = name;
        this.exerciseDetails = exerciseDetails;
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

    public List<ExerciseDetails> getExerciseDetails() {
        return exerciseDetails;
    }

    public void setExerciseDetails(List<ExerciseDetails> exerciseDetails) {
        this.exerciseDetails = exerciseDetails;
    }

    public static class ExerciseDetails{
        Exercise exercise;
        int sets;
        int reps;

        public ExerciseDetails(Exercise exercise, int sets, int reps) {
            this.exercise = exercise;
            this.sets = sets;
            this.reps = reps;
        }

        public Exercise getExercise() {
            return exercise;
        }

        public void setExercise(Exercise exercise) {
            this.exercise = exercise;
        }

        public int getSets() {
            return sets;
        }

        public void setSets(int sets) {
            this.sets = sets;
        }

        public int getReps() {
            return reps;
        }

        public void setReps(int reps) {
            this.reps = reps;
        }
    }
}
