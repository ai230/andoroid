package com.project.ai.todolist.Fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.project.ai.todolist.MainActivity;
import com.project.ai.todolist.MyComparator;
import com.project.ai.todolist.Notification.NotificationUtil;
import com.project.ai.todolist.R;
import com.project.ai.todolist.TODO;
import com.project.ai.todolist.data.DatabaseHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yamamotoai on 2017-08-16.
 */

public class ListInGroupFragment extends Fragment {

    ListView listView;
    ListInGroupAdapter adapter;
    private List<TODO> todoListInGroup;
    private List<TODO> todoListInGroupDone;
    private List<TODO> todoList = new ArrayList<TODO>();

    DatabaseHandler db;
    boolean isDeleteBtnClicked = false;
    int selectedGroupPosition;
    FloatingActionButton fab_add;

    String selectedGroupName;
    Bundle bundle;

    boolean isOnlyDataInGroup;
    boolean isEnableSelect;

    private ListGroupInFragmentInterface listGroupInFragmentInterface;
    public interface ListGroupInFragmentInterface
    {
        void onDisplayAddingPage(int position, String selectedGroup);
        void onDisplayAddingPageForEditing(String SelectedTodoId, String selectedGroupName, boolean isOnlyData);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listGroupInFragmentInterface = (ListGroupInFragmentInterface)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listGroupInFragmentInterface = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_todolist, container, false);

        setHasOptionsMenu(true);
        bundle = this.getArguments();
        if(bundle != null) {
            //getting selectedGroup position and name from MainActivity
//            selectedGroupPosition = bundle.getInt("position");
            selectedGroupName = bundle.getString("selectedGroup");
        }
        //set title
        ((MainActivity)getActivity()).setActionbarTitle(selectedGroupName);

        //read new data from database
        db = new DatabaseHandler(getActivity());
        todoList = new ArrayList<>();
        todoList = db.readDatabase(todoList);

        //create only this group of todolist
        isOnlyDataInGroup = false;
        organizedGroup(selectedGroupName);

        //setting app title to selectedGroup name
//        getSupportActionBar().setTitle(selectedGroupName);

        fab_add = (FloatingActionButton) view.findViewById(R.id.fab_todolist);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listGroupInFragmentInterface.onDisplayAddingPage(selectedGroupPosition, selectedGroupName);
            }
        });

        //Sort by date using comparator
        Collections.sort(todoListInGroup, MyComparator.DateComparator);
        Collections.sort(todoListInGroupDone, MyComparator.DateComparator);

        todoListInGroup.addAll(todoListInGroupDone);

        //initialize listview
        listView = (ListView)view.findViewById(R.id.listview_second);
        //create adapter
        adapter = new ListInGroupAdapter(getActivity(), todoListInGroup, isDeleteBtnClicked);
        //set adapter to the listview
        listView.setAdapter(adapter);
        //check if data is only one or not for delete todo
        if(todoListInGroup.size() == 1)
            isOnlyDataInGroup = true;
        //set item listener to the listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                listGroupInFragmentInterface.onDisplayAddingPageForEditing(String.valueOf(todoListInGroup.get(position).getId()), selectedGroupName, isOnlyDataInGroup);

            }
        });

        return view;
    }

    public void organizedGroup(String selectedGroup){
        todoListInGroup = new ArrayList<>();
        todoListInGroupDone = new ArrayList<>();
        for(TODO todo: todoList){
            if(todo.getGroup().toUpperCase().matches(selectedGroup)){
                if(todo.isDone())
                    todoListInGroupDone.add(todo);
                else
                    todoListInGroup.add(todo);
            }
        }
    }

    public void setDeleteBtn(){
        fab_add.setImageResource(R.drawable.ic_delete);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> arrayTemp = new ArrayList<String>();

                for(int i = todoListInGroup.size()-1; i >= 0; i--){
                    TODO todo = todoListInGroup.get(i);
                    if(todo.isSelected()) {
                        arrayTemp.add(String.valueOf(todo.getId()));
                        todoListInGroup.remove(i);
                    }
                }

                for(String id: arrayTemp){
                    String[] ids = new String[1];
                    ids[0] = id;
                    DatabaseHandler dbHandler = new DatabaseHandler(getActivity());
                    dbHandler.deleteFromDatabase(ids);
                    NotificationUtil.cancelNotification(getActivity(), Integer.parseInt(id));
                }

                ListInGroupAdapter.isEnabledDelete = false;
                adapter.notifyDataSetChanged();
                setAddBtn();

         }
        });

    }

    public void setAddBtn(){
        fab_add.setImageResource(R.drawable.ic_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listGroupInFragmentInterface.onDisplayAddingPage(selectedGroupPosition, selectedGroupName);
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_save).setVisible(false);
        menu.findItem(R.id.action_delete).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(true);
        menu.findItem(R.id.action_select).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_select:

                if(ListInGroupAdapter.isEnabledDelete){
                    ListInGroupAdapter.isEnabledDelete = false;
                    setAddBtn();
                }else{
                    ListInGroupAdapter.isEnabledDelete = true;
                    setDeleteBtn();
                }

                ((ListInGroupAdapter) listView.getAdapter()).notifyDataSetChanged();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
