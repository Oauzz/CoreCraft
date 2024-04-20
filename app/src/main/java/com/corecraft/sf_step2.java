package com.corecraft;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class sf_step2 extends AppCompatActivity {

    ProgressBar pb ;

    LinearLayout height_calc1 ;
    LinearLayout height_calc2 ;

    TextView HeightTxt ;
    TextView WeightTxt ;
    TextView weight_unit ;
    TextView height_unit ;

    Button left;
    Button right;
    int CurrentProgress = 2;

    int[] units ={R.id.sf_step2_metric,R.id.sf_step2_imp};
    int CurrentUnit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sf_step2);

        height_calc1 = findViewById(R.id.sf_step2_calc1) ;
        height_calc2 = findViewById(R.id.sf_step2_calc3) ;

        height_calc1.setVisibility(View.VISIBLE);
        height_calc2.setVisibility(View.GONE);


        left = findViewById(units[0]);
        right = findViewById(units[1]);
        Drawable backgroundDrawableLeft = left.getBackground();
        Drawable backgroundDrawableRight = right.getBackground();

        height_unit = findViewById(R.id.sf_step2_textView1);
        weight_unit = findViewById(R.id.sf_step2_textView2);

        String heightUnit_Text = getString(R.string.sf_step2_metImp1);
        String weightUnit_Text = getString(R.string.sf_step2_metImp2);

        int noSeleColor = ContextCompat.getColor(this, R.color.white);
        int SeleColor = ContextCompat.getColor(this, R.color.sfSelect);


        SpannableString spannableStr01 = new SpannableString(heightUnit_Text);
        SpannableString spannableStr02 = new SpannableString(weightUnit_Text);

        ForegroundColorSpan darkSpan0 = new ForegroundColorSpan(noSeleColor);
        ForegroundColorSpan lightSpan0 = new ForegroundColorSpan(SeleColor);


        int start01 = heightUnit_Text.indexOf("/");
        int end01 = start01 + "/ ft & in".length();
        int start02 = weightUnit_Text.indexOf("/");
        int end02 = start02 + "/ Lbs".length();

        spannableStr01.setSpan(lightSpan0, 0, heightUnit_Text.indexOf("/ "), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStr02.setSpan(lightSpan0, 0, weightUnit_Text.indexOf("/"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableStr01.setSpan(darkSpan0, start01, end01, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStr02.setSpan(darkSpan0, start02, end02, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        height_unit.setText(spannableStr01);
        weight_unit.setText(spannableStr02);

        CurrentUnit = units[0];
        left.isSelected();

        if (backgroundDrawableLeft instanceof GradientDrawable) {
            // Cast the background drawable to GradientDrawable
            GradientDrawable gradientDrawable = (GradientDrawable) backgroundDrawableLeft;

            // Change the solid color of the GradientDrawable
            gradientDrawable.setColor(ContextCompat.getColor(this, R.color.sfSelect)); // Replace R.color.new_solid_color with the color you want
        }
        if (backgroundDrawableRight instanceof GradientDrawable) {
            // Cast the background drawable to GradientDrawable
            GradientDrawable gradientDrawable = (GradientDrawable) backgroundDrawableRight;

            // Change the solid color of the GradientDrawable
            gradientDrawable.setColor(ContextCompat.getColor(this, R.color.sfNoSelect)); // Replace R.color.new_solid_color with the color you want
        }

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentUnit = units[1];
                right.isSelected();

                height_calc1.setVisibility(View.GONE);
                height_calc2.setVisibility(View.VISIBLE);

                if (backgroundDrawableLeft instanceof GradientDrawable) {
                    // Cast the background drawable to GradientDrawable
                    GradientDrawable gradientDrawable = (GradientDrawable) backgroundDrawableLeft;

                    // Change the solid color of the GradientDrawable
                    gradientDrawable.setColor(ContextCompat.getColor(getBaseContext(), R.color.sfNoSelect)); // Replace R.color.new_solid_color with the color you want
                }
                if (backgroundDrawableRight instanceof GradientDrawable) {
                    // Cast the background drawable to GradientDrawable
                    GradientDrawable gradientDrawable = (GradientDrawable) backgroundDrawableRight;

                    // Change the solid color of the GradientDrawable
                    gradientDrawable.setColor(ContextCompat.getColor(getBaseContext(), R.color.sfSelect)); // Replace R.color.new_solid_color with the color you want
                }

                spannableStr01.setSpan(darkSpan0, 0, heightUnit_Text.indexOf("/ "), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStr02.setSpan(darkSpan0, 0, weightUnit_Text.indexOf("/"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                spannableStr01.setSpan(lightSpan0, start01, end01, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStr02.setSpan(lightSpan0, start02, end02, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                height_unit.setText(spannableStr01);
                weight_unit.setText(spannableStr02);


            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentUnit = units[0];
                left.isSelected();
                height_calc1.setVisibility(View.VISIBLE);
                height_calc2.setVisibility(View.GONE);

                if (backgroundDrawableLeft instanceof GradientDrawable) {
                    // Cast the background drawable to GradientDrawable
                    GradientDrawable gradientDrawable = (GradientDrawable) backgroundDrawableLeft;

                    // Change the solid color of the GradientDrawable
                    gradientDrawable.setColor(ContextCompat.getColor(getBaseContext(), R.color.sfSelect)); // Replace R.color.new_solid_color with the color you want
                }
                if (backgroundDrawableRight instanceof GradientDrawable) {
                    // Cast the background drawable to GradientDrawable
                    GradientDrawable gradientDrawable = (GradientDrawable) backgroundDrawableRight;

                    // Change the solid color of the GradientDrawable
                    gradientDrawable.setColor(ContextCompat.getColor(getBaseContext(), R.color.sfNoSelect)); // Replace R.color.new_solid_color with the color you want
                }
                spannableStr01.setSpan(lightSpan0, 0, heightUnit_Text.indexOf("/ "), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStr02.setSpan(lightSpan0, 0, weightUnit_Text.indexOf("/"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                spannableStr01.setSpan(darkSpan0, start01, end01, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStr02.setSpan(darkSpan0, start02, end02, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                height_unit.setText(spannableStr01);
                weight_unit.setText(spannableStr02);


            }
        });



        int darkColor = ContextCompat.getColor(this, R.color.notimportant);
        int LightColor = ContextCompat.getColor(this, R.color.white);

        HeightTxt = findViewById(R.id.sf_step2_text1);
        WeightTxt = findViewById(R.id.sf_step2_text2);

        String height_Text = getString(R.string.sf_step2_Height);
        String weight_Text = getString(R.string.sf_step2_Weight);


        SpannableString spannableStr1 = new SpannableString(height_Text);
        SpannableString spannableStr2 = new SpannableString(weight_Text);

        ForegroundColorSpan darkSpan = new ForegroundColorSpan(darkColor);
        ForegroundColorSpan lightSpan = new ForegroundColorSpan(LightColor);

        spannableStr1.setSpan(lightSpan, 0, height_Text.indexOf("How"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStr2.setSpan(lightSpan, 0, weight_Text.indexOf("How"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStr1.setSpan(new AbsoluteSizeSpan(36, true), 0, height_Text.indexOf("How"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStr2.setSpan(new AbsoluteSizeSpan(36, true), 0, weight_Text.indexOf("How"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        int start1 = height_Text.indexOf("How");
        int end1 = start1 + "How tall are you ?".length();
        int start2 = weight_Text.indexOf("How");
        int end2 = start2 + "How much weight have you ?".length();

        spannableStr1.setSpan(darkSpan, start1, end1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStr2.setSpan(darkSpan, start2, end2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        HeightTxt.setText(spannableStr1);
        WeightTxt.setText(spannableStr2);

        pb = findViewById(R.id.sf_progressBar2);
        pb.setMax(6);
        pb.setProgress(CurrentProgress);

    }
}
