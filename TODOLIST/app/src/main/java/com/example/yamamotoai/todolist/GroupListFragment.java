package com.example.yamamotoai.todolist;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
    private List<String> groupList = new ArrayList<String>();

    DatabaseHandler db;
    boolean isDeleteBtnClicked = false;
    FloatingActionButton fab;

    GroupListFragmentInterface groupListFragmentInterface;

    public interface GroupListFragmentInterface {
        public void onTodoListInGroup(int position, String groupName);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        groupListFragmentInterface = (GroupListFragmentInterface) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
//        LayoutInflater inflater =
        View view = inflater.inflate(R.layout.fragment_grouplist, container, false);

        db = new DatabaseHandler(getActivity());
        //Always getting new data
        todoList = new ArrayList<>();
        todoList = db.readDatabase(todoList);
        for (TODO item : todoList) {
            if (!groupList.contains(item.getGroup().toUpperCase()))
                groupList.add(item.getGroup().toUpperCase());
        }

        listView_main = (ListView) view.findViewById(R.id.listview_main);
        adapter = new GroupListAdapter(getActivity(), groupList);
        listView_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.d("---", "clicked");
                groupListFragmentInterface.onTodoListInGroup(position, groupList.get(position));

//  Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                intent.putExtra("selectedGroup",groupList.get(position));
//                intent.putExtra("selectedGroupPosition",position);
//                startActivity(intent);
                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_SHORT).show();
            }
        });

        listView_main.setAdapter(adapter);

        return view;
    }
}