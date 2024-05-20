package com.corecraft.repository;

import android.content.Context;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
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
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import kotlinx.coroutines.flow.Flow;

@Dao
public class WorkoutRepository {
    private final DB db;
    private final ExecutorService executorService;
    public WorkoutRepository(Context context){
        this.db = DB.getInstance(context);
        this.executorService = Executors.newFixedThreadPool(1);
    }

    @Transaction
    private List<WorkoutWithDetails> _getAll(){
        final List<WorkoutWithDetails> details = new ArrayList<>();
        List<WorkoutEntity> workouts = db.workoutDao().getAll();
        for(final WorkoutEntity workout : workouts){
            List<ExerciseWithDetails> exerciseDetails = db.workoutExerciseDao()
                    .getById(workout.getId()).stream().map(we -> new ExerciseWithDetails(
                            db.exerciseDao().getById(we.getExerciseId()).orElseThrow(() -> new IllegalStateException("impossible !")),
                            we.getSets(),
                            we.getReps()
                    )).collect(Collectors.toList());
            details.add(new WorkoutWithDetails(workout,exerciseDetails));
        }
        return details;
    }

    public void getAll(Callback<List<WorkoutWithDetails>> callback){
        executorService.execute(() -> {
            final List<WorkoutWithDetails> details = _getAll();
            callback.onResult(details);
        });
    }

    @Transaction
    private Optional<WorkoutWithDetails> _getById(int workoutId){
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

    public void getById(int workoutId,Callback<Optional<WorkoutWithDetails>> callback){
        executorService.execute(() -> {
            Optional<WorkoutWithDetails> workout = _getById(workoutId);
            callback.onResult(workout);
        });
    }

    @Transaction
    private void _save(WorkoutWithDetails workoutWithDetails){
        final WorkoutEntity workout = workoutWithDetails.getWorkout();
        db.workoutDao().save(workout);
        for(int i = 0;i < workoutWithDetails.getExercises().size();++i){
            final ExerciseWithDetails details = workoutWithDetails.getExercises().get(i);
            db.workoutExerciseDao().save(new WorkoutExercise(i,workout.getId(),details.getExercise().getId(),details.getSets(),details.getReps()));
        }
    }

    public void save(WorkoutWithDetails workoutWithDetails){
        executorService.execute(() -> {
            db.workoutExerciseDao().getById(workoutWithDetails.getWorkout().getId()).forEach(we -> {
                db.workoutExerciseDao().delete(we);
            });
            _save(workoutWithDetails);
        });
    }

    @Transaction
    private void _delete(WorkoutWithDetails workoutWithDetails){
        final WorkoutEntity workout = workoutWithDetails.getWorkout();
        final List<WorkoutExercise> workoutExercises = db.workoutExerciseDao().getById(workout.getId());
        for(final WorkoutExercise workoutExercise : workoutExercises){
            db.workoutExerciseDao().delete(workoutExercise);
        }
        db.workoutDao().delete(workout);
    }

    public void delete(WorkoutWithDetails workoutWithDetails){
        executorService.execute(() -> {
            _delete(workoutWithDetails);
        });
    }
}
