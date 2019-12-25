package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mButtonConvert;
    private TextView mTextViewOutput;
    private EditText mEditTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewOutput = findViewById(R.id.text_out);
        mEditTextInput = findViewById(R.id.text_input);
        mEditTextInput.addTextChangedListener(new addListenerOnTextChange(this, mEditTextInput, mTextViewOutput));
    }
}

