package com.example.yamamotoai.todolist.Main;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yamamotoai on 2017-08-01.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    Calendar calendar;
    DatePickerFragmentInterface datePickerInterface;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {

        calendar.set(year, month, day);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

        datePickerInterface = (DatePickerFragmentInterface) getTargetFragment();
        datePickerInterface.onReturnDate(date);
    }

    public interface DatePickerFragmentInterface {
        void onReturnDate(String date);
    }
}
