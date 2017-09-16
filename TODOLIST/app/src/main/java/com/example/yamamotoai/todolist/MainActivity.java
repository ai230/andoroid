package com.example.yamamotoai.todolist;

import android.content.res.Configuration;
import android.support.v4.app.AppLaunchChecker;
import android.support.v4.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.yamamotoai.todolist.Fragment.AddEditFragment;
import com.example.yamamotoai.todolist.Fragment.GroupListFragment;
import com.example.yamamotoai.todolist.Fragment.ListInGroupFragment;
import com.example.yamamotoai.todolist.Fragment.SearchResultFragment;
import com.example.yamamotoai.todolist.Preferences.SettingActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements GroupListFragment.GroupListFragmentInterface,
        AddEditFragment.AddEditFragmentInterface,
        ListInGroupFragment.ListGroupInFragmentInterface,
        SearchResultFragment.SearchResultInterface {

    public static final String PREF_KEY_REMINDER = "key_reminder";
    public static final String PREF_KEY_DAY = "key_day";
    public static int NOTIFICATION_DAYS;
    public static boolean NOTIFICATION_REMINDER;

    private String selectedTodoId, selectedGroupName;
    private int selectedGroupPosition;
    public boolean screensize_large = false;
    public static boolean landscap_mode = false;
    private int viewId = R.id.fragmentContainer;

    SearchManager searchManager;
    SearchView searchView;

    boolean isGroupEmpty;

    boolean isSearching = true;
    boolean isOnlyDataInGroup = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        if(AppLaunchChecker.hasStartedFromLauncher(this)){
            Log.d("","");
        }else{
            Log.d("","");
        }
        Configuration configuration = getResources().getConfiguration();
        if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            landscap_mode = true;
        }else{
            landscap_mode = false;
        }
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        //Getting NOTIFICATION_REMINDER from SharedPreference
        SharedPreferences sharedPrefReminder = PreferenceManager.getDefaultSharedPreferences(this);
        NOTIFICATION_REMINDER = sharedPrefReminder.getBoolean(PREF_KEY_REMINDER, false);

        //Getting NOTIFICATION_DAYS from SharedPreference
        SharedPreferences sharedPref = getSharedPreferences(MainActivity.PREF_KEY_DAY, Context.MODE_PRIVATE);
        MainActivity.NOTIFICATION_DAYS = sharedPref.getInt(MainActivity.PREF_KEY_DAY, 1);

        if(landscap_mode){
            viewId = R.id.leftPaneContainer;
            onDisplayGroupList();
            viewId = R.id.rightPaneContainer;
            if(selectedGroupName == null){
                selectedGroupName = "";
            }
            onDisPlayTodoListInGroup(selectedGroupName);
        }else{
            onDisplayGroupList();
        }

    }

    public void setActionbarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    public void onDisplayGroupList(){

        GroupListFragment groupListFragment = new GroupListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);

        transaction.add(viewId, groupListFragment, "groupListFragment");
        transaction.commit();
    }

    public void onDisPlayTodoListInGroup(String selectedGroup){

        ListInGroupFragment listInGroupFragment = new ListInGroupFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(landscap_mode){
            viewId = R.id.rightPaneContainer;
            selectedGroupName = selectedGroup;
        }
        transaction.add(viewId, listInGroupFragment, "listInGroupFragment");
        transaction.addToBackStack(null);
        transaction.commit();

        Bundle bundle = new Bundle();
        bundle.putString("selectedGroup", selectedGroup);
        listInGroupFragment.setArguments(bundle);

    }

    public void onDisplayAddEditFragment(){

        isSearching = false;
        AddEditFragment addEditFragment = new AddEditFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(viewId, addEditFragment, "addEditFragment");
        transaction.addToBackStack(null);
        transaction.commit();
        Bundle arg = new Bundle();
        arg.putString("TODOid",selectedTodoId);
        arg.putString("selectedGroupName",selectedGroupName);
        arg.putInt("selectedGroupPosition", selectedGroupPosition);
        arg.putBoolean("isOnlyData", isOnlyDataInGroup);
        addEditFragment.setArguments(arg);
        isGroupEmpty = false;
    }


    public void onDisPlaySearchResult(String newText){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        SearchResultFragment searchResults = new SearchResultFragment();
        transaction.replace(R.id.rightPaneContainer, searchResults, "searchfragment");
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
        while(getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStackImmediate();
        }


        if(landscap_mode){
            viewId = R.id.leftPaneContainer;
            onDisplayGroupList();
            viewId = R.id.rightPaneContainer;
            onDisPlayTodoListInGroup(selectedGroup);
        }else{
            //add all fragment
            onDisplayGroupList();
            onDisPlayTodoListInGroup(selectedGroup);
        }

    }

    //Method for AddEditFragment
    @Override
    public void onDisplaySettingActivity() {
        startActivity(new Intent(this, SettingActivity.class));
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
    public void onDisplayAddingPageForEditing(String id, String selectedGroup, boolean isOnlyData) {
        selectedTodoId = id;
        selectedGroupName = selectedGroup;
        isOnlyDataInGroup = isOnlyData;
        onDisplayAddEditFragment();
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
        onDisplayAddEditFragment();
    }

    /* ---------------------------------------------------------------------- */
    /* Toolbar menu                                                           */
    /* ---------------------------------------------------------------------- */
    MenuItem item_save;
    MenuItem item_delete;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        item_save = menu.findItem(R.id.action_save);
        item_save.setVisible(false);
        item_delete = menu.findItem(R.id.action_delete);
        item_delete.setVisible(false);

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

                //if searchView is not active , do nothing
                if(searchView.isIconified() || !isSearching){
                    newText = null;
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
                startActivity(new Intent(this, SettingActivity.class));
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

        //When it comes bake from searchview , find the fragment by tag, and remove it
        Fragment searchFragment = getSupportFragmentManager().findFragmentByTag("searchfragment");
        if(searchFragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(searchFragment)
                    .commit();
        }

        //save and delete button are hidden here
        item_save.setVisible(false);
        item_delete.setVisible(false);


        if(landscap_mode){
            //Pop all existing fragment that was added to a container
            while(getSupportFragmentManager().getBackStackEntryCount() > 0){
                getSupportFragmentManager().popBackStackImmediate();
            }
            viewId = R.id.leftPaneContainer;
            onDisplayGroupList();
            viewId = R.id.rightPaneContainer;
            if(selectedGroupName != null){
                onDisPlayTodoListInGroup(selectedGroupName);
                setActionbarTitle(selectedGroupName);
            }

        }else{
            //If no back stack fragment exist( = 0) show GroupList
            //ToolBar title will be app name or group name
            if(getSupportFragmentManager().getBackStackEntryCount() == 0){
                onDisplayGroupList();
            }else if(getSupportFragmentManager().getBackStackEntryCount() == 1){
                setActionbarTitle(getString(R.string.app_name));
            }else if(getSupportFragmentManager().getBackStackEntryCount() == 2){
                setActionbarTitle(selectedGroupName);
            }
        }

//        when the SearchView is activated it close the searchview
        isSearching = true;
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
        }
    }


    //In order to unfocus from edittext when you clicked outside of edittext
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
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
