package com.example.yamamotoai.midproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        PageFragment.OnFragmentInteractionListener {

    public static List<TODO> todoList = new ArrayList<>();

    private static File dir;
    public static File file;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/sample";

    public static String selectedTabName;

    // tab title
    public static List<String> tabTitle = new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    FragmentStatePagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("---Main","onCreate");

        dir = new File (path);
        dir.mkdir();
        file = new File(path + "/tododata.txt");

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Everytime reset and get data from the file
        tabTitle.clear();
        todoList.clear();

        try {
            FileInput(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //When you have data from the file, selected tab => first tab and get only the group of data
        if(tabTitle.size() > 0)
            selectedTabName = tabTitle.get(0);

        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));

        // setting Pager
        adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return PageFragment.newInstance(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabTitle.get(position);
            }

            @Override
            public int getCount() {
                return tabTitle.size();
            }
        };

        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Log.d("---tabselected2",selectedTabName + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Set ViewPager to TabLayout
        tabLayout.setupWithViewPager(viewPager);


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
            case R.id.menu_add:
                Intent intent1 = new Intent(MainActivity.this, AdditionActivity.class);
                startActivity(intent1);
                return true;

//            case R.id.action_settings:
//                Intent intent2 = new Intent(MainActivity.this, SettingsActivity.class);
//                startActivity(intent2);
//                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(final int position) {
        String tabName = tabTitle.get(position);
        selectedTabName = tabName;
        Log.d("---tabselected",selectedTabName + position);


        //TODO)
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                PageFragment p = new PageFragment();
//                p.organizedGroup();

//                viewPager.setAdapter(adapter);
//                viewPager.addOnPageChangeListener(this);
//                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void FileInput(File file) throws IOException {
        BufferedReader bufferedReader = null;
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(",");
                int id = Integer.parseInt(tokens[0]);
                String date = tokens[1];
                String title = tokens[2];
                String group = tokens[3];
                String content = tokens[4];
                TODO todo = new TODO(id, date, title, group, content);
                todoList.add(todo);

                if(!tabTitle.contains(group)) tabTitle.add(group);

                Log.d("---line",line);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }

            } catch (IOException ioe) {
                System.out.println("Error in closing the BufferedReader");
            }
        }
    }
}
