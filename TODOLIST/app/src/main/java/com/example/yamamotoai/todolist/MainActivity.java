package com.example.yamamotoai.todolist;

import android.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity implements
        ActionBar.TabListener {

    // Set up the action bar.
    final ActionBar actionBar = getSupportActionBar();
actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

    // Create the adapter that will return a fragment for each of the three
// primary sections of the activity.
    mSectionsPagerAdapter = new SectionsPagerAdapter(
            getSupportFragmentManager());

// Set up the ViewPager with the sections adapter.
    mViewPager = (ViewPager) findViewById(R.id.pager);
mViewPager.setAdapter(mSectionsPagerAdapter);

}
