package com.corecraft;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ExerciseViewDialog extends DialogFragment {

    public final static int MAX_WIDTH = 960;
    public final static double GIF_ASPECT_RATIO = 540.0/960.0;

    //Of the gif
    private final int width;
    private final int height;

    private final Exercise exercise;

    public ExerciseViewDialog(Exercise exercise, int width){
        this.exercise = exercise;
        this.width = width;
        this.height = (int)(width*GIF_ASPECT_RATIO);
    }

    public Exercise getExercise(){
        return exercise;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_exercise_view, container, true);

        final ImageView gifView = view.findViewById(R.id.exercise_gif);
        final TextView descText = view.findViewById(R.id.exercise_desc);
        final TextView instrText = view.findViewById(R.id.exercise_instr);
        final Button okBtn = view.findViewById(R.id.exercise_ok_btn);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        layoutParams.gravity = Gravity.CENTER;
        gifView.setLayoutParams(layoutParams);
        gifView.requestLayout();
        Glide.with(this).asGif().load(exercise.getVideo()).apply(new RequestOptions().override(width,height)).into(gifView);
        descText.setText(exercise.getDescription());
        instrText.setText(exercise.getInstructions());
        okBtn.setOnClickListener(v -> {
            dismiss();
        });

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setLayout(width,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }
}
