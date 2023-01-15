package com.example.logicgates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Playground extends AppCompatActivity implements View.OnClickListener {
    TextView playHeading;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);
        playHeading = findViewById(R.id.p_name);
        intent = getIntent();
        playHeading.setText(intent.getStringExtra("username"));
    }

    @Override
    public void onClick(View v) {

    }
}