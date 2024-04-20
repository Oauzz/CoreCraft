package com.corecraft;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class s1_act extends AppCompatActivity {

    SliderView sliderView ;
    TextView signInTxt ;


    int[][] shots = {
            {R.drawable.s1_img_1,R.string.s1_featureText_1},
            {R.drawable.s1_img_1,R.string.s1_featureText_1},
            {R.drawable.s1_img_1,R.string.s1_featureText_1},
            {R.drawable.s1_img_1,R.string.s1_featureText_1},
            {R.drawable.s1_img_1,R.string.s1_featureText_1},
            {R.drawable.s1_img_1,R.string.s1_featureText_1},

    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s1_starter);

        int darkColor = ContextCompat.getColor(this, R.color.notimportant);
        int LightColor = ContextCompat.getColor(this, R.color.white);



        sliderView = findViewById(R.id.s1_slider);
        signInTxt = findViewById(R.id.s1_signInBtn);


        String signIn_Text = getString(R.string.s1_signInText);
        SpannableString spannableStr = new SpannableString(signIn_Text);

        ForegroundColorSpan darkSpan = new ForegroundColorSpan(darkColor);
        ForegroundColorSpan lightSpan = new ForegroundColorSpan(LightColor);


        spannableStr.setSpan(darkSpan, 0, signIn_Text.indexOf("Sign In"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                // Handle click action for "Sign In" part
                // Example: Start Sign In activity

            }
        };

        int start = signIn_Text.indexOf("Sign In");
        int end = start + "Sign In".length();
        spannableStr.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStr.setSpan(lightSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        signInTxt.setText(spannableStr);

        s1_sliderAdapter sliderAdapter = new s1_sliderAdapter(shots);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


    }
}
