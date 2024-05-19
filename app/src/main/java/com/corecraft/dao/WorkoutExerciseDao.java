package com.corecraft.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.corecraft.model.WorkoutExercise;

import java.util.List;
import java.util.Optional;

@Dao
public interface WorkoutExerciseDao {

    @Query("SELECT * FROM WorkoutExercise ORDER BY `order` ASC")
    public List<WorkoutExercise> getAll();

    @Query("SELECT * FROM WorkoutExercise WHERE workoutId = :workoutId ORDER BY `order` ASC")
    public List<WorkoutExercise> getById(int workoutId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void save(WorkoutExercise workoutExercise);

    @Delete
    public void delete(WorkoutExercise workoutExercise);
}
