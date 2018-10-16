package com.example.test.runner;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends Activity {

    private String StrWhereText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button next = (Button) findViewById(R.id.send);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this, Main.class);
                startActivity(intent);
            }
        });
        //announce editText
        EditText name = (EditText) findViewById(R.id.nameEdt);
        EditText birth = (EditText) findViewById(R.id.birthedt);
        EditText email = (EditText) findViewById(R.id.emailedt);

        name.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        birth.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});


    }
}
