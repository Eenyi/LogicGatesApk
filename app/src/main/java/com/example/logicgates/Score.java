package com.example.logicgates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Score extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    TextView s_name, s_score;
    Player currentPlayer;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        intent = getIntent();
        s_name = findViewById(R.id.s_name);
        s_score = findViewById(R.id.s_score);
        s_name.setText(intent.getStringExtra("player"));
        s_score.setText(intent.getStringExtra("score"));
        currentPlayer = new Player(intent.getStringExtra("player"), intent.getStringExtra("score"));
        db = new DBHelper(Score.this);
        db.addPlayer(currentPlayer);
    }

    @Override
    public void onClick(View v) {

    }
}