package com.corecraft;

import static com.corecraft.MainActivity.fragmentManager;
import static com.corecraft.PlanFragment.REQUEST_KEY;
import static com.corecraft.PlanFragment.WORKOUT_ID;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlanAdapter extends RecyclerView.Adapter<PlanViewHolder> {
    private final PlanFragment planFragment;
    private final Context context;
    private final Plans plan;
    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

    public PlanAdapter(PlanFragment planFragment, Context context, Plans plan) {
        this.planFragment = planFragment;
        this.context = context;
        this.plan = plan;
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlanViewHolder(LayoutInflater.from(context).inflate(R.layout.workout_time,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        holder.id = holder.getBindingAdapterPosition();
        final Plans.PlanWorkout planWorkout = plan.getWorkouts().get(position);
        final String time = formatter.format(new Date(planWorkout.getTime().getTime()));
        holder.getTime().setText(time);
        holder.getTime().setOnClickListener(v -> {
            final TimePickerDialog dialog = new TimePickerDialog(
                    context,
                    R.style.MyTimePicker,
                    (view, hourOfDay, minute) -> {
                        planWorkout.getTime().setTime((((long)hourOfDay)*60 + ((long)minute)) * 60 * 1000);
                        notifyItemChanged(holder.id);
                    },
                    Integer.parseInt(time.substring(0,2)),
                    Integer.parseInt(time.substring(3)),
                    true
            );
            dialog.show();
        });

        holder.getTitle().setText(planWorkout.getWorkout().getName());
        holder.getTitle().setOnClickListener(v -> {
            planFragment.getParentFragmentManager().setFragmentResultListener(
                    REQUEST_KEY,planFragment,
                    (requestKey, result) -> {
                        final int exercise_id = result.getInt(WORKOUT_ID);
                        planWorkout.setWorkout(Workout.WORKOUTS.get(exercise_id));
                        notifyItemChanged(holder.id);
                    });
            WorkoutFragment workoutFragment = WorkoutFragment.newInstance(true);
            fragmentManager.beginTransaction()
                    .replace(R.id.home_content,workoutFragment)
                    .addToBackStack(null)
                    .commit();
        });

        holder.getDel().setOnClickListener(v -> {
            plan.getWorkouts().remove(holder.id);
            notifyItemRemoved(holder.id);
        });
    }

    @Override
    public int getItemCount() {
        return plan.getWorkouts().size();
    }
}
