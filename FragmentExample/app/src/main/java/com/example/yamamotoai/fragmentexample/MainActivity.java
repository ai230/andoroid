package com.example.yamamotoai.fragmentexample;

import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
//change
//import android.support.v4.app.FragmentManager;
import android.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        //1.it gives you phone s orientation
        Configuration configuration = getResources().getConfiguration();
        //2.FragmentManager
        FragmentManager fragmentManager = getFragmentManager();
        //3.FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        PortraitMode portraitMode = new PortraitMode();
        LandscapeMode landscapeMode = new LandscapeMode();
        if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {

            Log.d("---","ORIENTATION_PORTRAIT");
           // fragmentTransaction.replace(android.R.id.content, portraitMode);
            //fragmentTransaction.add(android.R.id.content, landscapeMode);

        } else {
            fragmentTransaction.remove(portraitMode);
            Log.d("---","ORIENTATION_ELSE");
          //  fragmentTransaction.replace(android.R.id.content, landscapeMode);
        }
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
