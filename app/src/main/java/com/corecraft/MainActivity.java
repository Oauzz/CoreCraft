package com.corecraft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        WorkoutFragment workoutFragment = new WorkoutFragment();
        workoutFragment.setManager(getSupportFragmentManager());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_content,workoutFragment)
                .commit();
    }
}