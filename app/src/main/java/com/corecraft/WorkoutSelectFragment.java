package com.corecraft;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkoutSelectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutSelectFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String REQUEST_KEY = "filter_options";
    public static final String ARG_WORKOUT_TARGET = "workout_target";
    public static final String ARG_WORKOUT_NAME = "workout_name";
    public static final String ARG_WORKOUT_WITH_EQU = "workout_with_equ";
    public static final String ARG_TO_SELECT = "to_select";



    // TODO: Rename and change types of parameters
    private int workout_target;
    private String workout_name;
    private boolean workout_with_equ;
    private boolean toSelect;
    private RecyclerView recyclerView;

    public FragmentManager getManager() {
        return manager;
    }

    public void setManager(FragmentManager manager) {
        this.manager = manager;
    }

    private FragmentManager manager = MainActivity.fragmentManager;


    public WorkoutSelectFragment() {
        // Required empty public constructor
    }

    public static WorkoutSelectFragment newInstance(int workout_target,String workout_name,boolean workout_with_equ,boolean toSelect) {
        WorkoutSelectFragment fragment = new WorkoutSelectFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_WORKOUT_TARGET, workout_target);
        args.putString(ARG_WORKOUT_NAME, workout_name);
        args.putBoolean(ARG_WORKOUT_WITH_EQU, workout_with_equ);
        args.putBoolean(ARG_TO_SELECT,toSelect);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            workout_target = getArguments().getInt(ARG_WORKOUT_TARGET);
            workout_name = getArguments().getString(ARG_WORKOUT_NAME);
            workout_with_equ = getArguments().getBoolean(ARG_WORKOUT_WITH_EQU);
            toSelect = getArguments().getBoolean(ARG_TO_SELECT);
        }
        getParentFragmentManager()
                .setFragmentResultListener(REQUEST_KEY, this, (requestKey, result) -> {
                    workout_target = result.getInt(ARG_WORKOUT_TARGET);
                    workout_name = result.getString(ARG_WORKOUT_NAME);
                    workout_with_equ = result.getBoolean(ARG_WORKOUT_WITH_EQU);
                    ((WorkoutSelectAdapter) Objects.requireNonNull(recyclerView.getAdapter())).filterExercises(workout_target,workout_name,workout_with_equ);
                });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_workout_select_list, container, false);
        final LinearLayout filterBtn = v.findViewById(R.id.exercise_filter_btn);
        filterBtn.setOnClickListener(btn -> {
            WorkoutFilterFragment workoutFilterFragment = WorkoutFilterFragment.newInstance(workout_target,workout_name,workout_with_equ);
            manager.beginTransaction()
                    .replace(R.id.home_content,workoutFilterFragment)
                    .addToBackStack(null)
                    .commit();
        });
        recyclerView = v.findViewById(R.id.custom_workout_select_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        if (getArguments() != null) {
            recyclerView.setAdapter(new WorkoutSelectAdapter(toSelect,v.getContext(),Exercise.EXERCISES,workout_target,workout_name,workout_with_equ));
        }
        return v;
    }
}