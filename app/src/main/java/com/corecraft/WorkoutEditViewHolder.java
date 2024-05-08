package com.corecraft;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutEditViewHolder extends RecyclerView.ViewHolder {
    public int id;
    public ImageView exerciseImage;
    public TextView exerciseName,exerciseAmount;
    public Button exerciseEdit,exerciseDelete;
    public WorkoutEditViewHolder(@NonNull View itemView) {
        super(itemView);
        exerciseImage = itemView.findViewById(R.id.exercise_edit_img);
        exerciseName = itemView.findViewById(R.id.exercise_edit_name);
        exerciseAmount = itemView.findViewById(R.id.exercise_edit_amount);
        exerciseEdit = itemView.findViewById(R.id.exercise_edit_modify_btn);
        exerciseDelete = itemView.findViewById(R.id.exercise_edit_delete_btn);
    }
}
