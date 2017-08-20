package com.example.yamamotoai.todolist;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yamamotoai on 2017-08-17.
 */

public class AdditionActivity  extends AppCompatActivity implements DatePickerFragment.DatePickerFragmentInterface {

    TextView dateTextView;
    EditText titleEditText;
    EditText contentEditText;
    EditText editTextNewGroup;

    Spinner groupSpinner;
    FragmentManager fragmentManager = getFragmentManager();

    String title;
    String group;
    String content;
    String date;

    TODO todoEdit;
    Boolean isEditting = false;
    int selectedGroupPosition;
    List<String> group_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtion);

        for(TODO item : MainActivity.todoList){
            if(!group_list.contains(item.getGroup().toUpperCase()))
                group_list.add(item.getGroup().toUpperCase());
        }
        group_list.add("NEW GROUP");

        dateTextView = (TextView) findViewById(R.id.textview_date);
        titleEditText = (EditText) findViewById(R.id.edittext_title);
        contentEditText = (EditText) findViewById(R.id.edittext_content);
        editTextNewGroup = (EditText) findViewById(R.id.edittext_group);

        selectedGroupPosition = getIntent().getIntExtra("selectedGroupPosition",0);
        //Spinner for group
        groupSpinner = (Spinner) findViewById(R.id.spiner_group);
        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if((group_list.size()-1) == position){
                    //when "NEW GROUP" is selected show editText
                    editTextNewGroup.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, group_list);
        groupSpinner.setAdapter(spinnerArrayAdapter);
        //Set default value to the spinner
        groupSpinner.setSelection(selectedGroupPosition);
        //set visibility = GONE
        editTextNewGroup.setVisibility(View.GONE);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        c.set(year,month,day);

        String dateString = year + "-" + month + "-" + day;
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        dateTextView.setText(date);

        //For editting data
        todoEdit = (TODO) getIntent().getSerializableExtra("TODOObjEdit");
        if(todoEdit != null){
            isEditting = true;
            dateTextView.setText(todoEdit.getDate());
            titleEditText.setText(todoEdit.getTitle());
            contentEditText.setText(todoEdit.getContent());
        }
    }


    public void onClickCancel(View view){
        Intent intentCancel = new Intent(this, SecondActivity.class);
        selectedGroupPosition = groupSpinner.getSelectedItemPosition();
        intentCancel.putExtra("selectedGroupPosition",selectedGroupPosition);
        intentCancel.putExtra("selectedGroup",group_list.get(selectedGroupPosition));
        startActivity(intentCancel);
        finish();
    }

    public void onClickSave(View view){
        DatabaseHandler dbHandler = new DatabaseHandler(this);
        title = titleEditText.getText().toString();
        group = groupSpinner.getSelectedItem().toString();
        //if new group is entered the group name is getting from edittext
        if (group == group_list.get(group_list.size() -1))
            group = editTextNewGroup.getText().toString().toUpperCase();
        content = contentEditText.getText().toString();
        if (content.matches("")) content = "Not set";
        date = dateTextView.getText().toString();

        //Show alert if title is empty
        if (title.matches("")){
            AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
            alertDialogFragment.show(fragmentManager,"Alert flagment");
            //Show alert if the tabtitle that is created is already exist
        } else if (group_list.contains(editTextNewGroup.getText().toString().toUpperCase())){
            AlertDialogFragment2 alertDialogFragment = new AlertDialogFragment2();
            alertDialogFragment.show(fragmentManager,"Alert flagment");
        } else{
            //If it is editting
            if(isEditting == true){
                todoEdit.setDate(date);
                todoEdit.setTitle(title);
                todoEdit.setGroup(group);
                todoEdit.setContent(content);
                dbHandler.updateDatabase(todoEdit);

                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
                //New data
            }else{
                TODO todo = new TODO(date, title, group, content);
                dbHandler.writeDatabase(todo);
                Toast.makeText(getApplicationContext(),"SAVED",Toast.LENGTH_SHORT).show();
            }
            isEditting = false;
            Intent intentSave = new Intent(this, SecondActivity.class);
            selectedGroupPosition = groupSpinner.getSelectedItemPosition();
            intentSave.putExtra("selectedGroupPosition",selectedGroupPosition);
            intentSave.putExtra("selectedGroup",group_list.get(selectedGroupPosition));
            startActivity(intentSave);
            finish();
        }

    }

    //TODO)
    public void onClickDelete(View view){
        Log.d("---", "delete clicked");
        DatabaseHandler db = new DatabaseHandler(this);
        String[] ids = new String[1];
        ids[0] = String.valueOf(todoEdit.getId());
        db.deleteFromDatabase(ids);
        Intent intentDelete = new Intent(this, SecondActivity.class);
        Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
        intentDelete.putExtra("selectedGroupPosition",selectedGroupPosition);
        intentDelete.putExtra("selectedGroup",group_list.get(selectedGroupPosition));
        startActivity(intentDelete);
        finish();
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newDateFragment = new DatePickerFragment();
        newDateFragment.show(fragmentManager, "datePicker");
    }

    @Override
    public void onReturnDate(String date) {
        dateTextView.setText(date);
    }

}
