package com.example.yamamotoai.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView_main;
    MainActivityAdapter adapter;
    public static  List<TODO> todoList;
    private List<String> groupList = new ArrayList<String>();

    DatabaseHandler db;
    boolean isDeleteBtnClicked = false;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab_main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdditionActivity.class);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        db = new DatabaseHandler(this);
        //Always getting new data
        todoList = new ArrayList<>();
        todoList = db.readDatabase(todoList);
        for(TODO item : todoList){
            if(!groupList.contains(item.getGroup().toUpperCase()))
                groupList.add(item.getGroup().toUpperCase());
        }

        listView_main = (ListView) findViewById(R.id.listview_main);
        adapter = new MainActivityAdapter(this,groupList);
        listView_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                 @Override
                                                 public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                                     Log.d("---","clicked");
                                                     Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                                     intent.putExtra("selectedGroup",groupList.get(position));
                                                     intent.putExtra("selectedGroupPosition",position);
                                                     startActivity(intent);
                                                     //Toast.makeText(this, "clicked",Toast.LENGTH_SHORT).show();
                                                 }
                                             });

        listView_main.setAdapter(adapter);
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
            case R.id.action_settings:
                break;

            case R.id.action_search:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

//    public void setDeleteBtn(){
//        fab.setImageResource(R.drawable.ic_delete);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //TODO) set delete method
//                Toast.makeText(MainActivity.this, "clicked delete",Toast.LENGTH_SHORT).show();
//                String[] groups = {};
//                for(int i = 0; i > todoList.size(); i++){
//                    TODO todo = todoList.get(i);
//                    if(todo.isSelected == true)
//                        groups[i] = todo.getGroup();
//                }
//                db.deleteInMain(groups);
//            }
//        });
//    }
//
//    public void setAddBtn(){
//        fab.setImageResource(R.drawable.ic_add);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, AdditionActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
}
