package com.corecraft;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TIME_LONG = "date";
    // TODO: Rename and change types of parameters
    private final Date date = new Date(0);
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public PlanFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PlanFragment newInstance(Date date) {
        PlanFragment fragment = new PlanFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_TIME_LONG, date.getTime());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            date.setTime(getArguments().getLong(ARG_TIME_LONG));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.workout_time_list, container, false);
        ((TextView) view.findViewById(R.id.workout_date_title)).setText(
                formatter.format(date)
        );

        Plans plans = null;
        {
            for(final Plans p : Plans.PLANS){
                if(p.getDate().getTime() == date.getTime()){
                    plans = p;
                }
            }
            if(plans == null){
                plans = new Plans(date,new ArrayList<>());
                Toast.makeText(getContext(),"Here",Toast.LENGTH_SHORT).show();
            }
        }
        final PlanAdapter adapter = new PlanAdapter(getContext(),plans);
        final RecyclerView recyclerView = view.findViewById(R.id.workout_time_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        final Button addBtn = view.findViewById(R.id.workout_time_add_btn);
        addBtn.setOnClickListener(v -> {

        });
        return view;
    }
}