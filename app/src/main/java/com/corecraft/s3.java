package com.corecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class s3 extends AppCompatActivity {

    EditText Email;
    TextView Error;
    Button Continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s3_forget_pwd);

        Email = (EditText)findViewById(R.id.s3_email_txt);
        Error = (TextView)findViewById(R.id.s3_error_label);
        Continue = (Button)findViewById(R.id.s3_continue_btn);

        Continue.setOnClickListener(this::onClick);
    }

    void onClick(View v){
        final String email = Email.getText().toString();
        if(email.trim().equals("")){
            Error.setText(R.string.invalid_email_error);
        }else{
            Error.setText("");
        }
    }


}