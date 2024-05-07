package com.corecraft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class WorkoutPlayAdapter extends RecyclerView.Adapter<WorkoutPlayViewHolder> {

    Context context;
    List<Workout.ExerciseDetails> exerciseDetails;

    public WorkoutPlayAdapter(Context context, List<Workout.ExerciseDetails> exerciseDetails) {
        this.context = context;
        this.exerciseDetails = exerciseDetails;
    }

    @NonNull
    @Override
    public WorkoutPlayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkoutPlayViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_workout_play,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutPlayViewHolder holder, int position) {
        final Workout.ExerciseDetails details = exerciseDetails.get(position);
        holder.exerciseImage.setImageResource(details.exercise.image);
        holder.exerciseName.setText(details.exercise.name);
        holder.exerciseAmount.setText(String.format(Locale.getDefault(),
                "%d %s X %d %s",
                details.sets,
                context.getString(R.string.sets),
                details.reps,
                context.getString(R.string.reps))
        );
    }

    @Override
    public int getItemCount() {
        return exerciseDetails.size();
    }
}
