package com.project.ai.todolist.Fragment;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.ai.todolist.MainActivity;
import com.project.ai.todolist.Notification.NotificationUtil;
import com.project.ai.todolist.R;
import com.project.ai.todolist.TODO;
import com.project.ai.todolist.alert.AlertDialogFragment;
import com.project.ai.todolist.alert.AlertDialogFragment2;
import com.project.ai.todolist.alert.DeleteDialogFragment;
import com.project.ai.todolist.alert.DoneDialogFragment;
import com.project.ai.todolist.data.DatabaseHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yamamotoai on 2017-08-23.
 */

public class AddEditFragment extends Fragment
        implements View.OnClickListener
        , DatePickerFragment.DatePickerFragmentInterface
        , DoneDialogFragment.DoneDialogInterface
        , DeleteDialogFragment.DeleteDialogInterface {

    Context mContext;
    DatabaseHandler dbHandler;

    List<String> group_list = new ArrayList<>();
    private List<TODO> todoList = new ArrayList<TODO>();
    int TODOid = 0;

    Spinner groupSpinner;
    EditText editTextNewGroup;
    TextInputLayout titleEditText, contentEditText;
    TextView dateTextView;
    ImageButton calendarImgBtn;
    LinearLayout linearLayout_date, linearLayout_check;
    Button settingBtn;
    CheckBox doneCheckbox;

    String title, group, content, date;
    boolean isDone;
    Boolean isEditing = false;

    int selectedGroupPosition;
    String selectedGroupName;
    boolean isOnlyDataInGroup;

    AddEditFragmentInterface addEditFragmentInterface;


    public interface AddEditFragmentInterface
    {
        void onClosePage(String selectedGroup);
        void onDisplaySettingActivity();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
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

        View view = inflater.inflate(R.layout.fragment_add, container, false);
        setHasOptionsMenu(true);

        Bundle arg = getArguments();
        if(arg != null){
            selectedGroupName = arg.getString("selectedGroupName");
            //This is for setting default value of groupSpinner;
            selectedGroupPosition = arg.getInt("selectedGroupPosition");
            //if new data id & selectedGroup = null
            if(arg.getString("TODOid") != null){
                TODOid = Integer.parseInt(arg.getString("TODOid"));
                isOnlyDataInGroup = arg.getBoolean("isOnlyData");
                isEditing = true;
            }
        }

        dbHandler = new DatabaseHandler(getActivity());

        /* Group */
        todoList = dbHandler.readDatabase(todoList);
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

        //Spinner
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
                //do nothing so far
            }

        });
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, group_list);
        groupSpinner.setAdapter(spinnerArrayAdapter);
        //Set default value to the spinner
        groupSpinner.setSelection(selectedGroupPosition);

        editTextNewGroup = (EditText) view.findViewById(R.id.edittext_group);
        editTextNewGroup.setVisibility(View.GONE);


        /* Title */
        titleEditText = (TextInputLayout) view.findViewById(R.id.edittext_title);

        /* Note */
        contentEditText = (TextInputLayout) view.findViewById(R.id.edittext_content);



        /* Date */
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        c.set(year, month, day);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        calendarImgBtn = (ImageButton) view.findViewById(R.id.datePickerButton);
        calendarImgBtn.setOnClickListener(this);

        dateTextView = (TextView) view.findViewById(R.id.textview_date1);
        dateTextView.setText(date);

        linearLayout_date = (LinearLayout)view.findViewById(R.id.linealayout_date);
        linearLayout_date.setOnClickListener(this);

        /*Notification*/
        settingBtn = (Button) view.findViewById(R.id.btn_setting);
        settingBtn.setOnClickListener(this);
        setSettingBtnText();

        /*Checkbox*/
        doneCheckbox = (CheckBox) view.findViewById(R.id.checkbox);
        doneCheckbox.setChecked(isDone);
        doneCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(doneCheckbox.isChecked()){
                    isDone = true;
                    DoneDialogFragment doneDialogFragment = new DoneDialogFragment();
                    doneDialogFragment.setTargetFragment(AddEditFragment.this,0);
                    doneDialogFragment.show(getFragmentManager(), "Alert fragment");

                }else{
                    isDone = false;
                }
            }
        });
        linearLayout_check = (LinearLayout) view.findViewById(R.id.linea_check);
        linearLayout_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if it is isChecked it will be unChecked and false
                if(doneCheckbox.isChecked())
                    isDone = false;
                else
                    isDone = true;

                doneCheckbox.setChecked(isDone);

                if(doneCheckbox.isChecked()){
                    DoneDialogFragment doneDialogFragment = new DoneDialogFragment();
                    doneDialogFragment.setTargetFragment(AddEditFragment.this,0);
                    doneDialogFragment.show(getFragmentManager(), "Alert fragment");

                }
            }
        });


        //if it is edit data
        if(isEditing){
            //For editting data
            for (TODO item : todoList) {
                if (item.getId() == TODOid) {
                    titleEditText.getEditText().setText(item.getTitle());
                    contentEditText.getEditText().setText(item.getContent());
                    dateTextView.setText(item.getDate());
                    doneCheckbox.setChecked(item.isDone());
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

    //When it came back from preference it will call and change it to new data of text
    @Override
    public void onResume() {
        super.onResume();
        setSettingBtnText();
    }


    private void setSettingBtnText(){
        if(MainActivity.NOTIFICATION_REMINDER)
            if(MainActivity.NOTIFICATION_DAYS == 1)
                settingBtn.setText(getString(R.string.btn_text_on1, MainActivity.NOTIFICATION_DAYS));
            else
                settingBtn.setText(getString(R.string.btn_text_on2, MainActivity.NOTIFICATION_DAYS));
        else
            settingBtn.setText(R.string.btn_text_off);
    }

    /* ---------------------------------------------------------------------- */
    /* Interface                                                              */
    /* ---------------------------------------------------------------------- */

    //DatePickerFragmentInterface
    @Override
    public void onReturnDate(String date) {
        dateTextView.setText(date);
    }

    //DoneDialogInterface
    @Override
    public void onPositiveBtnClicked() {
        save();
    }

    //DeleteDialogInterface
    @Override
    public void onPositiveDeleteBtnClicked() {
        delete();
    }

    /* ---------------------------------------------------------------------- */
    /* onClick                                                                */
    /* ---------------------------------------------------------------------- */

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.datePickerButton:
                DialogFragment newDateFragment = new DatePickerFragment();
                newDateFragment.setTargetFragment(this,0);
                newDateFragment.show(getFragmentManager(), "datePicker");
                break;

            case R.id.btn_setting:
                addEditFragmentInterface.onDisplaySettingActivity();
                break;

            case R.id.linealayout_date:
                DialogFragment newDateFragment1 = new DatePickerFragment();
                newDateFragment1.setTargetFragment(this,0);
                newDateFragment1.show(getFragmentManager(), "datePicker");
            default:
                break;
        }
    }

    /* ---------------------------------------------------------------------- */
    /* Toolbar menu                                                           */
    /* ---------------------------------------------------------------------- */

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_select).setVisible(false);
        menu.findItem(R.id.action_save).setVisible(true);
        // If it is editing show delete button
        if(isEditing)
            menu.findItem(R.id.action_delete).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.action_save:

                save();

                break;

            case R.id.action_delete:

                DeleteDialogFragment deleteDialogFragment = new DeleteDialogFragment();
                deleteDialogFragment.setTargetFragment(this,0);
                deleteDialogFragment.show(getFragmentManager(),"");

                break;

        }
        return super.onOptionsItemSelected(item);

    }

    public void save() {

        dbHandler = new DatabaseHandler(getActivity());
        title = titleEditText.getEditText().getText().toString();
        group = groupSpinner.getSelectedItem().toString();
        //if new group is entered the group name is getting from edittext
        if (group == group_list.get(group_list.size() -1))
            group = editTextNewGroup.getText().toString().toUpperCase();
        content = contentEditText.getEditText().getText().toString();
        if(doneCheckbox.isChecked())
            isDone = true;
        else
            isDone =false;

        date = dateTextView.getText().toString();

        String s = dateTextView.getText().toString();
        //Show alert if title is empty
        if (group.matches("")){
            AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
            alertDialogFragment.show(getFragmentManager() ,"Alert flagment");

            //Show alert if the group is empty
        }else if(title.matches("")){
            AlertDialogFragment2 alertDialogFragment = new AlertDialogFragment2();
            alertDialogFragment.show(getFragmentManager() ,"Alert flagment");

        }else {
            //If it is editting
            if (isEditing) {
                TODO todoEdit = new TODO();
                todoEdit.setId(TODOid);
                todoEdit.setDate(date);
                todoEdit.setTitle(title);
                todoEdit.setGroup(group);
                todoEdit.setContent(content);
                todoEdit.setDone(isDone);
                dbHandler.updateDatabase(todoEdit);

                Toast.makeText(mContext, "UPDATED", Toast.LENGTH_SHORT).show();
                //New data
            } else {
                TODO todo = new TODO(date, title, group, content, isDone);
                TODOid = (int) dbHandler.writeDatabase(todo);
                Toast.makeText(mContext, "SAVED", Toast.LENGTH_SHORT).show();
            }

            if (MainActivity.NOTIFICATION_REMINDER && !isDone) {
                //setNotification
                NotificationUtil.setNotification(mContext, ListInGroupAdapter.caluculateDayDiff(date), TODOid, date, title);
            }

            //close move to list
            addEditFragmentInterface.onClosePage(group);
        }
    }

    public void delete(){
        String[] ids = new String[1];
        ids[0] = String.valueOf(TODOid);
        dbHandler = new DatabaseHandler(getActivity());
        dbHandler.deleteFromDatabase(ids);
        //close move to list
        //If the data is only one data in the group selectedGroupName will be first one
        if(isOnlyDataInGroup){
            selectedGroupName = group_list.get(0);
        }
        addEditFragmentInterface.onClosePage(selectedGroupName);
        NotificationUtil.cancelNotification(mContext, TODOid);
    }
}