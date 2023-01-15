package com.example.logicgates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Playground extends AppCompatActivity implements View.OnClickListener {
    TextView playHeading;
    Intent intent;
    ImageView p_img;
    Random rand = new Random();
    int [] arrDrawableGateImages = {R.drawable.and, R.drawable.or, R.drawable.nand, R.drawable.nor, R.drawable.not, R.drawable.xor};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);
        playHeading = findViewById(R.id.p_name);
        intent = getIntent();
        playHeading.setText(intent.getStringExtra("username"));
        p_img = findViewById(R.id.p_img);
        setGateImageOnCreate();
    }

    @Override
    public void onClick(View v) {

    }

    protected void setGateImageOnCreate() {
        int selectedGateIndex = rand.nextInt(6);
        p_img.setImageResource(arrDrawableGateImages[selectedGateIndex]);
    }
}