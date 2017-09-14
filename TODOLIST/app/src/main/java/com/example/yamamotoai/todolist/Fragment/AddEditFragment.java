package com.example.yamamotoai.todolist.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import com.example.yamamotoai.todolist.MainActivity;
import com.example.yamamotoai.todolist.Notification.NotificationUtil;
import com.example.yamamotoai.todolist.R;
import com.example.yamamotoai.todolist.TODO;
import com.example.yamamotoai.todolist.alert.AlertDialogFragment;
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

    Context mContext;
    DatabaseHandler dbHandler;

    TextView dateTextView;
    TextInputLayout titleEditText, contentEditText;
    Button settingBtn;
    EditText editTextNewGroup;
    ImageButton calendarImgBtn;
    Spinner groupSpinner;

    String title, group, content, date;

    Boolean isEditing = false;
    int selectedGroupPosition;
    List<String> group_list = new ArrayList<>();
    private List<TODO> todoList = new ArrayList<TODO>();
    int TODOid = 0;
    String selectedGroupName;

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
                isEditing = true;
            }
        }

        dbHandler = new DatabaseHandler(getActivity());
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

        dateTextView = (TextView) view.findViewById(R.id.textview_date);
        titleEditText = (TextInputLayout) view.findViewById(R.id.edittext_title);
        contentEditText = (TextInputLayout) view.findViewById(R.id.edittext_content);
        editTextNewGroup = (EditText) view.findViewById(R.id.edittext_group);
        calendarImgBtn = (ImageButton) view.findViewById(R.id.datePickerButton);
        calendarImgBtn.setOnClickListener(this);

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

        settingBtn = (Button) view.findViewById(R.id.btn_setting);
        settingBtn.setOnClickListener(this);
        setSettingBtnText();

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

            case R.id.btn_setting:
                addEditFragmentInterface.onDisplaySettingActivity();
                break;
            default:
                break;
        }
    }

    /* ---------------------------------------------------------------------- */
    /* Toolbar menu                                                           */
    /* ---------------------------------------------------------------------- */

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //Menu for MainActivity clear
        menu.clear();
        //Create new menu for edit todo or new todo
        if(isEditing)
            inflater.inflate(R.menu.menu_add_edit, menu);
        else
            inflater.inflate(R.menu.menu_new, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.action_save:
                dbHandler = new DatabaseHandler(getActivity());
                title = titleEditText.getEditText().getText().toString();
                group = groupSpinner.getSelectedItem().toString();
                //if new group is entered the group name is getting from edittext
                if (group == group_list.get(group_list.size() -1))
                    group = editTextNewGroup.getText().toString().toUpperCase();
                content = contentEditText.getEditText().getText().toString();
                date = dateTextView.getText().toString();

                //Show alert if title is empty
                if (title.matches("")){
                    AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
                    alertDialogFragment.show(getFragmentManager() ,"Alert flagment");
                    //Show alert if the tabtitle that is created is already exist
                } else{
                    //If it is editting
                    if(isEditing){
                        TODO todoEdit = new TODO();
                        todoEdit.setId(TODOid);
                        todoEdit.setDate(date);
                        todoEdit.setTitle(title);
                        todoEdit.setGroup(group);
                        todoEdit.setContent(content);
                        dbHandler.updateDatabase(todoEdit);

                        Toast.makeText(mContext,"Updated",Toast.LENGTH_SHORT).show();
                        //New data
                    }else{
                        TODO todo = new TODO(date, title, group, content);
                        TODOid = (int) dbHandler.writeDatabase(todo);
                        Toast.makeText(mContext,"SAVED",Toast.LENGTH_SHORT).show();
                    }

                    if(MainActivity.NOTIFICATION_REMINDER){
                        //setNotification
                        NotificationUtil.setNotification(mContext, ListInGroupAdapter.caluculateDayDiff(date),TODOid, date, title);
                    }

                    //close move to list
                    addEditFragmentInterface.onClosePage(group);
                }
                break;

            case R.id.action_delete:
                String[] ids = new String[1];
                ids[0] = String.valueOf(TODOid);
                dbHandler = new DatabaseHandler(getActivity());
                dbHandler.deleteFromDatabase(ids);
                //close move to list
                addEditFragmentInterface.onClosePage(selectedGroupName);
                NotificationUtil.cancelNotification(mContext, TODOid);
                break;

        }
        return super.onOptionsItemSelected(item);

    }

}