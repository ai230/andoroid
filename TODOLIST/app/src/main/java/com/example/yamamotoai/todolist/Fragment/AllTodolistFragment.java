package com.example.yamamotoai.todolist.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yamamotoai.todolist.MainActivity;
import com.example.yamamotoai.todolist.R;
import com.example.yamamotoai.todolist.TODO;
import com.example.yamamotoai.todolist.data.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-09-07.
 */

public class AllTodolistFragment extends Fragment {

    ListView listView;
    AllTodolistAdapter adapter;
    private List<TODO> todoList = new ArrayList<TODO>();

    DatabaseHandler db;
    Bundle bundle;
    FloatingActionButton fab_add;

    private AllTodolistFragmentInterface allTodolistFragmentInterface;
    public interface AllTodolistFragmentInterface
    {
        void onDisplayAddingPage();
        void onDisplayAddingPageForEditing(String SelectedTodoId, String selectedGroupName);
        void onBackToGroupList();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        allTodolistFragmentInterface = (AllTodolistFragmentInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        allTodolistFragmentInterface = null;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all_todolist, container, false);

        //read new data from database
        db = new DatabaseHandler(getActivity());
        todoList = db.readDatabase(todoList);

        fab_add = (FloatingActionButton) view.findViewById(R.id.fab_todolist);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                allTodolistFragmentInterface.onDisplayAddingPage();
            }
        });

        //initialize listview
        listView = (ListView)view.findViewById(R.id.listview_second);
        //create adapter
        adapter = new AllTodolistAdapter(getActivity(), todoList);
        //set adapter to the listview
        listView.setAdapter(adapter);
        //set item listener to the listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                allTodolistFragmentInterface.onDisplayAddingPageForEditing(String.valueOf(todoList.get(position).getId()), todoList.get(position).getGroup());
            }
        });

        return view;
    }
}
