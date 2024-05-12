package com.corecraft;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatisticsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    public StatisticsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static StatisticsFragment newInstance(String param1) {
        StatisticsFragment fragment = new StatisticsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((TextView) requireActivity().findViewById(R.id.toolbar_title)).setText(R.string.my_stats);

        final View view = inflater.inflate(R.layout.fragment_stats, container, false);

        final Spinner spinner = view.findViewById(R.id.stats_btn_2);
        final List<String> choices = new ArrayList<>(Arrays.asList("Week","Month","Year"));
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                R.layout.dropdown_item,
                choices
        );
        adapter.setDropDownViewResource(R.layout.dropdown);
        spinner.setAdapter(adapter);

        final HorizontalBarChart barChart = view.findViewById(R.id.stats_chart_2);
        final List<Float> freq = new ArrayList<>(Arrays.asList(
                5F,10F,8F
        ));
        final List<String> labels = Exercise.EXERCISES.stream().map(Exercise::getName).collect(Collectors.toList());
        assert freq.size() == Exercise.EXERCISES.size();
        List<BarEntry> entries = new ArrayList<>();
        for(int i = 0;i < freq.size();++i){
            entries.add(new BarEntry(i,freq.get(i)));
        }
        final BarDataSet dataSet = new BarDataSet(entries,"entries");
        final BarData data = new BarData(dataSet);

        final XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labels.size());

        barChart.setFitBars(true);
        barChart.setData(data);
        barChart.invalidate();
        barChart.getDescription().setText("Bar char example");
        barChart.animateX(2000);
        return view;
    }
}