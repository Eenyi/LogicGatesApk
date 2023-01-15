package com.example.logicgates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Playground extends AppCompatActivity implements View.OnClickListener {
    TextView playHeading, p_result;
    Intent intent;
    ImageView p_img;
    Random rand = new Random();
    int [] arrDrawableGateImages = new int[]{R.drawable.and, R.drawable.or, R.drawable.nand, R.drawable.nor, R.drawable.not, R.drawable.xor};
    String [] gatesNames = new String[] {"AND", "OR", "NAND", "NOR", "NOT", "XOR"};
    Button p_next, p_and, p_or, p_nand, p_nor, p_xor, p_not;
    int turnCount, currentImgID;
    String currentImgName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);
        playHeading = findViewById(R.id.p_name);
        p_result = findViewById(R.id.p_result);
        intent = getIntent();
        playHeading.setText(intent.getStringExtra("username"));
        p_img = findViewById(R.id.p_img);
        setGateImageRandomly();
        setButtonListeners();
        turnCount = 10;
    }

    @Override
    public void onClick(View v) {
        String yes = "Yes, this is "+currentImgName+" gate!",
                no = "No, this is "+currentImgName+" gate!";
        switch (v.getId()) {
            case R.id.p_next:
                if (turnCount != 0) {
                    setGateImageRandomly();
                    setOptionDisabled(true);
                    p_result.setText("");
                    turnCount--;
                }
                else {
                    //intent call of score & share button
                }
                break;
            case R.id.p_and:
                if (checkGuess(0)) {
                    p_result.setText(yes);
                }
                else {
                    p_result.setText(no);
                }
                break;
            case R.id.p_or:
                if (checkGuess(1)) {
                p_result.setText(yes);
                }
                else {
                    p_result.setText(no);
                }
                break;
            case R.id.p_nand:
                if (checkGuess(2)) {
                    p_result.setText(yes);
                }
                else {
                    p_result.setText(no);
                }
                break;
            case R.id.p_nor:
                if (checkGuess(3)) {
                    p_result.setText(yes);
                }
                else {
                    p_result.setText(no);
                }
                break;
            case R.id.p_xor:
                if (checkGuess(5)) {
                    p_result.setText(yes);
                }
                else {
                    p_result.setText(no);
                }
                break;
            case R.id.p_not:
                if (checkGuess(4)) {
                    p_result.setText(yes);
                }
                else {
                    p_result.setText(no);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    protected void setGateImageRandomly() {
        int selectedGateIndex = rand.nextInt(6);
        p_img.setImageResource(arrDrawableGateImages[selectedGateIndex]);
        currentImgID = arrDrawableGateImages[selectedGateIndex];
        currentImgName = gatesNames[getIndex(arrDrawableGateImages, currentImgID)];
    }

    protected void setButtonListeners() {
        p_next =findViewById(R.id.p_next);
        p_next.setOnClickListener(this);
        p_and =findViewById(R.id.p_and);
        p_and.setOnClickListener(this);
        p_or =findViewById(R.id.p_or);
        p_or.setOnClickListener(this);
        p_nand =findViewById(R.id.p_nand);
        p_nand.setOnClickListener(this);
        p_nor =findViewById(R.id.p_nor);
        p_nor.setOnClickListener(this);
        p_xor =findViewById(R.id.p_xor);
        p_xor.setOnClickListener(this);
        p_not =findViewById(R.id.p_not);
        p_not.setOnClickListener(this);
    }
    protected boolean checkGuess(int optionID) {
        setOptionDisabled(false);
        int gateImgID = getIndex(arrDrawableGateImages, currentImgID);
        if (optionID == gateImgID) { return true;}
        else {return false;}
    }

    protected int getIndex(int arr[], int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) { return i; }
        }
        return -1;
    }

    protected void setOptionDisabled(boolean flag) {
        p_and.setEnabled(flag);
        p_or.setEnabled(flag);
        p_nand.setEnabled(flag);
        p_nor.setEnabled(flag);
        p_xor.setEnabled(flag);
        p_not.setEnabled(flag);
    }
}