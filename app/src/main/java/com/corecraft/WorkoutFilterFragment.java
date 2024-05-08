package com.corecraft;

import static com.corecraft.WorkoutSelectFragment.ARG_WORKOUT_NAME;
import static com.corecraft.WorkoutSelectFragment.ARG_WORKOUT_TARGET;
import static com.corecraft.WorkoutSelectFragment.ARG_WORKOUT_WITH_EQU;
import static com.corecraft.WorkoutSelectFragment.REQUEST_KEY;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkoutFilterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutFilterFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private int workout_target;
    private String workout_name;
    private boolean workout_with_equ = true;

    private EditText name;
    private TextView equ;
    private List<Pair> target_muscles;

    public WorkoutFilterFragment() {
        // Required empty public constructor
    }

    public static WorkoutFilterFragment newInstance(int workout_target, String workout_name, boolean workout_with_equ) {
        WorkoutFilterFragment fragment = new WorkoutFilterFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_WORKOUT_TARGET, workout_target);
        args.putString(ARG_WORKOUT_NAME, workout_name);
        args.putBoolean(ARG_WORKOUT_WITH_EQU, workout_with_equ);
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
        }
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if(name != null){
                    workout_name = name.getText().toString();
                }
                Bundle args = new Bundle();
                args.putInt(ARG_WORKOUT_TARGET, workout_target);
                args.putString(ARG_WORKOUT_NAME, workout_name);
                args.putBoolean(ARG_WORKOUT_WITH_EQU, workout_with_equ);
                getParentFragmentManager().setFragmentResult(REQUEST_KEY,args);
                getParentFragmentManager().popBackStack();
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(this,callback);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_workout_filter, container, false);
        name = v.findViewById(R.id.fragment_workout_filter_name);
        name.setText(workout_name);
        equ = v.findViewById(R.id.fragment_workout_filter_equipment);
        {
            assert equ.getCompoundDrawables()[0] != null;
            final Rect bounds = equ.getCompoundDrawables()[0].getBounds();
            final Drawable stateOff = ResourcesCompat.getDrawable(getResources(),R.drawable.check_btn_false,null);
            assert stateOff != null;
            stateOff.setBounds(bounds);
            final Drawable stateOn = ResourcesCompat.getDrawable(getResources(),R.drawable.check_btn_true,null);
            assert stateOn != null;
            stateOn.setBounds(bounds);
            if(workout_with_equ){
                equ.setCompoundDrawables(stateOn,null,null,null);
            }else{
                equ.setCompoundDrawables(stateOff,null,null,null);
            }
        }

        equ.setOnClickListener(radio -> {
            assert equ.getCompoundDrawables()[0] != null;
            final Rect bounds = equ.getCompoundDrawables()[0].getBounds();
            final Drawable stateOff = ResourcesCompat.getDrawable(getResources(),R.drawable.check_btn_false,null);
            assert stateOff != null;
            stateOff.setBounds(bounds);
            final Drawable stateOn = ResourcesCompat.getDrawable(getResources(),R.drawable.check_btn_true,null);
            assert stateOn != null;
            stateOn.setBounds(bounds);
            final TextView view = (TextView) radio;
            workout_with_equ = !workout_with_equ;
            if(workout_with_equ){
                view.setCompoundDrawables(stateOn,null,null,null);
            }else{
                view.setCompoundDrawables(stateOff,null,null,null);
            }
        });
        target_muscles = new ArrayList<>(Arrays.asList(
            new Pair(v.findViewById(R.id.fragment_workout_back)),
            new Pair(v.findViewById(R.id.fragment_workout_chest)),
            new Pair(v.findViewById(R.id.fragment_workout_arms)),
            new Pair(v.findViewById(R.id.fragment_workout_legs)),
            new Pair(v.findViewById(R.id.fragment_workout_shoulder)),
            new Pair(v.findViewById(R.id.fragment_workout_abs))
        ));
        for(int i = 0;i < target_muscles.size();++i){
            final int I = i;
            final Pair tmp = target_muscles.get(I);
            tmp.selected = ((workout_target & (1 << I)) != 0);
            if(tmp.selected){
                tmp.img.setBackgroundResource(R.drawable.target_img_selected);
            }else{
                tmp.img.setBackgroundResource(R.drawable.target_img);
            }
            target_muscles.get(I).body.setOnClickListener(t -> {
                final Pair pair = target_muscles.get(I);
                if(pair.selected){
                    pair.img.setBackgroundResource(R.drawable.target_img);
                    workout_target = workout_target ^ (1 << I);
                }else{
                    pair.img.setBackgroundResource(R.drawable.target_img_selected);
                    workout_target = workout_target | (1 << I);
                }
                pair.selected = !pair.selected;
            });
        }
        return v;
    }

    private static class Pair{
        public LinearLayout body;
        public ImageView img;
        public boolean selected;

        public Pair(LinearLayout body){
            this.body = body;
            this.selected = true;
            this.img = (ImageView) body.getChildAt(0);
        }
    }
}