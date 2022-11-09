package com.example.logicgates;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    /*Declaring Varialbes*/
    Button generate;
    ImageView gateImage;
    int [] arrDrawable = {R.drawable.and, R.drawable.or, R.drawable.nand, R.drawable.nor, R.drawable.not, R.drawable.xor};
    Random rand = new Random();
    int selectedGateIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*get by id*/
        generate = findViewById(R.id.generate);
        gateImage = findViewById(R.id.gateImage);
        /*button listener*/
        generate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                selectedGateIndex = rand.nextInt(6);
                gateImage.setImageResource(arrDrawable[selectedGateIndex]);
            }
        });
    }
}