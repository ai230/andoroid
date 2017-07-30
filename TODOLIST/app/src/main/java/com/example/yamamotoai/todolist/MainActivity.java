package com.example.yamamotoai.todolist;

import android.content.Context;
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
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager mPager;
    private SlidingTabLayout mtabs;

    private DrawerLayout mDrawer;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        mDrawer = (DrawerLayout) findViewById(R.id.drawerlayout);
//
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
//
//        drawerFragment.setUp(R.id.fragment_navigation_drawer.(DrawerLayout)findViewById(R.id.drawerlayout).toolbar);

        context = this;
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        mtabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mtabs.setViewPager(mPager);


    }

    //    class MyPagerAdapter extends FragmentPagerAdapter{
//
//        String[] tabs;
//        public MyPagerAdapter(FragmentManager fm) {
//            super(fm);
//            tabs = getResources().getStringArray(R.array.tabs);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            MyFragment myfragment = MyFragment.getInstance(position);
//            return myfragment;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return tabs[position];
//        }
//
//        @Override
//        public int getCount() {
//            return 3;
//        }
//    }

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
            textView = (TextView) layout.findViewById(R.id.textview);
            Bundle bundle = getArguments();
            if (bundle != null) {
                textView.setText("The page number is " + bundle.getInt("position"));
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
            case android.R.id.home:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}