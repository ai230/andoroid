package com.example.yamamotoai.todolist;

import android.app.AlarmManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements GroupListFragment.GroupListFragmentInterface,
        AddEditFragment.AddEditFragmentInterface,
        ListInGroupFragment.ListGroupInFragmentInterface,
        SearchResultFragment.SearchResultInterface
        , AllTodolistFragment.AllTodolistFragmentInterface{

    private String selectedTodoId;
    private String selectedGroupName;
    private int selectedGroupPosition;
    public boolean screensize_large = false;
    private int viewId = R.id.fragmentContainer;

    private int selectedIconId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

//        Display display = getWindowManager().getDefaultDisplay();
//        int orientation = Configuration.ORIENTATION_UNDEFINED;
//        if(display.getSize(); == )

        int screensize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        if(screensize == Configuration.SCREENLAYOUT_SIZE_XLARGE){
            viewId = R.id.leftPaneContainer;
            screensize_large = true;
        }


        onDisplayGroupList();

    }

    public void setActionbarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    public void onDisplayGroupList(){
        GroupListFragment groupListFragment = new GroupListFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        Bundle arg = new Bundle();
        arg.putString("selectedIconId", String.valueOf(selectedIconId));
        groupListFragment.setArguments(arg);
        transaction.replace(viewId, groupListFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onDisPlayTodoListInGroup(String selectedGroup){

        ListInGroupFragment listInGroupFragment = new ListInGroupFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if(screensize_large == true && selectedTodoId == null)
            viewId = R.id.rightPaneContainer;

        transaction.replace(viewId, listInGroupFragment);

        transaction.addToBackStack(null);
        transaction.commit();

        Bundle bundle = new Bundle();
        bundle.putString("selectedGroup", selectedGroup);
        listInGroupFragment.setArguments(bundle);

    }

    public void onDisplayAddEditFragment(){
        AddEditFragment addEditFragment = new AddEditFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(viewId, addEditFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        Bundle arg = new Bundle();
        arg.putString("TODOid",selectedTodoId);
        arg.putString("selectedGroupName",selectedGroupName);
        arg.putInt("selectedGroupPosition", selectedGroupPosition);
        addEditFragment.setArguments(arg);
    }

    public void onDisPlaySearchResult(String newText){
        SearchResultFragment searchResults = new SearchResultFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(viewId, searchResults);
//        transaction.addToBackStack(null);
        transaction.commit();

        Bundle arg = new Bundle();
        arg.putString("newText", newText);
        searchResults.setArguments(arg);
    }

    public void onDisplayAllTodolist(){
        AllTodolistFragment allTodolistFragment = new AllTodolistFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(viewId, allTodolistFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //Method for GroupListFragmentInterface
    @Override
    public void onDisplayTodoListPage(int position, String selectedGroup) {
        selectedGroupPosition = position;
        onDisPlayTodoListInGroup(selectedGroup);

    }


    //Method for AddEditFragment , when you pressed save button or delete button
    @Override
    public void onClosePage(String selectedGroup) {

        getSupportFragmentManager().popBackStack();
        ListInGroupFragment listInGroupFragment = new ListInGroupFragment();
        Bundle arg = new Bundle();
        arg.putString("selectedGroup", selectedGroup);
        listInGroupFragment.setArguments(arg);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(viewId, listInGroupFragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }


    //Method for GroupListFragmentInterface
    @Override
    public void onDisplayAddingPage() {
        selectedTodoId = null;
        onDisplayAddEditFragment();
    }

    //Method for ListGroupInFragmentInterface
    @Override
    public void onDisplayAddingPage(int position, String selectedGroup) {
        selectedTodoId = null;
        if(selectedGroup != null)
            selectedGroupName = selectedGroup;
        onDisplayAddEditFragment();
    }

    //Method for ListGroupInFragmentInterface
    @Override
    public void onDisplayAddingPageForEditing(String id, String selectedGroup) {
        selectedTodoId = id;
        selectedGroupName = selectedGroup;
        onDisplayAddEditFragment();
    }

    //Method for ListGroupInFragmentInterface
    @Override
    public void onBackToGroupList() {
        onDisplayGroupList();
        if(screensize_large){
            ListInGroupFragment listInGroupFragment = new ListInGroupFragment();
            Bundle arg = new Bundle();
            arg.putString("selectedGroup", selectedGroupName);
            listInGroupFragment.setArguments(arg);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(viewId, listInGroupFragment);
//            transaction.addToBackStack(null);
            transaction.commit();
        }

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

    //SearchResulrInterface
    @Override
    public void onDisplayAddingPageInSearch() {
        selectedTodoId = null;
        onDisplayAddEditFragment();
    }

    @Override
    public void onDisplayAddingPageForEditingInSearch(String id, String selectedGroup) {
        selectedTodoId = id;
        selectedGroupName = selectedGroup;
        onDisPlayTodoListInGroup(selectedGroupName);
        onDisplayAddEditFragment();
    }

    @Override
    public void onBackToGroupListInSearch() {
        onDisplayGroupList();
        if(screensize_large){
            ListInGroupFragment listInGroupFragment = new ListInGroupFragment();
            Bundle arg = new Bundle();
            arg.putString("selectedGroup", selectedGroupName);
            listInGroupFragment.setArguments(arg);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.rightPaneContainer, listInGroupFragment);
            transaction.commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //If at least one letter in SearchView, it moves to SearchResultFragment
                //Otherwise stay GroupListFragment
                if(newText.matches("")){
                    onDisplayAllTodolist();
                }else{
                    onDisPlaySearchResult(newText);
                }
                return false;
            }
        });

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
//            case R.id.action_add:
//                Log.d("","");
//                break;
//            case R.id.action_delete:
//                Log.d("","");
//                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
