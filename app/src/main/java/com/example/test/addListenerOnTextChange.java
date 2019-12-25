package com.example.test;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Character.toLowerCase;

public class addListenerOnTextChange implements TextWatcher {
    private Context mContext;
    private EditText mTextViewInput;
    private TextView mTextViewOutput;

    public addListenerOnTextChange(Context context, EditText textedit, TextView textview) {
        super();
        this.mContext = context;
        this.mTextViewInput = textedit;
        this.mTextViewOutput = textview;
    }
    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mTextViewOutput.setText(Convert(mTextViewInput.getText().toString()));
    }

    protected String Convert(String str){
        String res = "";
        char[] chars = str.toCharArray();

        for(int i = 0; i < chars.length; i++){
            int c = Character.toLowerCase(chars[i]);
            if( c >= (int) 'a' && c <= (int) 'z'){
                if(c < (int) 'n'){
                    c = c + 13;
                }
                else{
                    c = c - 13;
                }
            }
            res += (char) c;
        }
        return res;
    }
}
