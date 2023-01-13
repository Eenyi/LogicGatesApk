package com.example.logicgates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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
        Intent intent;
        Uri uri;
        switch (v.getId()) {
            case R.id.lggithub:
                uri = Uri.parse("https://github.com/Eenyi/LogicGatesApk.git");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
    }
}