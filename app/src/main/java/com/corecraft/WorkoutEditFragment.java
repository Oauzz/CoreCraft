package com.corecraft;

import static com.corecraft.MainActivity.fragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkoutEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutEditFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_WORKOUT_ID = "workout_id";

    // TODO: Rename and change types of parameters
    private int workout_id;

    public WorkoutEditFragment() {
        // Required empty public constructor
    }

    public static WorkoutEditFragment newInstance(int workout_id) {
        WorkoutEditFragment fragment = new WorkoutEditFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_WORKOUT_ID, workout_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            workout_id = getArguments().getInt(ARG_WORKOUT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_workout_edit_list, container, false);
        final Button addEx = v.findViewById(R.id.custom_workout_edit_list_btn_new);

        final RecyclerView recyclerView = v.findViewById(R.id.custom_workout_edit_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        if (getArguments() != null) {
            final int id = getArguments().getInt(ARG_WORKOUT_ID);
            final Workout workout = Workout.WORKOUTS.get(id);
            final WorkoutEditAdapter adapter = new WorkoutEditAdapter(v.getContext(),workout.exerciseDetails);
            recyclerView.setAdapter(adapter);

            ItemTouchHelper.Callback callback = new ItemMoveCallback(adapter);
            ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
            touchHelper.attachToRecyclerView(recyclerView);

            final TextView title = v.findViewById(R.id.custom_workout_edit_list_name);
            title.setText(workout.getName());
            title.setOnFocusChangeListener((v1, hasFocus) -> {
                final TextView t = (TextView) v1;
                if(!hasFocus){
                    workout.setName(t.getText().toString());
                }
            });

            addEx.setOnClickListener(tmp -> {
                Workout.ExerciseDetails details = new Workout.ExerciseDetails(Exercise.EXERCISES.get(0),4,8);
                workout.exerciseDetails.add(details);
                ExerciseEditDialog dialog = new ExerciseEditDialog(adapter,adapter.getItemCount(),details,Math.min(((int)(getResources().getDisplayMetrics().widthPixels * 0.8)),ExerciseEditDialog.MAX_WIDTH));
//            dialog.showNow(fragmentManager,"test");
                fragmentManager.beginTransaction()
                        .add(dialog,"select")
                        .addToBackStack("select")
                        .commit();
            });
        }
        return v;
    }
}