package com.corecraft;

import static com.corecraft.MainActivity.fragmentManager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Objects;

public class ExerciseEditDialog extends DialogFragment {
    public static final String REQUEST_KEY = "exercise";
    public final static int MAX_WIDTH = 960;
    public final static double GIF_ASPECT_RATIO = 540.0/960.0;

    //Of the gif
    private final int width;
    private final int height;
    private int sets,reps;

    private final Workout.ExerciseDetails details;
    private final RecyclerView.Adapter<WorkoutEditViewHolder> adapter;
    private final int holderId;

    public ExerciseEditDialog(RecyclerView.Adapter<WorkoutEditViewHolder> adapter,int id,Workout.ExerciseDetails details, int width){
        this.adapter = adapter;
        this.holderId = id;
        this.details = details;
        this.width = width;
        this.height = (int)(width*GIF_ASPECT_RATIO);
        this.sets = details.getSets();
        this.reps = details.getReps();
    }

    public Workout.ExerciseDetails getDetails(){
        return details;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_exercise_select, container, true);

        final LinearLayout pickBtn = view.findViewById(R.id.exercise_pick);
        final ImageView exImg = view.findViewById(R.id.exercise_pick_img);
        final Button subSetsBtn = view.findViewById(R.id.fragment_exercise_sets_sub);
        final EditText setsEdit = view.findViewById(R.id.fragment_exercise_sets_text);
        final Button addSetsBtn = view.findViewById(R.id.fragment_exercise_sets_add);
        final Button subRepsBtn = view.findViewById(R.id.fragment_exercise_reps_sub);
        final EditText repsEdit = view.findViewById(R.id.fragment_exercise_reps_text);
        final Button addRepsBtn = view.findViewById(R.id.fragment_exercise_reps_add);

        exImg.setImageResource(details.getExercise().getImage());
        setsEdit.setText(String.valueOf(sets));
        repsEdit.setText(String.valueOf(reps));

        subSetsBtn.setOnClickListener(v -> {
            sets = Math.max(Integer.parseInt(setsEdit.getText().toString())-1,0);
            setsEdit.setText(String.valueOf(sets));
        });
        addSetsBtn.setOnClickListener(v -> {
            sets = Integer.parseInt(setsEdit.getText().toString())+1;
            setsEdit.setText(String.valueOf(sets));
        });
        subRepsBtn.setOnClickListener(v -> {
            reps = Math.max(Integer.parseInt(repsEdit.getText().toString())-1,0);
            repsEdit.setText(String.valueOf(reps));
        });
        addRepsBtn.setOnClickListener(v -> {
            reps = Integer.parseInt(repsEdit.getText().toString())+1;
            repsEdit.setText(String.valueOf(reps));
        });
        getParentFragmentManager()
                .setFragmentResultListener("Exercise", this, (requestKey, result) -> {
                    final int id = result.getInt("id");
                    Exercise exercise = null;
                    {
                        for(Exercise ex : Exercise.EXERCISES){
                            if(ex.id == id){
                                exercise = ex;
                            }
                        }
                        assert exercise != null;
                    }
                    details.setExercise(exercise);
                    exImg.setImageResource(exercise.getImage());
                    Objects.requireNonNull(getDialog()).show();
                });
        pickBtn.setOnClickListener(v -> {
            Objects.requireNonNull(getDialog()).hide();
            WorkoutSelectFragment workoutSelectFragment = WorkoutSelectFragment.newInstance(
                    details.getExercise().getTarget(),
                    "",
                    details.getExercise().isWithEquipment(),
                    true
            );
            workoutSelectFragment.setManager(fragmentManager);
            fragmentManager.beginTransaction()
                    .replace(R.id.home_content,workoutSelectFragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        details.setReps(reps);
        details.setSets(sets);
        dismiss();
        if(adapter.getItemCount() == holderId){
            adapter.notifyItemInserted(holderId);
        }
        adapter.notifyItemChanged(holderId);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setLayout(width,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }
}
