package com.corecraft;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class s4 extends AppCompatActivity {

    final int max_len = 6;
    EditText Code;
    TextView Error;
    Button Continue;

    ImageView[] tiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s4_otp_code);

        final DisplayMetrics dims = Resources.getSystem().getDisplayMetrics();

        float textSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,  20.0f, dims);
        float letterSpacingPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT,  1.8f, dims);
        float androidLetterSpacing = letterSpacingPx / textSizePx;


        Code = (EditText)findViewById(R.id.s4_otp_code_txt);
        System.out.println("Coeff " + androidLetterSpacing);
        Code.setLetterSpacing(androidLetterSpacing);
        Error = (TextView)findViewById(R.id.s4_error_label);
        Continue = (Button)findViewById(R.id.s4_continue_btn);
        tiles = new ImageView[]{
                (ImageView) findViewById(R.id.s4_code_floor_1),
                (ImageView) findViewById(R.id.s4_code_floor_2),
                (ImageView) findViewById(R.id.s4_code_floor_3),
                (ImageView) findViewById(R.id.s4_code_floor_4),
                (ImageView) findViewById(R.id.s4_code_floor_5),
                (ImageView) findViewById(R.id.s4_code_floor_6)
        };

        Code.addTextChangedListener(new OtpWatcher());
        Continue.setOnClickListener(this::onClick);
    }

    void onClick(View v){
        final String input = Code.getText().toString();
        if(input.length() != 6){
            Error.setText(R.string.invalid_otp_code_error);
        }else{
            Error.setText("");
        }
    }


    private class OtpWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if(count < before){
                for(int i = before + start - 1;i >= start;--i){
                    tiles[i].setAlpha(0.5f);
                }
            }else if(count > before){
                for(int i = start;i < start + count;++i){
                    tiles[i].setAlpha(1.0f);
                }
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}