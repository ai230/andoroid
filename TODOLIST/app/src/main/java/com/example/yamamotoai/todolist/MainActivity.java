package com.example.yamamotoai.todolist;

import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements GroupListFragment.GroupListFragmentInterface,
        AddEditFragment.AddEditFragmentInterface, ListInGroupFragment.ListGroupInFragmentInterface{

    private String selectedId;
    private String selectedGroupName;

    public boolean screensize_large = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        int screensize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        if(screensize != Configuration.SCREENLAYOUT_SIZE_XLARGE)
            screensize_large = false;
        if(screensize_large) {
            onDisplayGroupList();
        }else {
            onDisplayGroupList();
        }

//        Intent intent = getIntent();
//        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
//            String query = intent.getStringExtra(SearchManager.QUERY);
////            doMySearch(query);
//        }
    }

    public void onDisplayGroupList(){
        GroupListFragment groupListFragment = new GroupListFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if(screensize_large)
            transaction.replace(R.id.leftPaneContainer, groupListFragment);
        else
            transaction.replace(R.id.fragmentContainer, groupListFragment);
        transaction.commit();
    }

    public void onDisPlayTodoListInGroup(String selectedGroup){
        ListInGroupFragment listInGroupFragment = new ListInGroupFragment();
        Bundle arg = new Bundle();
        arg.putString("selectedGroup", selectedGroup);
        listInGroupFragment.setArguments(arg);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if(screensize_large)
            if(selectedId == null)
                transaction.replace(R.id.rightPaneContainer, listInGroupFragment);
            else
                transaction.replace(R.id.leftPaneContainer, listInGroupFragment);
        else
            transaction.replace(R.id.fragmentContainer, listInGroupFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onDisplayAddEditFragment(){
        AddEditFragment addEditFragment = new AddEditFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if(screensize_large)
            transaction.replace(R.id.rightPaneContainer, addEditFragment);
        else
            transaction.replace(R.id.fragmentContainer, addEditFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        Bundle arg = new Bundle();
        arg.putString("TODOid",selectedId);
        arg.putString("selectedGroupName",selectedGroupName);
        addEditFragment.setArguments(arg);
    }

    //Method for GroupListFragmentInterface
    @Override
    public void onDisplayTodoListPage(int position, String groupName) {
        ListInGroupFragment listInGroupFragment = new ListInGroupFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if(screensize_large)
            if(selectedId == null)
                transaction.replace(R.id.rightPaneContainer, listInGroupFragment);
            else
                transaction.replace(R.id.leftPaneContainer, listInGroupFragment);
        else
            transaction.replace(R.id.fragmentContainer, listInGroupFragment);

        transaction.addToBackStack(null);
        transaction.commit();

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putString("selectedGroup", groupName);
        listInGroupFragment.setArguments(bundle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
//        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("","");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("",newText);

                return false;
            }
        });
        return true;
    }

    @Override
    public void onClosePage(String selectedGroup) {
        //largesize left frouplist right listInGroulist
        onDisplayGroupList();
        ListInGroupFragment listInGroupFragment = new ListInGroupFragment();
        Bundle arg = new Bundle();
        arg.putString("selectedGroup", selectedGroupName);
        listInGroupFragment.setArguments(arg);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if(screensize_large)
            transaction.replace(R.id.rightPaneContainer, listInGroupFragment);
        else
            transaction.replace(R.id.fragmentContainer, listInGroupFragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
                Intent intent = new Intent(MainActivity.this, SearchResultsActivity.class);
                startActivity(intent);
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    //Method for ListGroupInFragmentInterface
    @Override
    public void onDisplayAddingPage() {
        selectedId = null;
        onDisplayAddEditFragment();
    }

    //Method for ListGroupInFragmentInterface
    @Override
    public void onDisplayAddingPageForEditing(String id, String selectedGroup) {
        selectedId = id;
        selectedGroupName = selectedGroup;
        onDisPlayTodoListInGroup(selectedGroupName);
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
            transaction.replace(R.id.rightPaneContainer, listInGroupFragment);
            transaction.addToBackStack(null);
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

    public void setActionbarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
}
