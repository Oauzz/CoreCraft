package com.corecraft;

import static com.corecraft.PlanFragment.WORKOUT_ID;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TO_SELECT = "TO_SELECT";
    // TODO: Rename and change types of parameters
    private boolean toSelect;
    private FragmentManager manager = MainActivity.fragmentManager;

    public FragmentManager getManager() {
        return manager;
    }

    public void setManager(FragmentManager manager) {
        this.manager = manager;
    }

    public WorkoutFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static WorkoutFragment newInstance(boolean toSelect) {
        WorkoutFragment fragment = new WorkoutFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_TO_SELECT, toSelect);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            toSelect = getArguments().getBoolean(ARG_TO_SELECT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((TextView) requireActivity().findViewById(R.id.toolbar_title)).setText(R.string.my_workouts);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_workout_list, container, false);
        final WorkoutAdapter adapter = new WorkoutAdapter(this,toSelect,v.getContext(),Workout.WORKOUTS,manager);

        final Button addWorkBtn = v.findViewById(R.id.custom_workout_add_list_btn_new);
        addWorkBtn.setOnClickListener(tmp -> {
            final Workout workout = new Workout(Workout.WORKOUTS.size(),"NEW WORKOUT",new ArrayList<>());
            Workout.WORKOUTS.add(workout);
            adapter.notifyItemInserted(workout.getId());
        });

        RecyclerView recyclerView = v.findViewById(R.id.custom_workout_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(adapter);
        return v;
    }
}