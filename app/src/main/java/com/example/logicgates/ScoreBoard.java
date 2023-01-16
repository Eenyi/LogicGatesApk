package com.example.logicgates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ScoreBoard extends AppCompatActivity implements View.OnClickListener {
    ListView scorelist;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        scorelist = findViewById(R.id.scorelist);
        db = new DBHelper(ScoreBoard.this);
        List<Player> students = db.viewAllScores();
        ArrayAdapter arrayAdapter = new ArrayAdapter<Player>(ScoreBoard.this, android.R.layout.simple_list_item_1,students);
        scorelist.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}