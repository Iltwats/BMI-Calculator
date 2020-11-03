package com.atul.android.weightpicker;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.NumberPicker;

import androidx.appcompat.app.AlertDialog;

import com.atul.android.weightpicker.databinding.ActivityHeightPickerBinding;

public class HeightPicker {

    public void show(Context context, final OnHeightPickedListener listener) {
        ActivityHeightPickerBinding b = ActivityHeightPickerBinding.inflate(LayoutInflater.from(context));
        new AlertDialog.Builder(context)
                .setTitle("Pick Height")
                .setView(b.getRoot())
                .setPositiveButton("SELECT", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int cms = b.cmsPicker.getValue();
                        listener.onHeightPicked(cms);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onHeightPickerCancelled();
                    }
                })
                .show();

        setupNumberPickers(b.cmsPicker);
    }

    private void setupNumberPickers(NumberPicker cmsPicker) {
        cmsPicker.setMaxValue(250);
        cmsPicker.setMinValue(1);
        cmsPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.valueOf(value);
            }
        });
    }

    interface OnHeightPickedListener{
        void onHeightPicked(int cms);
        void onHeightPickerCancelled();
    }
}
