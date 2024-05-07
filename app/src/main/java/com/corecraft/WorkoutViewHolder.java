package com.corecraft;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutViewHolder extends RecyclerView.ViewHolder{

    public TextView workoutName;
    public Button workoutEdit,workoutDel;
    public WorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        workoutName = itemView.findViewById(R.id.workout_item_id);
        workoutEdit = itemView.findViewById(R.id.workout_item_edit);
        workoutDel = itemView.findViewById(R.id.workout_item_del);
        itemView.setOnClickListener(v -> {
            Toast.makeText(itemView.getContext(),"Hello",Toast.LENGTH_SHORT).show();
        });
    }
}
