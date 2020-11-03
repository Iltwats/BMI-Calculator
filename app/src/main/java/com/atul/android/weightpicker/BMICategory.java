package com.atul.android.weightpicker;

import android.graphics.Color;

import androidx.core.util.Pair;

public class BMICategory {
    public Pair<String, Integer> getCategory(double result) {
        String category;
        int conditionColor;
        if (result < 15) {
            category =  "Very Severely Underweight";
            conditionColor = Color.parseColor("#2196f3"); //blue
        } else if (result >=15 && result <= 16) {
            category = "Severely Underweight";
            conditionColor = Color.parseColor("#0D09DD"); //Light Blue
        } else if (result >16 && result <= 18.5) {
            category = "Underweight";
            conditionColor = Color.parseColor("#09CFDD"); //sky blue
        } else if (result >18.5 && result <= 25) {
            category = "Normal (healthy weight)";
            conditionColor = Color.parseColor("#689f38"); //green
        } else if (result >25 && result <= 30) {
            category = "Overweight";
            conditionColor = Color.parseColor("#ff9800"); //orange
        } else if (result >30 && result <= 35) {
            category = "Obese";
            conditionColor = Color.parseColor("#ff5722"); //deep orange
        } else {
            category ="Very Severely Obese";
            conditionColor = Color.parseColor("#DD092C"); //red
        }
        return new Pair<String,Integer>(category, conditionColor);
    }

}
