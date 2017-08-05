package com.example.yamamotoai.midproject;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yamamotoai on 2017-08-01.
 */

public class AdditionActivity extends AppCompatActivity implements DatePickerFragment.DatePickerFragmentInterface {

//    private List<TODO> todoList = new ArrayList<>();



    TextView dateTextView;
    EditText titleEditText;
    EditText groupEditText;
    EditText contentEditText;
    TextView daysDiffTextView;

    String dateString;
    FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtion);

        dateTextView = (TextView) findViewById(R.id.textview_date);
        titleEditText = (EditText) findViewById(R.id.edittext_title);
        groupEditText = (EditText) findViewById(R.id.edittext_group);
        contentEditText = (EditText) findViewById(R.id.edittext_content);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        c.set(year,month,day);

        String dateString = year + "-" + month + "-" + day;
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        dateTextView.setText(date);
        TODO todo1 = (TODO) getIntent().getSerializableExtra("TODOObjEdit");
        if(todo1 != null){
            Log.d("---todo1", todo1.getTitle());
            dateTextView.setText(todo1.getDate());
            titleEditText.setText(todo1.getTitle());
            groupEditText.setText(todo1.getGroup());
            contentEditText.setText(todo1.getContent());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        TODO todo1 = (TODO) getIntent().getSerializableExtra("TODOObjEdit");
        if (todo1 != null) {
            Log.d("---", todo1.getTitle());
        }
    }

    public void onClickCancel(View view){
        Log.d("---", "click1");
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);

    }

    public void onClickSave(View view){
        Log.d("---", "click2");

        TODO todo = new TODO(String.valueOf(dateTextView.getText()), String.valueOf(titleEditText.getText()), String.valueOf(groupEditText.getText()), String.valueOf(contentEditText.getText()));

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("TODOObj", todo);
        startActivity(intent);
    }


    public void onClickDelete(View view){
        Log.d("---", "click3");
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }

    public void showDatePickerDialog(View v) {
        Log.d("---","clicked date");
        DialogFragment newDateFragment = new DatePickerFragment();
        newDateFragment.show(fragmentManager, "datePicker");
    }

    @Override
    public void onReturnDate(String date) {
        dateTextView.setText(date);
    }

}
