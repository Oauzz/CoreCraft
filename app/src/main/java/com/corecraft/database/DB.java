package com.corecraft.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.corecraft.dao.ExerciseDao;
import com.corecraft.dao.WorkoutDao;
import com.corecraft.dao.WorkoutExerciseDao;
import com.corecraft.model.ExerciseEntity;
import com.corecraft.model.WorkoutEntity;
import com.corecraft.model.WorkoutExercise;

@Database(
        entities = {ExerciseEntity.class, WorkoutEntity.class, WorkoutExercise.class},
        version = 1
)
public abstract class DB extends RoomDatabase {
    private static final String DB_NAME = "CORE_CRAFT_DB";
    private static DB instance = null;

    public abstract ExerciseDao exerciseDao();
    public abstract WorkoutDao workoutDao();
    public abstract WorkoutExerciseDao workoutExerciseDao();

    protected DB(){}

    public static synchronized DB getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,DB.class,DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
