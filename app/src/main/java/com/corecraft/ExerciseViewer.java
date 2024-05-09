package com.corecraft;

import static com.corecraft.MainActivity.fragmentManager;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ExerciseViewer extends androidx.appcompat.widget.AppCompatImageView {

    public Exercise exercise;

    public ExerciseViewer(@NonNull Context context) {
        super(context);
        setOnClickListener(this::onClick);
    }

    public ExerciseViewer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this::onClick);
    }

    public ExerciseViewer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this::onClick);
    }

    public void setExercise(Exercise exercise){
        this.exercise = exercise;
        setImageResource(exercise.getImage());
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        assert exercise != null;
    }

    void onClick(View v){
        ExerciseViewDialog dialog = new ExerciseViewDialog(exercise,Math.min(((int)(getResources().getDisplayMetrics().widthPixels * 0.8)),ExerciseViewDialog.MAX_WIDTH));
        dialog.showNow(fragmentManager,"test");
//        fragmentManager.beginTransaction()
//                .add(dialog,"text")
//                .addToBackStack(null)
//                .commit();
    }

}
