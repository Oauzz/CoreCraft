package com.corecraft.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.corecraft.model.ExerciseWithDetails;
import com.corecraft.model.WorkoutEntity;
import com.corecraft.model.WorkoutWithDetails;

import java.util.List;
import java.util.Optional;

@Dao
public interface WorkoutDao {

    @Query("SELECT * FROM workout ORDER BY id ASC")
    public List<WorkoutEntity> getAll();

    @Query("SELECT * FROM workout WHERE id = :id")
    public Optional<WorkoutEntity> getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void save(WorkoutEntity workout);

    @Delete
    public void delete(WorkoutEntity workout);
}
