package com.example.logicgates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    /*Declaring Varialbes*/
    Button generate, input1, input2, output;
    ImageView gateImage, resultAvatar;
    TextView name, success, failure;
    Button[] optionButtons = new Button[6];
    int [] arrDrawable = {R.drawable.and, R.drawable.or, R.drawable.nand, R.drawable.nor, R.drawable.not, R.drawable.xor},
    options = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6},
    colors = {R.drawable.color_red, R.drawable.color_blue, R.drawable.color_green, R.drawable.color_yellow, R.drawable.color_purple, R.drawable.color_magenta, R.drawable.color_brown, R.drawable.color_gold, R.drawable.color_orange};
    String [] optionText = {"AND", "OR", "NAND", "NOR", "NOT", "XOR"};
    String currentGateImage = optionText[0];
    Random rand = new Random();
    int selectedGateIndex, optionRandomIndex, successScore = 0, failureScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*get by id*/
        generate = findViewById(R.id.generate);
        resultAvatar = findViewById(R.id.resultAvatar);
        gateImage = findViewById(R.id.gateImage);
        name = findViewById(R.id.name);
        success = findViewById(R.id.success);
        failure = findViewById(R.id.failure);
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
                resultAvatar.setImageResource(R.drawable.guess);
                name.setText(" ");
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
                currentGateImage = optionText[selectedGateIndex];
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

        /*MCQ Options Handle*/
        optionSelection(0);
        optionSelection(1);
        optionSelection(2);
        optionSelection(3);
        optionSelection(4);
        optionSelection(5);
    }
    public void optionSelection(int index) {
        optionButtons[index] = findViewById(options[index]);
        optionButtons[index].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempText = (String) optionButtons[index].getText();
                if (tempText.equals(currentGateImage)) {
                    resultAvatar.setImageResource(R.drawable.r);
                    name.setText("Yes, I'm "+ currentGateImage +" Gate");
                    success.setText("Success: "+ ++successScore);
                }
                else {
                    resultAvatar.setImageResource(R.drawable.w);
                    name.setText("No, I'm "+ currentGateImage + " Gate");
                    failure.setText("Failure: "+ ++failureScore);
                }
            }
        });
    }
}