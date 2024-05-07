package com.corecraft;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutViewHolder extends RecyclerView.ViewHolder{

    public int id;
    public LinearLayout workout;
    public TextView workoutName;
    public Button workoutEdit,workoutDel;
    public WorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        workoutName = itemView.findViewById(R.id.workout_item_id);
        workoutEdit = itemView.findViewById(R.id.workout_item_edit);
        workoutDel = itemView.findViewById(R.id.workout_item_del);
        workout = itemView.findViewById(R.id.fragment_workout_layout);
    }
}
