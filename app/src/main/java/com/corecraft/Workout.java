package com.corecraft;

import com.corecraft.model.ExerciseWithDetails;
import com.corecraft.model.WorkoutWithDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Workout {
    public static List<Workout> WORKOUTS = new ArrayList<>();
    static {
        WORKOUTS.add(new Workout(1,"first workout", new ArrayList<>(Arrays.asList(
                new ExerciseDetails(Exercise.EXERCISES.get(0),4,8),
                new ExerciseDetails(Exercise.EXERCISES.get(2),3,12)
        ))));
        WORKOUTS.add(new Workout(2,"upper body workout", new ArrayList<>(Arrays.asList(
                new ExerciseDetails(Exercise.EXERCISES.get(0),4,10),
                new ExerciseDetails(Exercise.EXERCISES.get(1),4,8)
        ))));
        WORKOUTS.add(new Workout(3,"upper body workout", new ArrayList<>(Arrays.asList(
                new ExerciseDetails(Exercise.EXERCISES.get(0),1,10),
                new ExerciseDetails(Exercise.EXERCISES.get(1),2,9),
                new ExerciseDetails(Exercise.EXERCISES.get(2),3,8),
                new ExerciseDetails(Exercise.EXERCISES.get(0),4,7),
                new ExerciseDetails(Exercise.EXERCISES.get(1),5,6),
                new ExerciseDetails(Exercise.EXERCISES.get(2),6,5),
                new ExerciseDetails(Exercise.EXERCISES.get(0),7,4),
                new ExerciseDetails(Exercise.EXERCISES.get(1),8,3),
                new ExerciseDetails(Exercise.EXERCISES.get(2),9,2),
                new ExerciseDetails(Exercise.EXERCISES.get(0),10,1)
        ))));
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

    public static Workout fromEntity(WorkoutWithDetails workout){
        return new Workout(
                workout.getWorkout().getId(),
                workout.getWorkout().getName(),
                workout.getExercises().stream().map(ExerciseDetails::fromEntity).collect(Collectors.toList())
        );
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

        public static ExerciseDetails fromEntity(ExerciseWithDetails exercise){
            return new ExerciseDetails(
                    Exercise.fromEntity(exercise.getExercise()),
                    exercise.getSets(),
                    exercise.getReps()
            );
        }
    }
}
