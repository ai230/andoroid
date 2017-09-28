package com.project.yamamotoai.todolist.alert;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.project.yamamotoai.todolist.MainActivity;
import com.project.yamamotoai.todolist.R;

/**
 * Created by yamamotoai on 2017-09-28.
 */

public class NumberPickerDialogFragment extends DialogFragment {

    NumberPicker picker;

    private NumberPickerInterface numberPickerInterface;
    public interface NumberPickerInterface{
        void onReturnPickedValue(int value);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        numberPickerInterface = (NumberPickerInterface)getTargetFragment();

        int maxValue = getContext().getResources().getInteger(R.integer.number_picker_max_value);
        int minValue = getContext().getResources().getInteger(R.integer.number_picker_min_value);

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pref_number_dialog, null);

        picker = (NumberPicker) view.findViewById(R.id.numberPicker);
        picker.setMaxValue(maxValue);
        picker.setMinValue(minValue);
        picker.setValue(MainActivity.NOTIFICATION_DAYS);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.picker_title)
                .setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int pickedValue = picker.getValue();
                        //Set picker value into SharedPreference
                        SharedPreferences prefs = getActivity().getSharedPreferences(MainActivity.PREF_KEY_DAY, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putInt(MainActivity.PREF_KEY_DAY, pickedValue);
                        editor.commit();

                        MainActivity.NOTIFICATION_DAYS = pickedValue;
                        numberPickerInterface.onReturnPickedValue(pickedValue);

                    }
                })
                .setNegativeButton(R.string.cancel_button, null)
                .setView(view);

        return builder.create();
    }
}
