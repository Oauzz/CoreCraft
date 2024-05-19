package com.corecraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.corecraft.database.DB;
import com.corecraft.model.ExerciseEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;

    TextView home,workouts,plans,stats,bot;
    List<TextView> bottomToolbar = new ArrayList<>();
    DB db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        db = DB.getInstance(this);
        List<ExerciseEntity> exercises = db.exerciseDao().getAll();
        if(exercises.size() == 0){
            ExerciseEntity.EXERCISES.forEach(ex -> {
                db.exerciseDao().save(new ExerciseEntity(
                        ex.getName(),
                        ex.getDescription(),
                        ex.getTarget(),
                        ex.isWithEquipment(),
                        ex.getImage(),
                        ex.getVideo(),
                        ex.getInstructions()
                ));
            });
        }
        db.exerciseDao().getAll().forEach(ex -> {
            Log.e("EXERCISE",ex.toString());
        });

        ((Button) findViewById(R.id.toolbar_back_btn)).setOnClickListener(v -> {
            getSupportFragmentManager().popBackStack();
        });

        ((TextView) findViewById(R.id.toolbar_title)).setText(R.string.home);

        fragmentManager = getSupportFragmentManager();

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
            WorkoutFragment workoutFragment = WorkoutFragment.newInstance(false);
//            workoutFragment.setManager(getSupportFragmentManager());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_content,workoutFragment)
                    .commit();
        });

        plans.setOnClickListener(v -> {
            selectOnly((TextView) v);
//            DatePickerFragment datePickerFragment = new DatePickerFragment();
//            datePickerFragment.showNow(getSupportFragmentManager(),"date_picker");
            DatePickerBuilder builder = new DatePickerBuilder(this, list -> {
                PlanFragment planFragment = PlanFragment.newInstance(new java.sql.Date(list.get(0).getTimeInMillis()));
                getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_content,planFragment)
                                        .addToBackStack(null)
                                                .commit();

            })
                    .pickerType(CalendarView.ONE_DAY_PICKER)
                    .date(Calendar.getInstance()) // Initial date as Calendar object
                    .headerColor(R.color.primary_color_2) // Color of the dialog header
                    .headerLabelColor(R.color.white) // Color of the header label
                    .abbreviationsBarColor(R.color.primary_color_3) // Color of bar with day symbols
                    .abbreviationsLabelsColor(R.color.primary_color_4) // Color of symbol labels
                    .abbreviationsBarVisibility(CalendarView.VISIBLE) // Visibility of abbreviations bar
                    .pagesColor(R.color.primary_color_4) // Color of the calendar background
                    .selectionColor(R.color.primary_color_6) // Color of the selection circle
                    .selectionLabelColor(R.color.primary_color_7) // Color of the label in the circle
                    .daysLabelsColor(R.color.primary_color_1) // Color of days numbers
                    .anotherMonthsDaysLabelsColor(R.color.primary_color_5) // Color of visible days numbers from previous and next month page
                    .disabledDaysLabelsColor(R.color.primary_color_3) // Color of disabled days numbers
                    .highlightedDaysLabelsColor(R.color.white) // Color of highlighted days numbers
                    .todayColor(R.color.primary_color_4) // Color of the present day background
                    .todayLabelColor(R.color.primary_color_5) // Color of the today number
                    .dialogButtonsColor(R.color.background_color) // Color of "Cancel" and "OK" buttons
                    .typefaceSrc(R.font.carterone_regular); // Calendar font
            builder.highlightedDays(new ArrayList<>(Plans.PLANS.stream().map(p -> {
                final Calendar calendar = Calendar.getInstance();
                calendar.setTime(p.getDate());
                return calendar;
            }).distinct().collect(Collectors.toList())));

            builder.build().show();

        });

        stats.setOnClickListener(v -> {
            selectOnly((TextView) v);
            StatisticsFragment statisticsFragment = StatisticsFragment.newInstance("");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_content,statisticsFragment)
                    .addToBackStack(null)
                    .commit();
        });


        bot.setOnClickListener(v -> {
            selectOnly((TextView) v);
            WorkoutSelectFragment workoutSelectFragment = WorkoutSelectFragment.newInstance(
                    TargetMuscles.ARM | TargetMuscles.ABS | TargetMuscles.LEG
                    | TargetMuscles.BACK | TargetMuscles.CHEST | TargetMuscles.SHOULDER,
                    "",
                    true,
                    false
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