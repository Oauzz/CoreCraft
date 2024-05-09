package com.corecraft;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutPlayViewHolder extends RecyclerView.ViewHolder {
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
