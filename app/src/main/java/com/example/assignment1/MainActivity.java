/*
CSIS 4020 Assignment 1
Graham Tuso
January 13, 2022
 */

package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLOutput;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    EditText editWeight;
    TextView displayTextView;
    CheckBox trimCheckBox, fleaCheckBox, massageCheckBox;
    NumberFormat fmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editWeight = findViewById(R.id.editWeight);
        displayTextView = findViewById(R.id.textView);
        trimCheckBox = findViewById(R.id.trim_checkBox);
        fleaCheckBox = findViewById(R.id.flea_checkBox);
        massageCheckBox = findViewById(R.id.massage_checkBox);
        displayTextView.setText("$0.00");
        fmt = NumberFormat.getCurrencyInstance();
    }

    public void handleReset(View v) {
        reset();
    }

    public void handleCalculate(View v) {
        updateUI();
    }

    public void handleOrder(View v) {
        Toast.makeText(this, "Order placed. Thanks for your business!", Toast.LENGTH_SHORT).show();
        reset();
    }

    public void updateUI() {
        String cost = "";
        try {
            Integer weight = Integer.parseInt(editWeight.getText().toString());
            double base_cost = 0.00;
            if (weight < 30) {
                base_cost = 35.00;
            } else if (weight < 50) {
                base_cost = 45.00;
            } else {
                base_cost = 55.00;
            }
            if (trimCheckBox.isChecked()) {
                base_cost += 5.00;
            }
            if (fleaCheckBox.isChecked()) {
                base_cost += 10.00;
            }
            if (massageCheckBox.isChecked()) {
                base_cost += 20.00;
            }
            cost = fmt.format(base_cost);
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Invalid Action", Toast.LENGTH_SHORT).show();
        } finally {
            displayTextView.setText(cost);
        }
    }

    public void reset() {
        editWeight.setText("");
        displayTextView.setText("$0.00");
        trimCheckBox.setChecked(false);
        fleaCheckBox.setChecked(false);
        massageCheckBox.setChecked(false);
    }

}