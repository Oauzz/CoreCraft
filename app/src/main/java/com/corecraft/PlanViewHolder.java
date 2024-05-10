package com.corecraft;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlanViewHolder extends RecyclerView.ViewHolder {
    public int id;
    private final TextView time, title;
    private final Button del;
    public PlanViewHolder(@NonNull View itemView) {
        super(itemView);
        time = itemView.findViewById(R.id.workout_time_time);
        title = itemView.findViewById(R.id.workout_time_title);
        del = itemView.findViewById(R.id.workout_time_btn);
    }

    public TextView getTime() {
        return time;
    }

    public TextView getTitle() {
        return title;
    }

    public Button getDel() {
        return del;
    }
}
