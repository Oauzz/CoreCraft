package com.corecraft;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutSelectViewHolder extends RecyclerView.ViewHolder {
    public ImageView exerciseImage;
    public TextView exerciseName,exerciseTarget;
    public WorkoutSelectViewHolder(@NonNull View itemView) {
        super(itemView);
        exerciseImage = itemView.findViewById(R.id.exercise_select_img);
        exerciseName = itemView.findViewById(R.id.exercise_select_name);
        exerciseTarget = itemView.findViewById(R.id.exercise_select_target);
    }
}
