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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    /*Declaring Varialbes*/
    Button generate, input1, input2, output;
    ImageView gateImage;
    int [] arrDrawable = {R.drawable.and, R.drawable.or, R.drawable.nand, R.drawable.nor, R.drawable.not, R.drawable.xor},
    options = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6},
    colors = {R.drawable.color_red, R.drawable.color_blue, R.drawable.color_green, R.drawable.color_yellow, R.drawable.color_purple, R.drawable.color_magenta, R.drawable.color_brown, R.drawable.color_gold, R.drawable.color_orange};
    String [] optionText = {"AND", "OR", "NAND", "NOR", "NOT", "XOR"};
    Random rand = new Random();
    int selectedGateIndex, optionRandomIndex;

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
                generate.setBackgroundResource(colors[rand.nextInt(9)]);
                /*Code to Set Options on Buttons*/
                List<String> randOption = new ArrayList<String>();
                while(randOption.size() != 6) {
                    optionRandomIndex = rand.nextInt(6);
                    if (!randOption.contains(optionText[optionRandomIndex])) {
                        randOption.add(optionText[optionRandomIndex]);
                    }
                }
                Button tempBtn;
                for (int i = 0; i < options.length; i++) {
                    tempBtn = findViewById(options[i]);
                    tempBtn.setText(randOption.get(i));
                    tempBtn.setBackgroundResource(colors[rand.nextInt(9)]);
                }
                /*Generate Button Functionality to change Gates Images*/
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