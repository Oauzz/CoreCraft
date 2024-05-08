package com.corecraft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WorkoutSelectAdapter extends RecyclerView.Adapter<WorkoutSelectViewHolder> {

    Context context;
    List<Exercise> orgExercises;
    List<Exercise> exercises;

    public WorkoutSelectAdapter(Context context, List<Exercise> exercises, int target, String name, boolean with_equipped) {
        this.context = context;
        this.orgExercises = exercises;
        filterExercises(target,name,with_equipped);
    }

    public synchronized void filterExercises(int target,String name, boolean with_equipped){
        this.exercises = new ArrayList<>(this.orgExercises);
        final List<Exercise> filtered_out = new ArrayList<>();
        this.exercises.forEach(exercise -> {
            if((exercise.target & target) == 0){
                filtered_out.add(exercise);
            }else if(exercise.withEquipment != with_equipped){
                filtered_out.add(exercise);
            }
        });
        if(!name.equals("")){
            this.exercises.forEach(exercise -> {
                if (!exercise.name.toLowerCase().contains(name.toLowerCase())){
                    filtered_out.add(exercise);
                }
            });
        }
        this.exercises.removeAll(filtered_out);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WorkoutSelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkoutSelectViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_workout_select,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutSelectViewHolder holder, int position) {
        final Exercise exercise = exercises.get(position);
        holder.exerciseImage.setImageResource(exercise.image);
        holder.exerciseName.setText(exercise.name);
        holder.exerciseTarget.setText(String.join(" & ",TargetMuscles.getTarget(exercise.getTarget())).concat(" muscles"));
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
}
