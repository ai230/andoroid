package com.example.yamamotoai.fragmentexample2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Log.d("------","ok");
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
////        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //1.it gives you phone s orientation
        Configuration configuration = getResources().getConfiguration();
        //2.FragmentManager
        FragmentManager fragmentManager = getFragmentManager();
        //3.FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();

        if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.d("------","ORIENTATION_LANDSCAPE Fragment1");
//            fragmentManager.beginTransaction().remove(fragment1).commit();
           // fragmentTransaction.replace(R.id.fragment_container1, fragment1);
            fragmentTransaction.replace(android.R.id.content, fragment2);
        }else{
//            fragmentManager.beginTransaction().remove(fragment2).commit();
            Log.d("------","ORIENTATION_Portrait Fragment2");
            fragmentTransaction.replace(android.R.id.content, fragment1);
        }
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
