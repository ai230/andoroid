package com.example.yamamotoai.todolist.Main;

import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yamamotoai.todolist.Preferences.SettingActivity;
import com.example.yamamotoai.todolist.R;

public class MainActivity extends AppCompatActivity
        implements GroupListFragment.GroupListFragmentInterface,
        AddEditFragment.AddEditFragmentInterface,
        ListInGroupFragment.ListGroupInFragmentInterface,
        SearchResultFragment.SearchResultInterface
        , AllTodolistFragment.AllTodolistFragmentInterface{

    public static final String PREF_KEY_REMINDER = "key_reminder";
    public static final String PREF_KEY_DAY = "key_day";
    public static int NOTIFICATION_DAYS;
    public static boolean NOTIFICATION_REMINDER;

    private String selectedTodoId, selectedGroupName;
    private int selectedGroupPosition;
//    public boolean screensize_large = false;
    private int viewId = R.id.fragmentContainer;

    SearchManager searchManager;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);


        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        SharedPreferences sharedPrefReminder = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPrefReminder = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        NOTIFICATION_REMINDER = sharedPrefReminder.getBoolean(PREF_KEY_REMINDER, false);


        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        sharedPref = getSharedPreferences(PREF_KEY_DAY, Context.MODE_PRIVATE);
        NOTIFICATION_DAYS = sharedPref.getInt(PREF_KEY_DAY, 1);

//        Display display = getWindowManager().getDefaultDisplay();
//        int orientation = Configuration.ORIENTATION_UNDEFINED;
//        if(display.getSize(); == )

//        int screensize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
//        if(screensize == Configuration.SCREENLAYOUT_SIZE_XLARGE){
//            viewId = R.id.leftPaneContainer;
//            screensize_large = true;
//        }

        onDisplayGroupList();

    }

    public void setActionbarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    public void onDisplayGroupList(){

        if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStackImmediate();
        }
        GroupListFragment groupListFragment = new GroupListFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.addToBackStack(null);

        transaction.add(viewId, groupListFragment);
        transaction.commit();

    }

    public void onDisPlayTodoListInGroup(String selectedGroup){

        ListInGroupFragment listInGroupFragment = new ListInGroupFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

//        if(screensize_large == true && selectedTodoId == null)
//            viewId = R.id.rightPaneContainer;

        transaction.add(viewId, listInGroupFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        Bundle bundle = new Bundle();
        bundle.putString("selectedGroup", selectedGroup);
        listInGroupFragment.setArguments(bundle);


    }

    public void onDisplayAddEditFragment(){

        AddEditFragment addEditFragment = new AddEditFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(viewId, addEditFragment);
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
        transaction.add(viewId, searchResults);
        transaction.commit();
        Bundle arg = new Bundle();
        arg.putString("newText", newText);
        searchResults.setArguments(arg);
    }


    /* ---------------------------------------------------------------------- */
    /* Implement of Interface                                                 */
    /* ---------------------------------------------------------------------- */
    //Method for GroupListFragmentInterface
    @Override
    public void onDisplayTodoListPage(int position, String selectedGroup) {
        selectedGroupPosition = position;
        onDisPlayTodoListInGroup(selectedGroup);

    }


    //Method for AddEditFragment , when you pressed save button or delete button
    @Override
    public void onClosePage(String selectedGroup) {

        //Pop all existing fragment that was added to a container
        while(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStackImmediate();
        }

        //add all fragment
        onDisplayGroupList();
        onDisPlayTodoListInGroup(selectedGroup);
    }

    //Method for AddEditFragment
    @Override
    public void onDisplaySettingActivity() {
        Intent intent = new Intent(this, SettingActivity.class);
        intent.putExtra("AccessFromAddEditFragment",true);
        startActivity(intent);
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
//        if(screensize_large){
//            ListInGroupFragment listInGroupFragment = new ListInGroupFragment();
//            Bundle arg = new Bundle();
//            arg.putString("selectedGroup", selectedGroupName);
//            listInGroupFragment.setArguments(arg);
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.replace(viewId, listInGroupFragment);
//            transaction.commit();
//        }

    }


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
//        onDisPlayTodoListInGroup(selectedGroupName);
        onDisplayAddEditFragment();
    }

    @Override
    public void onBackToGroupListInSearch() {
        onDisplayGroupList();
//        if(screensize_large){
//            ListInGroupFragment listInGroupFragment = new ListInGroupFragment();
//            Bundle arg = new Bundle();
//            arg.putString("selectedGroup", selectedGroupName);
//            listInGroupFragment.setArguments(arg);
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.replace(R.id.rightPaneContainer, listInGroupFragment);
//            transaction.commit();
//        }
    }
    /* ---------------------------------------------------------------------- */
    /* Toolbar menu                                                           */
    /* ---------------------------------------------------------------------- */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                onDisPlaySearchResult(newText);
                return true;
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
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.action_search:
                break;

        }
        return super.onOptionsItemSelected(item);
    }



    /* ---------------------------------------------------------------------- */
    /* BackButton                                                             */
    /* ---------------------------------------------------------------------- */

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //If no back stack fragment exist( = 0) show GroupList
        //ToolBar title will be app name or group name
        if(getFragmentManager().getBackStackEntryCount() == 0){
            onDisplayGroupList();
        }else if(getFragmentManager().getBackStackEntryCount() == 1){
            setActionbarTitle(getString(R.string.app_name));
        }else if(getFragmentManager().getBackStackEntryCount() == 2){
            setActionbarTitle(selectedGroupName);
        }

        //when the SearchView is activated it close the searchview
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
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
}