package com.example.yamamotoai.todolist.Fragment;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yamamotoai.todolist.Group;
import com.example.yamamotoai.todolist.MainActivity;
import com.example.yamamotoai.todolist.R;
import com.example.yamamotoai.todolist.TODO;
import com.example.yamamotoai.todolist.data.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-08-23.
 */

public class GroupListFragment extends Fragment {

    ListView listView_main;
    GroupListAdapter adapter;
    public static List<TODO> todoList;
    private List<Group> groupList = new ArrayList<Group>();
    private List<String> groupList1 = new ArrayList<String>();
    private List<TODO> todoListInGroup = new ArrayList<TODO>();

    DatabaseHandler db;
    FloatingActionButton fab_add;

    int count;

    GroupListFragmentInterface groupListFragmentInterface;
    public interface GroupListFragmentInterface {
        void onDisplayTodoListPage(int position, String groupName);
        void onDisplayAddingPage(); // group = null
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        groupListFragmentInterface = (GroupListFragmentInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        groupListFragmentInterface = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grouplist, container, false);
//        setHasOptionsMenu(true);

        //set title
        ((MainActivity)getActivity()).setActionbarTitle(getResources().getString(R.string.app_name));

        Bundle arg = getArguments();

        db = new DatabaseHandler(getActivity());
        //Always getting new data
        todoList = new ArrayList<>();
        todoList = db.readDatabase(todoList);
        int numOfTodo = 0;
        int numOfDone = 0;
        int n;
        List<String> group_list = new ArrayList<>();
        List<Integer> nomOfTodo_list = new ArrayList<>();
        for (TODO item : todoList) {
            if (!group_list.contains(item.getGroup().toUpperCase())) {
                group_list.add(item.getGroup().toUpperCase());
            }
        }

        for(int i=0; i < group_list.size(); i++){
            Group group = new Group();
            for(int j=0; j < todoList.size(); j++){
                if (todoList.get(j).getGroup().toUpperCase().contains(group_list.get(i))) {
                    numOfTodo++;
                    if(todoList.get(j).isDone() == true)
                        numOfDone++;
                }
            }
            group.setNumOfTodo(numOfTodo);
            group.setNumOfDone(numOfDone);
            group.setGroup(group_list.get(i));
            groupList.add(group);
            numOfTodo = 0;
            numOfDone = 0;
        }


        listView_main = (ListView) view.findViewById(R.id.listview_main);
        adapter = new GroupListAdapter(getActivity(), groupList);
        listView_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                groupListFragmentInterface.onDisplayTodoListPage(position, groupList.get(position).getGroup());
            }
        });
        listView_main.setAdapter(adapter);

        fab_add = (FloatingActionButton) view.findViewById(R.id.fab_grouplist);
        if(MainActivity.landscap_mode)
            fab_add.setVisibility(View.GONE);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupListFragmentInterface.onDisplayAddingPage();
            }
        });


        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_select).setVisible(false);
    }

    //    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.clear();
//        inflater.inflate(R.menu.menu_main, menu);
//    }
}