package com.example.yamamotoai.todolist;

import android.content.Context;
import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private ViewPager mPager;
    private SlidingTabLayout mtabs;

//    private DrawerLayout mDrawer;
    Context context;

    List<TODO> todoList = new ArrayList<>();
    private ListView listView;
//    private RecyclerView recyclerView;
    private TodoListAdapter todoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
//
//        drawerFragment.setUp(R.id.fragment_navigation_drawer.(DrawerLayout)findViewById(R.id.drawerlayout).toolbar);

        context = this;

//        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        TODO todo1 = new TODO( String date, String title, String group, String content);
        todoListAdapter = new TodoListAdapter(this, todoList);

        Vector<View> pages = new Vector<View>();
        listView = (ListView) findViewById(R.id.listview);
        ListView listview1 = new ListView(context);
        ListView listview2 = new ListView(context);
        ListView listview3 = new ListView(context);

//        pages.add(listView);
        pages.add(listview1);
        pages.add(listview2);
        pages.add(listview3);

        CustomPagerAdapter adapter = new CustomPagerAdapter(context,pages);


        mPager = (ViewPager) findViewById(R.id.pager);
//        mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        mPager.setAdapter(adapter);
        mtabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mtabs.setViewPager(mPager);


        listView.setAdapter((ListAdapter) todoListAdapter);
        listview1.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,new String[]{"A1","B1","C1","D1"}));
        listview2.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,new String[]{"A2","B2","C2","D2"}));
        listview3.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,new String[]{"A3","B3","C3","D3"}));
        TODO todo1 = (TODO) getIntent().getSerializableExtra("MyClass");
        if(todo1 != null){
            Log.d("---", todo1.getTitle());
            todoList.add(todo1);
        }
        Log.d("---", String.valueOf(todoList.size()));
    }


    public static class MyFragment extends Fragment {
        private TextView textView;

        public static MyFragment getInstance(int position) {
            MyFragment myFragment = new MyFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            myFragment.setArguments(args);
            return myFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.tab_item, container, false);
            textView = (TextView) layout.findViewById(R.id.textview_list_row1);
            Bundle bundle = getArguments();
            if (bundle != null) {
                if(bundle.getInt("position") == 0){
                    textView.setText("The page number is 0");
                }else if(bundle.getInt("position") == 1){
                    textView.setText("The page number is 1");
                }else{
                    textView.setText("The page number is else");
                }

            }
            return layout;
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
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent1 = new Intent(this, AdditionActivity.class);
                startActivity(intent1);
                return true;

            case R.id.action_settings:
                Intent intent2 = new Intent(this, PreferencesActivity.class);
                startActivity(intent2);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}