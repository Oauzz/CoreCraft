package com.corecraft.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.corecraft.model.PlanEntity;

@Dao
public interface PlanDao {

    @Query("SELECT * FROM `plan`")
    public PlanEntity getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void save(PlanEntity plan);

    @Delete
    public void delete(PlanEntity plan);
}
