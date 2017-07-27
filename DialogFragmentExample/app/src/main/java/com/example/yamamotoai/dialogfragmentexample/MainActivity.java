package com.example.yamamotoai.dialogfragmentexample;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements DatePickerFragment.DatePickerFragmentInterface, TimePickerFragment.TimePickerFragmentInterface{

    Button btn1;
    Button btn2;

    TextView textViewDate;
    TextView textViewTime;

    FragmentManager fragmentManager = getFragmentManager();
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        textViewDate = (TextView) findViewById(R.id.textviewDate);
        textViewTime = (TextView) findViewById(R.id.textviewTime);
        spinner = (Spinner) findViewById(R.id.spiner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planetnames, android.R.layout.simple_spinner_item);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment1 dialogFragment = new DialogFragment1();
                dialogFragment.show(fragmentManager, "dialog fragment");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialogFragment1 alertDialog = new AlertDialogFragment1();
                alertDialog.show(fragmentManager, "Alert fragment");
            }
        });

        spinner.setAdapter(adapter);
//        AdapterView.OnItemSelectedListener
    }

    public void showDatePickerDialog(View v) {
        Log.d("---","clicked date");
        DialogFragment newDateFragment = new DatePickerFragment();
        newDateFragment.show(fragmentManager, "datePicker");
    }

    public void showTimePickerDialog(View v) {
        Log.d("---","clicked time");
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(fragmentManager, "timePicker");
    }

    @Override
    public void onReturnTime(String string) {
        textViewTime.setText(string);
    }

    @Override
    public void onReturnDate(String string) {
        textViewDate.setText(string);
    }
}
