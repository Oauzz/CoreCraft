package com.corecraft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutViewHolder>{

    Context context;
    List<Workout> workouts;
    FragmentManager fragmentManager;

    public WorkoutAdapter(Context context, List<Workout> workouts,FragmentManager fragmentManager) {
        this.context = context;
        this.workouts = workouts;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkoutViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_workout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        holder.workoutName.setText(workouts.get(position).getName());
        holder.id = holder.getBindingAdapterPosition();
        holder.workout.setOnClickListener(v -> {
            WorkoutPlayFragment fragment = WorkoutPlayFragment.newInstance(holder.id);
            fragmentManager.beginTransaction()
                    .replace(R.id.home_content,fragment)
                    .addToBackStack(null)
                    .commit();
        });
        holder.workoutDel.setOnClickListener(v -> {
            workouts.remove(holder.id);
            notifyItemRemoved(holder.id);
        });
        holder.workoutEdit.setOnClickListener(v -> {
            WorkoutEditFragment fragment = WorkoutEditFragment.newInstance(holder.id);
            fragmentManager.beginTransaction()
                    .replace(R.id.home_content,fragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }
}
