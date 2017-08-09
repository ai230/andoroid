package com.example.yamamotoai.midproject;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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
import java.util.concurrent.TimeUnit;

/**
 * Created by yamamotoai on 2017-08-01.
 */

public class AdditionActivity extends AppCompatActivity implements DatePickerFragment.DatePickerFragmentInterface {

    TextView dateTextView;
    EditText titleEditText;
    EditText contentEditText;
    EditText editTextNewGroup;
    TextView daysDiffTextView;

    Spinner groupSpinner;
    String dateString;
    FragmentManager fragmentManager = getFragmentManager();

    String title;
    String group;
    String content;
    String date;

    int tabSize;
    TODO todoEdit;
    Boolean isEditting = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtion);

        MainActivity.tabTitle.add("NEW GROUP");
        dateTextView = (TextView) findViewById(R.id.textview_date);
        titleEditText = (EditText) findViewById(R.id.edittext_title);
        contentEditText = (EditText) findViewById(R.id.edittext_content);
        editTextNewGroup = (EditText) findViewById(R.id.edittext_group);
        tabSize = MainActivity.tabTitle.size();

        //Spinner for group
        groupSpinner = (Spinner) findViewById(R.id.spiner_group);
        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(tabSize-1 == position){
                    //when "NEW GROUP" is selected show editText
                    editTextNewGroup.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainActivity.tabTitle); //selected item will look like a spinner set from XML
        groupSpinner.setAdapter(spinnerArrayAdapter);
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
        Intent intentCancel = new Intent(this, MainActivity.class);
        startActivity(intentCancel);
    }

    public void onClickSave(View view){

        title = titleEditText.getText().toString();
        group = groupSpinner.getSelectedItem().toString();
        if (group == MainActivity.tabTitle.get(tabSize -1)) group = editTextNewGroup.getText().toString().toUpperCase();
        content = contentEditText.getText().toString();
        if (content.matches("")) content = "Not set";
        date = dateTextView.getText().toString();

        //Show alert if title is empty
        if (title.matches("")){
            AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
            alertDialogFragment.show(fragmentManager,"Alert flagment");
            //Show alert if the tabtitle that is created is already exist
        } else if (MainActivity.tabTitle.contains(editTextNewGroup.getText().toString().toUpperCase())){
            AlertDialogFragment2 alertDialogFragment = new AlertDialogFragment2();
            alertDialogFragment.show(fragmentManager,"Alert flagment");
        } else{
            //If it is editting
            if(isEditting == true){

                todoEdit = new TODO(todoEdit.getId(), date, title, group, content);
                MainActivity.todoList.set(todoEdit.getId(),todoEdit);
                //File data will be deleted and recreated with the arraylist
                MainActivity.file.delete();
                for(int i = 0; i < MainActivity.todoList.size(); i++){
                    TODO todo = MainActivity.todoList.get(i);
                    String date = todo.getDate();
                    String title = todo.getTitle();
                    String group = todo.group;
                    String content = todo.getContent();
                    String outPutData = i + "," + date + "," + title + "," + group + "," + content;
                    try {
                        FileOutputAppend(MainActivity.file,outPutData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
                //New data
            }else{
                String outPutData = MainActivity.todoList.size() + "," + date + "," + title + "," + group + "," + content;
                try {
                    FileOutputAppend(MainActivity.file, outPutData);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Toast.makeText(getApplicationContext(),"SAVED",Toast.LENGTH_SHORT).show();
            }
            isEditting = false;
            Intent intentSave = new Intent(this, MainActivity.class);
            startActivity(intentSave);
        }

    }


    public void onClickDelete(View view){
        Log.d("---", "delete clicked");
        Intent intentDelete = new Intent(this, MainActivity.class);

        //Remove from arraylist
        MainActivity.todoList.remove(todoEdit.getId());
        //File data will be deleted and recreated with the arraylist
        MainActivity.file.delete();
            for(int i = 0; i < MainActivity.todoList.size(); i++){
                TODO todo = MainActivity.todoList.get(i);
                String date = todo.getDate();
                String title = todo.getTitle();
                String group = todo.group;
                String content = todo.getContent();
                String outPutData = i + "," + date + "," + title + "," + group + "," + content;
                try {
                    FileOutputAppend(MainActivity.file,outPutData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
        startActivity(intentDelete);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newDateFragment = new DatePickerFragment();
        newDateFragment.show(fragmentManager, "datePicker");
    }

    @Override
    public void onReturnDate(String date) {
        dateTextView.setText(date);
    }

    public void FileOutputAppend(File file, String outPutData) throws IOException {
        try{
            FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            //This will add a new line to the file content
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(outPutData);
            printWriter.close();
            System.out.println("Data successfully appended at the end of file");

        }catch(IOException ioe){
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
        }
    }

}
