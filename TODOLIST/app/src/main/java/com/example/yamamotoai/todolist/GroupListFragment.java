package com.example.yamamotoai.todolist;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
    FloatingActionButton fab_add;

    private int selectedIconId;

    GroupListFragmentInterface groupListFragmentInterface;
    public interface GroupListFragmentInterface {
        void onDisplayTodoListPage(int position, String groupName);
        void onDisplayAddingPage();
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
        super.onCreateView(inflater, container, savedInstanceState);
//        LayoutInflater inflater =
        View view = inflater.inflate(R.layout.fragment_grouplist, container, false);

        //set title
        ((MainActivity)getActivity()).setActionbarTitle(getResources().getString(R.string.app_name));

        Bundle arg = getArguments();
        if(arg != null){
            selectedIconId = Integer.parseInt(arg.getString("selectedIconId"));
        }
        db = new DatabaseHandler(getActivity());
        //Always getting new data
        todoList = new ArrayList<>();
        todoList = db.readDatabase(todoList);
        for (TODO item : todoList) {
            if (!groupList.contains(item.getGroup().toUpperCase())) {
                groupList.add(item.getGroup().toUpperCase());
            }
        }

        listView_main = (ListView) view.findViewById(R.id.listview_main);
        adapter = new GroupListAdapter(getActivity(), groupList);
        listView_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.d("---", "clicked");
                groupListFragmentInterface.onDisplayTodoListPage(position, groupList.get(position));

                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
        listView_main.setAdapter(adapter);

        fab_add = (FloatingActionButton) view.findViewById(R.id.fab_grouplist);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("---","clicked");
                groupListFragmentInterface.onDisplayAddingPage();
            }
        });

        return view;
    }


//    private Integer[] mIconIds = {
//            R.drawable.icon_0, R.drawable.icon_1,
//            R.drawable.icon_2, R.drawable.icon_3,
//            R.drawable.icon_4, R.drawable.icon_5,
//            R.drawable.icon_6, R.drawable.icon_7,
//            R.drawable.icon_8, R.drawable.icon_9
//    };

}