package com.corecraft.repository;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Transaction;

import com.corecraft.database.DB;
import com.corecraft.model.ExerciseEntity;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Dao
public class ExerciseRepository {

    private final DB db;
    private final ExecutorService executorService;

    public ExerciseRepository(Context context) {
        this.db = DB.getInstance(context);
        this.executorService = Executors.newFixedThreadPool(1);
    }

    @Transaction
    private List<ExerciseEntity> _getAll(){
        return db.exerciseDao().getAll();
    }

    public void getAll(Callback<List<ExerciseEntity>> callback){
        executorService.execute(() -> {
            final List<ExerciseEntity> exerciseEntities = _getAll();
            callback.onResult(exerciseEntities);
        });
    }

    @Transaction
    private Optional<ExerciseEntity> _getById(int exerciseId){
        return db.exerciseDao().getById(exerciseId);
    }

    public void getById(int exerciseId,Callback<Optional<ExerciseEntity>> callback){
        executorService.execute(() -> {
            callback.onResult(_getById(exerciseId));
        });
    }

    @Transaction
    private void _save(ExerciseEntity exerciseEntity){
        db.exerciseDao().save(exerciseEntity);
    }

    public void save(ExerciseEntity exerciseEntity){
        executorService.execute(() -> {
            _save(exerciseEntity);
        });
    }

    @Transaction
    private void _delete(ExerciseEntity exerciseEntity){
        db.exerciseDao().delete(exerciseEntity);
    }

    public void delete(ExerciseEntity exerciseEntity){
        executorService.execute(() -> {
            _delete(exerciseEntity);
        });
    }
}
