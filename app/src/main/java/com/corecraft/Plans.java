package com.corecraft;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Plans {

    public static class PlanWorkout{
        private static int ID = 0;
        private int id;
        private Workout workout;
        private Time time;

        public PlanWorkout(Workout workout, Time time) {
            this.id = ID;
            ID++;
            this.workout = workout;
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Workout getWorkout() {
            return workout;
        }

        public void setWorkout(Workout workout) {
            this.workout = workout;
        }

        public Time getTime() {
            return time;
        }

        public void setTime(Time time) {
            this.time = time;
        }
    }

    public static final List<Plans> PLANS;

    static {
        PLANS = new ArrayList<>(
                Arrays.asList(new Plans(
                                Date.valueOf("2024-05-05"),
                                new ArrayList<>(Arrays.asList(
                                        new PlanWorkout(Workout.WORKOUTS.get(0),Time.valueOf("09:00:00")),
                                        new PlanWorkout(Workout.WORKOUTS.get(1),Time.valueOf("11:00:00"))
                                ))
                ))
        );
    }
    private Date date;
    private List<PlanWorkout> workouts;

    public Plans(Date date, List<PlanWorkout> workouts) {
        this.date = date;
        this.workouts = workouts;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<PlanWorkout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<PlanWorkout> workouts) {
        this.workouts = workouts;
    }
}
