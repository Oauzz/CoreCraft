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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class s4 extends AppCompatActivity {

    final int max_len = 6;
    EditText[] Codes;
    TextView Error;
    Button Continue;

    ImageView[] tiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s4_otp_code);

        Codes = new EditText[]{
                (EditText) findViewById(R.id.s4_otp_code_txt_1),
                (EditText) findViewById(R.id.s4_otp_code_txt_2),
                (EditText) findViewById(R.id.s4_otp_code_txt_3),
                (EditText) findViewById(R.id.s4_otp_code_txt_4),
                (EditText) findViewById(R.id.s4_otp_code_txt_5),
                (EditText) findViewById(R.id.s4_otp_code_txt_6)
        };
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

        for(int i = 0;i < Codes.length;++i){
            Codes[i].addTextChangedListener(new OtpWatcher(i));
        }

        Continue.setOnClickListener(this::onClick);
    }

    void onClick(View v){
        final String input = Arrays.stream(Codes).map(Code -> Code.getText().toString()).collect(Collectors.joining());
        if(input.length() != 6){
            Error.setText(R.string.invalid_otp_code_error);
        }else{
            Error.setText("");
        }
    }


    private class OtpWatcher implements TextWatcher{
        final int n;
        OtpWatcher(int n){
            this.n = n;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(count > 0 && before == 0){
                tiles[n].setAlpha(1.0f);
                if(n+1 < max_len){
                    Codes[n+1].requestFocus();
                    if(Codes[n+1].getText().toString().length() != 0)
                        Codes[n+1].setSelection(1);
                }
            }else if(count == 0 && before == 1){
                tiles[n].setAlpha(0.5f);
                if(n-1 >= 0){
                    Codes[n-1].requestFocus();
                    if(Codes[n-1].getText().toString().length() != 0)
                        Codes[n-1].setSelection(1);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}