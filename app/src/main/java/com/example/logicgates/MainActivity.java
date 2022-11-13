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
    int selectedGateIndex = 0, optionRandomIndex, successScore = 0, failureScore = 0;
    Boolean input1Flag = false, input2Flag = false, outputFlag = false;
    GateTable [] andGateTable = {new GateTable("OFF", "OFF", "OFF", R.drawable.and),
            new GateTable("ON", "ON", "ON", R.drawable.and),
            new GateTable("ON", "OFF", "OFF", R.drawable.and),
            new GateTable("OFF", "ON", "OFF", R.drawable.and)};
    GateTable [] nandGateTable = {new GateTable("OFF", "OFF", "ON", R.drawable.nand),
            new GateTable("ON", "ON", "OFF", R.drawable.nand),
            new GateTable("ON", "OFF", "ON", R.drawable.nand),
            new GateTable("OFF", "ON", "ON", R.drawable.nand)};
    GateTable [] orGateTable = {new GateTable("OFF", "OFF", "OFF", R.drawable.or),
            new GateTable("ON", "ON", "ON", R.drawable.or),
            new GateTable("ON", "OFF", "ON", R.drawable.or),
            new GateTable("OFF", "ON", "ON", R.drawable.or)};
    GateTable [] norGateTable = {new GateTable("OFF", "OFF", "ON", R.drawable.nor),
            new GateTable("ON", "ON", "OFF", R.drawable.nor),
            new GateTable("ON", "OFF", "OFF", R.drawable.nor),
            new GateTable("OFF", "ON", "OFF", R.drawable.nor)};
    GateTable [] xorGateTable = {new GateTable("OFF", "OFF", "OFF", R.drawable.xor),
            new GateTable("ON", "ON", "OFF", R.drawable.xor),
            new GateTable("ON", "OFF", "ON", R.drawable.xor),
            new GateTable("OFF", "ON", "ON", R.drawable.xor)};
    GateTable [] notGateTable = {new GateTable("-", "ON", "OFF", R.drawable.not),
            new GateTable("-", "OFF", "ON", R.drawable.not)};
    List<GateTable[]> gateTablesList = new ArrayList<GateTable[]>();

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
        output = findViewById(R.id.output);
        gateTablesList.add(andGateTable);
        gateTablesList.add(nandGateTable);
        gateTablesList.add(orGateTable);
        gateTablesList.add(norGateTable);
        gateTablesList.add(xorGateTable);
        gateTablesList.add(notGateTable);
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
                input1.setBackgroundResource(colors[1]);
                input1.setText("OFF");
                input1Flag = false;
                input2.setBackgroundResource(colors[1]);
                input2.setText("OFF");
                input2Flag = false;
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
                if (arrDrawable[selectedGateIndex] == R.drawable.nand ||
                        arrDrawable[selectedGateIndex] == R.drawable.nor ||
                        arrDrawable[selectedGateIndex] == R.drawable.not) {
                    output.setText("ON");
                    output.setBackgroundResource(R.drawable.color_yellow);
                }
                else {
                    output.setText("OFF");
                    output.setBackgroundResource(R.drawable.color_white);
                }
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

        /*Illustration Handle*/
        input1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input1Flag) {
                    input1.setBackgroundResource(colors[8]);
                    input1.setText("ON");
                    input1Flag = true;
                    illustrationHandle();
                }
                else {
                    input1.setBackgroundResource(colors[1]);
                    input1.setText("OFF");
                    input1Flag = false;
                    illustrationHandle();
                }
            }
        });
        input2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input2Flag) {
                    input2.setBackgroundResource(colors[8]);
                    input2.setText("ON");
                    input2Flag = true;
                    illustrationHandle();
                }
                else {
                    input2.setBackgroundResource(colors[1]);
                    input2.setText("OFF");
                    input2Flag = false;
                    illustrationHandle();
                }
            }
        });

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
    public void outputColor(String out) {
        if ("ON".equals(out)) {
            output.setBackgroundResource(R.drawable.color_yellow);
        }
        else {
            output.setBackgroundResource(R.drawable.color_white);
        }
    }
    public void illustrationHandle() {
        for (GateTable[] possibility: gateTablesList) {
            if (arrDrawable[selectedGateIndex] == possibility[0].drawableID) {
                if (arrDrawable[selectedGateIndex] != R.drawable.not) {
                    for (GateTable item:
                            possibility) {
                        if(input1.getText().equals(item.in1) && input2.getText().equals(item.in2)) {
                            output.setText(item.out);
                            outputColor(item.out);
                        }
                    }
                }
                else {
                    for (GateTable item:
                            possibility) {
                        if(input2.getText().equals(item.in2)) {
                            output.setText(item.out);
                            outputColor(item.out);
                        }
                    }
                }
            }
        }
    }
}