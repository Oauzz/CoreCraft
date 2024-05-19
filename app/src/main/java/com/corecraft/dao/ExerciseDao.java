package com.corecraft.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.corecraft.model.ExerciseEntity;

import java.util.List;
import java.util.Optional;

@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM exercise ORDER BY id ASC")
    public List<ExerciseEntity> getAll();

    @Query("SELECT * FROM exercise WHERE id = :id")
    public Optional<ExerciseEntity> getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void save(ExerciseEntity exercise);

    @Delete
    public void delete(ExerciseEntity exercise);
}
