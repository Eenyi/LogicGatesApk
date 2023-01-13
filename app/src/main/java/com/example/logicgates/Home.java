package com.example.logicgates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener {
    Button github;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        github = findViewById(R.id.lggithub);
        github.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}