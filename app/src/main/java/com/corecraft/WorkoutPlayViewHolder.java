package com.corecraft;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutPlayViewHolder extends RecyclerView.ViewHolder {
    public ImageView exerciseImage;
    public TextView exerciseName,exerciseAmount;
    public Button exerciseDone;
    public WorkoutPlayViewHolder(@NonNull View itemView) {
        super(itemView);
        exerciseImage = itemView.findViewById(R.id.exercise_play_img);
        exerciseName = itemView.findViewById(R.id.exercise_play_name);
        exerciseAmount = itemView.findViewById(R.id.exercise_play_amount);
        exerciseDone = itemView.findViewById(R.id.exercise_play_btn);

        exerciseDone.setOnClickListener(v -> {
            exerciseDone.setBackgroundResource(R.drawable.check_btn_true);
        });
    }
}
