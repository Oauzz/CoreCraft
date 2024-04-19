package com.corecraft;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class s5 extends AppCompatActivity {

    EditText Pwd;
    ImageView Eye;
    boolean isPwdVisible = false;
    TextView Error;
    Button Continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s5_new_pwd);

        Pwd = (EditText)findViewById(R.id.s5_pwd_txt);
        Eye = (ImageView)findViewById(R.id.s5_eye);
        Error = (TextView)findViewById(R.id.s5_error_label);
        Continue = (Button)findViewById(R.id.s5_continue_btn);

        Eye.setImageResource(
                isPwdVisible ? R.drawable.pwd_eye_enabled : R.drawable.pwd_eye_disabled
        );

        Eye.setOnClickListener(this::togglePwdVisibility);
        Continue.setOnClickListener(this::onClick);
    }

    void togglePwdVisibility(View v){
        isPwdVisible = !isPwdVisible;
        if(isPwdVisible){
            Eye.setImageResource(R.drawable.pwd_eye_enabled);
            Pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else{
            Eye.setImageResource(R.drawable.pwd_eye_disabled);
            Pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    void onClick(View v){
        final String pwd = Pwd.getText().toString();
        StringBuilder msg = new StringBuilder();
        if(pwd.trim().length() < 8)
            msg.append(getString(R.string.invalid_pwd_8chars_error)).append('\n');
        if(!pwd.matches(".*\\d.*") || !pwd.matches(".*[A-Z].*"))
            msg.append(getString(R.string.invalid_pwd_alphanumeric_error)).append('\n');
        if(msg.toString().equals("")){
            Error.setText("");
        }else{
            Error.setText(msg);
        }
    }


}