package com.example.logicgates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity implements View.OnClickListener {
    Button github, start, lgscoreboardbtn;
    EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        username = findViewById(R.id.lgenternametext);
        github = findViewById(R.id.lggithub);
        github.setOnClickListener(this);
        start = findViewById(R.id.lgenterbtn);
        start.setOnClickListener(this);
        lgscoreboardbtn = findViewById(R.id.lgscoreboardbtn);
        lgscoreboardbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Uri uri;
        switch (v.getId()) {
            case R.id.lggithub:
                uri = Uri.parse("https://github.com/Eenyi/LogicGatesApk.git");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.lgenterbtn:
                intent = new Intent(Home.this, Playground.class);
                intent.putExtra("username", username.getText().toString());
                startActivity(intent);
                break;
            case R.id.lgscoreboardbtn:
                intent = new Intent(Home.this, ScoreBoard.class);
                startActivity(intent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}