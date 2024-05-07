package com.corecraft;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkoutPlayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutPlayFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_WORKOUT_ID = "workout_id";

    // TODO: Rename and change types of parameters
    private int workout_id;

    public WorkoutPlayFragment() {
        // Required empty public constructor
    }

    public static WorkoutPlayFragment newInstance(int workout_id) {
        WorkoutPlayFragment fragment = new WorkoutPlayFragment();
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
        final View v = inflater.inflate(R.layout.fragment_workout_play_list, container, false);
        final RecyclerView recyclerView = v.findViewById(R.id.custom_workout_play_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        if (getArguments() != null) {
            recyclerView.setAdapter(new WorkoutPlayAdapter(v.getContext(),Workout.WORKOUTS.get(getArguments().getInt(ARG_WORKOUT_ID)).exerciseDetails));
            Toast.makeText(getContext(),"Test",Toast.LENGTH_SHORT).show();
        }
        return v;
    }
}