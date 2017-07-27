package com.example.yamamotoai.dialogfragmentexample;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by yamamotoai on 2017-07-27.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        Date date = c.getTime();

        String stringDate = String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(day);

        //create instance of interface
        DatePickerFragmentInterface d = (DatePickerFragmentInterface) getActivity();
        d.onReturnDate(stringDate);
    }

    public interface DatePickerFragmentInterface {
        public void onReturnDate(String string);
    }

}
