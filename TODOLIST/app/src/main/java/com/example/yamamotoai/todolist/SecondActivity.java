package com.example.yamamotoai.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamotoai on 2017-08-16.
 */

public class SecondActivity extends AppCompatActivity {

    ListView listView;
    SecondActivityAdapter adapter;
    private List<TODO> todoListInGroup;

    DatabaseHandler db;
    boolean isDeleteBtnClicked = false;
    int selectedGroupPosition;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);

        //add toolbar back button
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //getting selectedGroup position and name from MainActivity and AdditionActivity
        selectedGroupPosition = getIntent().getIntExtra("selectedGroupPosition",0);
        String selectedGroupName = getIntent().getStringExtra("selectedGroup");

        db = new DatabaseHandler(this);
        MainActivity.todoList = new ArrayList<>();
        MainActivity.todoList = db.readDatabase(MainActivity.todoList);
        //create only this group of todolist
        organizedGroup(selectedGroupName);

        //setting app title to selectedGroup name
        getSupportActionBar().setTitle(selectedGroupName);

        fab = (FloatingActionButton) findViewById(R.id.fab_second);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, AdditionActivity.class);
                intent.putExtra("selectedGroupPosition",selectedGroupPosition);
                startActivity(intent);
            }
        });

        //initialize listview
        listView = (ListView)findViewById(R.id.listview_second);
        //create adapter
        adapter = new SecondActivityAdapter(this, todoListInGroup, isDeleteBtnClicked);
        //set adapter to the listview
        listView.setAdapter(adapter);
        //set item listener to the listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(SecondActivity.this, AdditionActivity.class);
                TODO todo = todoListInGroup.get(position);
                intent.putExtra("TODOObjEdit", todo);
                intent.putExtra("selectedGroupPosition", selectedGroupPosition);
                startActivity(intent);
            }
        });
    }

    public void organizedGroup(String selectedGroup){
        todoListInGroup = new ArrayList<>();
        for(TODO todo: MainActivity.todoList){
             if(todo.getGroup().toUpperCase().matches(selectedGroup)){
                todoListInGroup.add(todo);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                finish();
            case R.id.action_settings:
                break;
            case R.id.action_delete:
                if (isDeleteBtnClicked){
                    isDeleteBtnClicked = false;
                    item.setIcon(R.drawable.ic_delete);
                    setAddBtn();
                } else {
                    isDeleteBtnClicked = true;
                    item.setIcon(R.drawable.ic_delete_sweep);
                    setDeleteBtn();
                }

                adapter = new SecondActivityAdapter(this,todoListInGroup,isDeleteBtnClicked);
//                adapter.notifyDataSetChanged();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Log.d("---","clicked");
                        Intent intent = new Intent(SecondActivity.this, AdditionActivity.class);
                        intent.putExtra("selectedGroupPosition",position);
                        startActivity(intent);
                    }
                });
                listView.setAdapter(adapter);
                break;
            case R.id.action_search:
                break;
        }
        return super.onOptionsItemSelected(item);
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
                Toast.makeText(SecondActivity.this, "Data delete",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setAddBtn(){
        fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, AdditionActivity.class);
                startActivity(intent);
            }
        });
    }
}
