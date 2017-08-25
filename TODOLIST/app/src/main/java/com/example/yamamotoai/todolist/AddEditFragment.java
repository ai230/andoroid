package com.example.yamamotoai.todolist;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yamamotoai.todolist.data.DatabaseHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yamamotoai on 2017-08-23.
 */

public class AddEditFragment extends Fragment implements View.OnClickListener, DatePickerFragment.DatePickerFragmentInterface {

    TextView dateTextView;
    EditText titleEditText;
    EditText contentEditText;
    EditText editTextNewGroup;
    ImageButton calenderImgBtn;
    Spinner groupSpinner;
    FragmentManager fragmentManager = getFragmentManager();

    Button cancelBtn;
    Button saveBtn;
    Button deleteBtn;

    String title;
    String group;
    String content;
    String date;

    TODO todoEdit;
    Boolean isEditting = false;
    int selectedGroupPosition;
    List<String> group_list = new ArrayList<>();
    DatabaseHandler db;
    private List<TODO> todoList = new ArrayList<TODO>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        Bundle bundle = new Bundle();
        int TODOid = bundle.getInt("TODOid");
        db = new DatabaseHandler(getActivity());
        todoList = db.readDatabase(todoList);
        for (TODO item : todoList) {
            if (!group_list.contains(item.getGroup().toUpperCase()))
                group_list.add(item.getGroup().toUpperCase());
        }
        group_list.add("NEW GROUP");

        dateTextView = (TextView) view.findViewById(R.id.textview_date);
        titleEditText = (EditText) view.findViewById(R.id.edittext_title);
        contentEditText = (EditText) view.findViewById(R.id.edittext_content);
        editTextNewGroup = (EditText) view.findViewById(R.id.edittext_group);
        calenderImgBtn = (ImageButton) view.findViewById(R.id.datePickerButton);
        calenderImgBtn.setOnClickListener(this);
        cancelBtn = (Button) view.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);
        saveBtn = (Button) view.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);
        deleteBtn = (Button) view.findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(this);


        //Spinner for group
        groupSpinner = (Spinner) view.findViewById(R.id.spiner_group);
        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((group_list.size() - 1) == position) {
                    //when "NEW GROUP" is selected show editText
                    editTextNewGroup.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, group_list);
        groupSpinner.setAdapter(spinnerArrayAdapter);
        //Set default value to the spinner
        groupSpinner.setSelection(selectedGroupPosition);
        //set visibility = GONE
        editTextNewGroup.setVisibility(View.GONE);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        c.set(year, month, day);

        String dateString = year + "-" + month + "-" + day;
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        dateTextView.setText(date);

        //For editting data
        for (TODO item : todoList) {
            if (item.getId() == TODOid)
                titleEditText.setText(item.getTitle());
            contentEditText.setText(item.getContent());
            dateTextView.setText(item.getDate());
        }


        return view;
    }

    //implement DatePickerFragment.DatePickerFragmentInterface
    @Override
    public void onReturnDate(String date) {
        dateTextView.setText(date);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.datePickerButton:
                DialogFragment newDateFragment = new DatePickerFragment();
                newDateFragment.show(getFragmentManager(), "datePicker");
                break;
            case R.id.cancelBtn:
                break;
            case R.id.saveBtn:
                break;
            case R.id.deleteBtn:
                break;
            default:
                break;
        }
    }
}