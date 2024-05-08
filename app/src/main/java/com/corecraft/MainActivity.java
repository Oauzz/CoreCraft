package com.corecraft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView home,workouts,plans,stats,bot;
    List<TextView> bottomToolbar = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        home = findViewById(R.id.bottom_toolbar_home);
        workouts = findViewById(R.id.bottom_toolbar_workout);
        plans = findViewById(R.id.bottom_toolbar_plans);
        stats = findViewById(R.id.bottom_toolbar_stats);
        bot = findViewById(R.id.bottom_toolbar_bot);

        bottomToolbar.add(home);
        bottomToolbar.add(workouts);
        bottomToolbar.add(plans);
        bottomToolbar.add(stats);
        bottomToolbar.add(bot);

        workouts.setOnClickListener(v -> {
            selectOnly((TextView) v);
            WorkoutFragment workoutFragment = new WorkoutFragment();
            workoutFragment.setManager(getSupportFragmentManager());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_content,workoutFragment)
                    .commit();
        });

        bot.setOnClickListener(v -> {
            selectOnly((TextView) v);
            WorkoutSelectFragment workoutSelectFragment = WorkoutSelectFragment.newInstance(
                    TargetMuscles.ARM | TargetMuscles.ABS | TargetMuscles.LEG
                    | TargetMuscles.BACK | TargetMuscles.CHEST | TargetMuscles.SHOULDER,
                    "",
                    true
            );
            workoutSelectFragment.setManager(getSupportFragmentManager());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_content,workoutSelectFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    private void selectOnly(TextView v){

        final int color_on = getResources().getColor(R.color.primary_color_3,getTheme());
        final ColorStateList color_state_on = ColorStateList.valueOf(color_on);
        final int color_off = getResources().getColor(R.color.primary_color_5,getTheme());
        final ColorStateList color_state_off = ColorStateList.valueOf(color_off);

        for(final TextView textView : bottomToolbar){
            if(textView == v){
                continue;
            }
            textView.setTextColor(color_off);
            textView.setCompoundDrawableTintList(color_state_off);
        }

        v.setTextColor(color_on);
        v.setCompoundDrawableTintList(color_state_on);
    }
}