package com.example.yamamotoai.midproject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by yamamotoai on 2017-08-01.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        Date date = new Date();

//        long msDiff = Calendar.getInstance().getTimeInMillis() - testCalendar.getTimeInMillis();
//        long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String dateString = year + "-" + month + "-" + day;
        DatePickerFragmentInterface d = (DatePickerFragmentInterface) getActivity();
        d.onReturnDate(dateString);
    }

    public interface DatePickerFragmentInterface {
        public void onReturnDate(String date);
        public void onReturnDays(int days);
    }
}
