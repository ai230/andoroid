package com.example.yamamotoai.dialogfragmentexample;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by yamamotoai on 2017-07-27.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user

        String stringDate = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);

        //create instance of interface
        TimePickerFragmentInterface d = (TimePickerFragmentInterface) getActivity();
        d.onReturnTime(stringDate);

    }

    public interface TimePickerFragmentInterface {
        public void onReturnTime(String string);
    }
}

