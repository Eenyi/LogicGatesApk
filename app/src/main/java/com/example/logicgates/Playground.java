package com.example.logicgates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Playground extends AppCompatActivity implements View.OnClickListener {
    TextView playHeading, p_result, p_count, p_s, p_f;
    Intent intent;
    ImageView p_img, p_bulb;
    Random rand = new Random();
    int [] arrDrawableGateImages = new int[]{R.drawable.and, R.drawable.or, R.drawable.nand, R.drawable.nor, R.drawable.not, R.drawable.xor};
    String [] gatesNames = new String[] {"AND", "OR", "NAND", "NOR", "NOT", "XOR"};
    Button p_next, p_and, p_or, p_nand, p_nor, p_xor, p_not, p_ter1, p_ter2;
    int turnCount, currentImgID, success, failure;
    String currentImgName, player;
    boolean ter1, ter2;
    TruthTable [] andTruthTable = {new TruthTable("⭕ OFF", "⭕ OFF", R.drawable.off, R.drawable.and),
            new TruthTable("⚡ ON", "⚡ ON", R.drawable.on, R.drawable.and),
            new TruthTable("⚡ ON", "⭕ OFF", R.drawable.off, R.drawable.and),
            new TruthTable("⭕ OFF", "⚡ ON", R.drawable.off, R.drawable.and)};
    TruthTable [] nandTruthTable = {new TruthTable("⭕ OFF", "⭕ OFF", R.drawable.on, R.drawable.nand),
            new TruthTable("⚡ ON", "⚡ ON", R.drawable.off, R.drawable.nand),
            new TruthTable("⚡ ON", "⭕ OFF", R.drawable.on, R.drawable.nand),
            new TruthTable("⭕ OFF", "⚡ ON", R.drawable.on, R.drawable.nand)};
    TruthTable [] orTruthTable = {new TruthTable("⭕ OFF", "⭕ OFF", R.drawable.off, R.drawable.or),
            new TruthTable("⚡ ON", "⚡ ON", R.drawable.on, R.drawable.or),
            new TruthTable("⚡ ON", "⭕ OFF", R.drawable.on, R.drawable.or),
            new TruthTable("⭕ OFF", "⚡ ON", R.drawable.on, R.drawable.or)};
    TruthTable [] norTruthTable = {new TruthTable("⭕ OFF", "⭕ OFF", R.drawable.on, R.drawable.nor),
            new TruthTable("⚡ ON", "⚡ ON", R.drawable.off, R.drawable.nor),
            new TruthTable("⚡ ON", "⭕ OFF", R.drawable.off, R.drawable.nor),
            new TruthTable("⭕ OFF", "⚡ ON", R.drawable.off, R.drawable.nor)};
    TruthTable [] xorTruthTable = {new TruthTable("⭕ OFF", "⭕ OFF", R.drawable.off, R.drawable.xor),
            new TruthTable("⚡ ON", "⚡ ON", R.drawable.off, R.drawable.xor),
            new TruthTable("⚡ ON", "⭕ OFF", R.drawable.on, R.drawable.xor),
            new TruthTable("⭕ OFF", "⚡ ON", R.drawable.on, R.drawable.xor)};
    TruthTable [] notTruthTable = {new TruthTable("-", "⚡ ON", R.drawable.off, R.drawable.not),
            new TruthTable("-", "⭕ OFF", R.drawable.on, R.drawable.not)};
    List<TruthTable[]> truthTablesList = new ArrayList<TruthTable[]>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);
        playHeading = findViewById(R.id.p_name);
        p_result = findViewById(R.id.p_result);
        p_count = findViewById(R.id.p_count);
        p_s = findViewById(R.id.p_s);
        p_f = findViewById(R.id.p_f);
        p_bulb = findViewById(R.id.p_bulb);
        intent = getIntent();
        playHeading.setText(intent.getStringExtra("username"));
        player = intent.getStringExtra("username");
        p_img = findViewById(R.id.p_img);
        setGateImageRandomly();
        setButtonListeners();
        setP_bulb();
        turnCount = 9;
        failure = 0;
        success = 0;
        ter1 = false;
        ter2 = false;
        truthTablesList.add(andTruthTable);
        truthTablesList.add(nandTruthTable);
        truthTablesList.add(orTruthTable);
        truthTablesList.add(norTruthTable);
        truthTablesList.add(xorTruthTable);
        truthTablesList.add(notTruthTable);
    }

    @Override
    public void onClick(View v) {
        String yes = "Yes, this is "+currentImgName+" gate!",
                no = "No, this is "+currentImgName+" gate!";
        Intent intent1;
        switch (v.getId()) {
            case R.id.p_next:
                if (turnCount != 0) {
                    setGateImageRandomly();
                    setOptionDisabled(true);
                    setP_bulb();
                    p_result.setText("");
                    p_count.setText("Remaining Turns : " + --turnCount);
                }
                else {
                    //intent call of score & share button
                    intent1 = new Intent(Playground.this, Score.class);
                    intent1.putExtra("player", player);
                    intent1.putExtra("score", Integer.toString(success));
                    startActivity(intent1);
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
            case R.id.p_ter1:
                setP_bulb();
                if (!ter1){
                    p_ter1.setText("⚡ ON");
                    ter1 = true;
                }
                else {
                    p_ter1.setText("⭕ OFF");
                    ter1 = false;
                }
                break;
            case R.id.p_ter2:
                setP_bulb();
                if (!ter2){
                    p_ter2.setText("⚡ ON");
                    ter2 = true;
                }
                else {
                    p_ter2.setText("⭕ OFF");
                    ter2 = false;
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

    protected void setP_bulb() {
        for (TruthTable[] truthTable: truthTablesList) {
            if (currentImgID == truthTable[0].drawableID && currentImgID != R.drawable.not) {
                for (TruthTable possibility: truthTable) {
                    if (p_ter1.getText().equals(possibility.in1) && p_ter2.getText().equals(possibility.in2)) {
                        p_bulb.setImageResource(possibility.bulbID);
                    }
                }
            }
            if (currentImgID == truthTable[0].drawableID && currentImgID == R.drawable.not) {
                for (TruthTable possibility: truthTable) {
                    if (p_ter1.getText().equals(possibility.in2)) {
                        p_bulb.setImageResource(possibility.bulbID);
                    }
                }
            }
        }

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
        p_ter1 =findViewById(R.id.p_ter1);
        p_ter1.setOnClickListener(this);
        p_ter2 =findViewById(R.id.p_ter2);
        p_ter2.setOnClickListener(this);
    }

    protected boolean checkGuess(int optionID) {
        setOptionDisabled(false);
        int gateImgID = getIndex(arrDrawableGateImages, currentImgID);
        if (optionID == gateImgID) {
            p_s.setText("Success : "+ ++success);
            return true;
        }
        else {
            p_f.setText("Failure : "+ ++failure);
            return false;
        }
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