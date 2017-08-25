package com.example.yamamotoai.todolist;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yamamotoai.todolist.data.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GroupListFragment.GroupListFragmentInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GroupListFragment groupListFragment = new GroupListFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, groupListFragment);
        transaction.commit();
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

            case R.id.action_delete:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTodoListInGroup(int position, String groupName) {
        ListInGroupFragment secondFragment = new ListInGroupFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, secondFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putString("selectedGroup", groupName);
        secondFragment.setArguments(bundle);
    }

//    public void setDeleteBtn(){
//        fab.setImageResource(R.drawable.ic_delete);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //TODO) set delete method
//                List<String> arrayTemp = new ArrayList<String>();
//                for(TODO item: todoListInGroup){
//                    if(item.isSelected)
//                        arrayTemp.add(String.valueOf(item.getId()));
//                }
//                String[] arr = new String[arrayTemp.size()];
//                arr = arrayTemp.toArray(arr);
//                db.deleteFromDatabase(arr);
//                Toast.makeText(SecondActivity.this, "Data delete",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//
//    public void setAddBtn(){
//        fab.setImageResource(R.drawable.ic_add);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SecondActivity.this, AdditionActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
}
