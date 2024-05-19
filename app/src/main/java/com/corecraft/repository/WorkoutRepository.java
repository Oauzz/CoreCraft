package com.corecraft.repository;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Transaction;

import com.corecraft.database.DB;
import com.corecraft.model.ExerciseEntity;
import com.corecraft.model.ExerciseWithDetails;
import com.corecraft.model.WorkoutEntity;
import com.corecraft.model.WorkoutExercise;
import com.corecraft.model.WorkoutWithDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Dao
public class WorkoutRepository {
    private final DB db;

    public WorkoutRepository(Context context){
        this.db = DB.getInstance(context);
    }

    @Transaction
    public List<WorkoutWithDetails> getAll(){
        List<WorkoutWithDetails> all = new ArrayList<>();
        List<WorkoutEntity> workouts = db.workoutDao().getAll();
        for(final WorkoutEntity workout : workouts){
            List<ExerciseWithDetails> exerciseDetails = db.workoutExerciseDao()
                    .getById(workout.getId()).stream()
                    .map(we -> new ExerciseWithDetails(
                            db.exerciseDao().getById(we.getExerciseId()).orElseThrow(() -> new IllegalStateException("impossible !")),
                            we.getSets(),
                            we.getReps()
                    )).collect(Collectors.toList());
            final WorkoutWithDetails details = new WorkoutWithDetails(workout,exerciseDetails);
            all.add(details);
        }
        return all;
    }

    @Transaction
    public Optional<WorkoutWithDetails> getById(int workoutId){
        Optional<WorkoutEntity> workout = db.workoutDao().getById(workoutId);
        if(!workout.isPresent()){
            return Optional.empty();
        }
        List<ExerciseWithDetails> exerciseDetails = db.workoutExerciseDao()
                .getById(workoutId).stream()
                .map(we -> new ExerciseWithDetails(
                        db.exerciseDao().getById(we.getExerciseId()).orElseThrow(() -> new IllegalStateException("impossible !")),
                        we.getSets(),
                        we.getReps()
                )).collect(Collectors.toList());
        return Optional.of(new WorkoutWithDetails(workout.get(), exerciseDetails));
    }

    @Transaction
    public void save(WorkoutWithDetails workoutWithDetails){
        final WorkoutEntity workout = workoutWithDetails.getWorkout();
        db.workoutDao().save(workout);
        for(int i = 0;i < workoutWithDetails.getExercises().size();++i){
            final ExerciseWithDetails details = workoutWithDetails.getExercises().get(i);
            db.workoutExerciseDao().save(new WorkoutExercise(i,workout.getId(),details.getExercise().getId(),details.getSets(),details.getReps()));
        }
    }

    @Transaction
    public void delete(WorkoutWithDetails workoutWithDetails){
        final WorkoutEntity workout = workoutWithDetails.getWorkout();
        final List<WorkoutExercise> workoutExercises = db.workoutExerciseDao().getById(workout.getId());
        for(final WorkoutExercise workoutExercise : workoutExercises){
            db.workoutExerciseDao().delete(workoutExercise);
        }
        db.workoutDao().delete(workout);
    }
}
