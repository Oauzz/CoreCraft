package com.corecraft;

import static com.corecraft.MainActivity.fragmentManager;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class WorkoutEditAdapter extends RecyclerView.Adapter<WorkoutEditViewHolder> implements ItemTouchHelperContract {

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
        holder.exerciseImage.setExercise(details.getExercise());
        holder.exerciseName.setText(details.getExercise().getName());
        holder.exerciseAmount.setText(String.format(Locale.getDefault(),
                "%d %s X %d %s",
                details.getSets(),
                context.getString(R.string.sets),
                details.getReps(),
                context.getString(R.string.reps))
        );
        holder.exerciseDelete.setOnClickListener(v -> {
            exerciseDetails.remove(holder.id);
            notifyItemRemoved(holder.id);
        });
        holder.exerciseEdit.setOnClickListener(v -> {
            ExerciseEditDialog dialog = new ExerciseEditDialog(this,holder.id,details,Math.min(((int)(holder.exerciseImage.getResources().getDisplayMetrics().widthPixels * 0.8)),ExerciseEditDialog.MAX_WIDTH));
//            dialog.showNow(fragmentManager,"test");
        fragmentManager.beginTransaction()
                .add(dialog,"select")
                .addToBackStack("select")
                .commit();

        });
    }

    @Override
    public int getItemCount() {
        return exerciseDetails.size();
    }

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        Collections.swap(exerciseDetails,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }
}
