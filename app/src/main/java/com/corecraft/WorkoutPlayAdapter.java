package com.corecraft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class WorkoutPlayAdapter extends RecyclerView.Adapter<WorkoutPlayAdapter.WorkoutPlayViewHolder> {

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
        holder.current = holder.getBindingAdapterPosition();
        final Workout.ExerciseDetails details = exerciseDetails.get(position);
        holder.exerciseImage.setExercise(details.getExercise());
        holder.exerciseName.setText(details.exercise.getName());
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

    public static class WorkoutPlayViewHolder extends RecyclerView.ViewHolder {
        public int current = 0;
        public static int amount = 0;
        private boolean completed = false;
        public ExerciseViewer exerciseImage;
        public TextView exerciseName,exerciseAmount;
        public Button exerciseDone;
        public WorkoutPlayViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseImage = itemView.findViewById(R.id.exercise_play_img);
            exerciseName = itemView.findViewById(R.id.exercise_play_name);
            exerciseAmount = itemView.findViewById(R.id.exercise_play_amount);
            exerciseDone = itemView.findViewById(R.id.exercise_play_btn);

            exerciseDone.setOnClickListener(v -> {
                if(current == amount) {
                    exerciseDone.setBackgroundResource(R.drawable.check_btn_true);
                    completed = true;
                    ++amount;
                }else {
                    Toast.makeText(v.getContext(),"Complete the exercises in order !",Toast.LENGTH_SHORT).show();
                }
            });
        }

        public boolean isCompleted(){
            return completed;
        }
    }
}
