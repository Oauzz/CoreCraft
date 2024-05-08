package com.corecraft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class WorkoutEditAdapter extends RecyclerView.Adapter<WorkoutEditViewHolder> {

    Context context;
    List<Workout.ExerciseDetails> exerciseDetails;

    public WorkoutEditAdapter(Context context, List<Workout.ExerciseDetails> exerciseDetails) {
        this.context = context;
        this.exerciseDetails = exerciseDetails;
    }

    @NonNull
    @Override
    public WorkoutEditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkoutEditViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_workout_edit,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutEditViewHolder holder, int position) {
        final Workout.ExerciseDetails details = exerciseDetails.get(position);
        holder.id = holder.getBindingAdapterPosition();
        holder.exerciseImage.setImageResource(details.exercise.image);
        holder.exerciseName.setText(details.exercise.name);
        holder.exerciseAmount.setText(String.format(Locale.getDefault(),
                "%d %s X %d %s",
                details.sets,
                context.getString(R.string.sets),
                details.reps,
                context.getString(R.string.reps))
        );
        holder.exerciseDelete.setOnClickListener(v -> {
            exerciseDetails.remove(holder.id);
            notifyItemRemoved(holder.id);
        });

    }

    @Override
    public int getItemCount() {
        return exerciseDetails.size();
    }
}
