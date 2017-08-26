package com.example.yamamotoai.todolist;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
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
import android.widget.Toast;

import com.example.yamamotoai.todolist.alert.AlertDialogFragment;
import com.example.yamamotoai.todolist.alert.AlertDialogFragment2;
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

    DatabaseHandler dbHandler;

    TextView dateTextView;
    TextInputLayout titleEditText;
    TextInputLayout contentEditText;
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

    Boolean isEditing = false;
    int selectedGroupPosition;
    List<String> group_list = new ArrayList<>();
    DatabaseHandler db;
    private List<TODO> todoList = new ArrayList<TODO>();
    int TODOid = 0;
    String selectedGroupName;
    AddEditFragmentInterface addEditFragmentInterface;
    public interface AddEditFragmentInterface
    {
        void onClosePage(String selectedGroup);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        addEditFragmentInterface = (AddEditFragmentInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        addEditFragmentInterface = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        Bundle arg = getArguments();
        if(arg != null){
            //if new data id & selectedGroup = null
            if(arg.getString("TODOid") != null){
                TODOid = Integer.parseInt(arg.getString("TODOid"));
                selectedGroupName = arg.getString("selectedGroupName");
                isEditing = true;
            }

        }

        db = new DatabaseHandler(getActivity());
        todoList = db.readDatabase(todoList);
        int count = 0;
        for (TODO item : todoList) {
            if (!group_list.contains(item.getGroup().toUpperCase())) {
                group_list.add(item.getGroup().toUpperCase());
                if(isEditing == true && item.getGroup().matches(selectedGroupName)) {
                    selectedGroupPosition = count;
                }
                count++;
            }
        }
        group_list.add("NEW GROUP");

        dateTextView = (TextView) view.findViewById(R.id.textview_date);
        titleEditText = (TextInputLayout) view.findViewById(R.id.edittext_title);
        contentEditText = (TextInputLayout) view.findViewById(R.id.edittext_content);
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
                }else{
                    editTextNewGroup.setVisibility(View.GONE);
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

        //if it is edit data
        if(isEditing){
            //For editting data
            for (TODO item : todoList) {
                if (item.getId() == TODOid) {
                    titleEditText.getEditText().setText(item.getTitle());
                    contentEditText.getEditText().setText(item.getContent());
                    dateTextView.setText(item.getDate());
                }
            }
            //set title
            ((MainActivity)getActivity()).setActionbarTitle("EDIT TODO");
        }else{
            //set title
            ((MainActivity)getActivity()).setActionbarTitle("NEW TODO");
        }

        return view;
    }

    //implement DatePickerFragmentInterface
    @Override
    public void onReturnDate(String date) {
        dateTextView.setText(date);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.datePickerButton:
                DialogFragment newDateFragment = new DatePickerFragment();
                newDateFragment.setTargetFragment(this,0);
                newDateFragment.show(getFragmentManager(), "datePicker");
                break;

            case R.id.cancelBtn:
                group = groupSpinner.getSelectedItem().toString();
                //if new group is entered the group name is getting from edittext
                if (group == group_list.get(group_list.size() -1))
                    group = editTextNewGroup.getText().toString().toUpperCase();
                addEditFragmentInterface.onClosePage(group);
                break;

            case R.id.saveBtn:
                dbHandler = new DatabaseHandler(getActivity());
                title = titleEditText.getEditText().getText().toString();
                group = groupSpinner.getSelectedItem().toString();
                //if new group is entered the group name is getting from edittext
                if (group == group_list.get(group_list.size() -1))
                group = editTextNewGroup.getText().toString().toUpperCase();
                content = contentEditText.getEditText().getText().toString();
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
                    if(isEditing == true){
                        TODO todoEdit = new TODO();
                        todoEdit.setId(TODOid);
                        todoEdit.setDate(date);
                        todoEdit.setTitle(title);
                        todoEdit.setGroup(group);
                        todoEdit.setContent(content);
                        dbHandler.updateDatabase(todoEdit);

                        Toast.makeText(getActivity(),"Updated",Toast.LENGTH_SHORT).show();
                        //New data
                    }else{
                        TODO todo = new TODO(date, title, group, content);
                        dbHandler.writeDatabase(todo);
                        Toast.makeText(getActivity(),"SAVED",Toast.LENGTH_SHORT).show();
                    }
                    //close move to list
                    addEditFragmentInterface.onClosePage(group);
                }
                break;

            case R.id.deleteBtn:
                String[] ids = new String[1];
                ids[0] = String.valueOf(TODOid);
                dbHandler = new DatabaseHandler(getActivity());
                dbHandler.deleteFromDatabase(ids);
                //close move to list
                addEditFragmentInterface.onClosePage(selectedGroupName);
                Toast.makeText(getActivity(),"DELETED",Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
}