package com.example.logicgates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    /*Declaring Varialbes*/
    Button generate, input1, input2, output;
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
        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        /*button listener*/
        generate.setOnClickListener(new View.OnClickListener() {
        /*Get Layouts*/
        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) input2.getLayoutParams();
        int notBottomMargin = lp.bottomMargin+100;
            @Override
            public void onClick(View view) {
                selectedGateIndex = rand.nextInt(6);
                gateImage.setImageResource(arrDrawable[selectedGateIndex]);
                /*Special NOT Case handle*/
                if (selectedGateIndex == 4){
                    input1.setVisibility(View.INVISIBLE);
                    lp.bottomMargin=notBottomMargin;
                    input2.setLayoutParams(lp);
                }
                else {
                    input1.setVisibility(View.VISIBLE);
                    lp.bottomMargin=notBottomMargin-100;
                    input2.setLayoutParams(lp);
                }
            }
        });
    }
}