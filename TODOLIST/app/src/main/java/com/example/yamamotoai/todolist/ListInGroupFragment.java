package com.example.yamamotoai.todolist;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yamamotoai.todolist.data.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-08-16.
 */

public class ListInGroupFragment extends Fragment {

    ListView listView;
    ListInGroupAdapter adapter;
    private List<TODO> todoListInGroup;
    private List<TODO> todoList = new ArrayList<TODO>();

    DatabaseHandler db;
    boolean isDeleteBtnClicked = false;
    int selectedGroupPosition;
    FloatingActionButton fab;

    String selectedGroupName;
    Bundle bundle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_todolist, container, false);

        bundle = this.getArguments();
        if(bundle != null) {
            //getting selectedGroup position and name from MainActivity
            selectedGroupPosition = bundle.getInt("position");
            selectedGroupName = bundle.getString("selectedGroup");
        }
        db = new DatabaseHandler(getActivity());
        todoList = db.readDatabase(todoList);

//        MainActivity.todoList = new ArrayList<>();
//        MainActivity.todoList = db.readDatabase(MainActivity.todoList);
        //create only this group of todolist
        organizedGroup(selectedGroupName);

        //setting app title to selectedGroup name
//        getSupportActionBar().setTitle(selectedGroupName);

        fab = (FloatingActionButton) view.findViewById(R.id.fab_second);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("---","clicked");
//                Intent intent = new Intent(getActivity().getBaseContext().SecondFragment.class, AdditionActivity.class);
//                intent.putExtra("selectedGroupPosition",selectedGroupPosition);
//                startActivity(intent);
            }
        });

        //initialize listview
        listView = (ListView)view.findViewById(R.id.listview_second);
        //create adapter
        adapter = new ListInGroupAdapter(getActivity(), todoListInGroup, isDeleteBtnClicked);
        //set adapter to the listview
        listView.setAdapter(adapter);
        //set item listener to the listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.d("---","clicked");

                AddEditFragment addFragment = new AddEditFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, addFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                bundle.putInt("TODOid", todoList.get(position).getId());
                addFragment.setArguments(bundle);

//                Intent intent = new Intent(getActivity().SecondFragment.class, AdditionActivity.class);
//                TODO todo = todoListInGroup.get(position);
//                intent.putExtra("TODOObjEdit", todo);
//                intent.putExtra("selectedGroupPosition", selectedGroupPosition);
//                startActivity(intent);
            }
        });

        return view;
    }

    public void organizedGroup(String selectedGroup){
        todoListInGroup = new ArrayList<>();
        for(TODO todo: todoList){
            if(todo.getGroup().toUpperCase().matches(selectedGroup)){
                todoListInGroup.add(todo);
            }
        }
    }

    public void setDeleteBtn(){
        fab.setImageResource(R.drawable.ic_delete);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO) set delete method
                List<String> arrayTemp = new ArrayList<String>();
                for(TODO item: todoListInGroup){
                    if(item.isSelected)
                        arrayTemp.add(String.valueOf(item.getId()));
                }
                String[] arr = new String[arrayTemp.size()];
                arr = arrayTemp.toArray(arr);
                db.deleteFromDatabase(arr);
                Toast.makeText(getActivity(), "Data delete",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setAddBtn(){
        fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("---","clicked");
//                Intent intent = new Intent(SecondActivity.this, AdditionActivity.class);
//                startActivity(intent);
            }
        });
    }
}
