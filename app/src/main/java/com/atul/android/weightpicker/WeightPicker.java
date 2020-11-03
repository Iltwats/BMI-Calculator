package com.atul.android.weightpicker;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import androidx.appcompat.app.AlertDialog;

import com.atul.android.weightpicker.databinding.ActivityWeightPickerBinding;

public class WeightPicker {

    public void show(Context context,final OnWeightPickedListener listener) {
        ActivityWeightPickerBinding b = ActivityWeightPickerBinding.inflate(LayoutInflater.from(context));
        new AlertDialog.Builder(context)
                .setTitle("Pick Weight")
                .setView(b.getRoot())
                .setPositiveButton("SELECT", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int kg = b.kgPicker.getValue();
                        int g = b.gmPicker.getValue();
                        if (kg == 0 && g == 0) {
                            return ;
                        }
                        listener.onWeightPicked(kg, g);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onWeightPickerCancelled();
                    }
                })
                .show();

        setupNumberPickers(b.kgPicker, b.gmPicker);
    }

    private static void setupNumberPickers(NumberPicker kgPicker, NumberPicker gmPicker) {
        kgPicker.setMaxValue(200);
        kgPicker.setMinValue(0);
        gmPicker.setMaxValue(9);
        gmPicker.setMinValue(0);
        kgPicker.setFormatter(value -> String.valueOf(value));
        gmPicker.setFormatter(value -> String.valueOf(value));

        View Kg = kgPicker.getChildAt(0);
        if (Kg != null) {
            Kg.setVisibility(View.INVISIBLE);
        }
        View g = gmPicker.getChildAt(0);
        if (g != null) {
            g.setVisibility(View.INVISIBLE);
        }
    }

    interface OnWeightPickedListener{
        void onWeightPicked(int kg, int g);
        void onWeightPickerCancelled();
    }


}
