package com.corecraft;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.lang.reflect.Field;


public class sf_step1 extends AppCompatActivity {

    ProgressBar pb ;

    Spinner spinner ;
    //Button btn ;
    TextView genderTxt ;
    TextView ageTxt ;

    int CurrentProgress = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sf_step1);

        int darkColor = ContextCompat.getColor(this, R.color.notimportant);
        int LightColor = ContextCompat.getColor(this, R.color.white);

        genderTxt = findViewById(R.id.sf_step1_text1);
        ageTxt = findViewById(R.id.sf_step1_text2);

        String gender_Text = getString(R.string.sf_step1_gender);
        String age_Text = getString(R.string.sf_step1_age);


        SpannableString spannableStr1 = new SpannableString(gender_Text);
        SpannableString spannableStr2 = new SpannableString(age_Text);

        ForegroundColorSpan darkSpan = new ForegroundColorSpan(darkColor);
        ForegroundColorSpan lightSpan = new ForegroundColorSpan(LightColor);

        spannableStr1.setSpan(lightSpan, 0, gender_Text.indexOf("Please "), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStr2.setSpan(lightSpan, 0, age_Text.indexOf("Please"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStr1.setSpan(new AbsoluteSizeSpan(36, true), 0, gender_Text.indexOf("Please "), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStr2.setSpan(new AbsoluteSizeSpan(36, true), 0, age_Text.indexOf("Please"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        int start1 = gender_Text.indexOf("Please");
        int end1 = start1 + "Please provide your gender".length();
        int start2 = age_Text.indexOf("Please");
        int end2 = start2 + "Please provide your age".length();

        spannableStr1.setSpan(darkSpan, start1, end1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStr2.setSpan(darkSpan, start2, end2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        genderTxt.setText(spannableStr1);
        ageTxt.setText(spannableStr2);

        pb = findViewById(R.id.sf_progressBar1);

        spinner = findViewById(R.id.sf_spinner1);
        spinner.setPopupBackgroundResource(R.drawable.dropdown_menu);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.sf1_dd1,
                R.layout.dropdown_item
        );
        spinner.setAdapter(adapter);


        pb.setMax(6);
        pb.setProgress(CurrentProgress);
        //btn = findViewById(R.id.btn);

        /*btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                CurrentProgress= CurrentProgress+1;
                pb.setProgress(CurrentProgress);
            }
        });*/
    };



}
