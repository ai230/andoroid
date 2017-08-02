package com.example.yamamotoai.flagquiz;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    //TODO 1) keys for reading data from SharedPreference, two strings are keynames
    public static final String CHOICES = "pref_numberOfChoices";//value = 6
    public static final String REGIONS = "pref_regionsToInclude";//value = asia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    //TODO 2) Create onStart()
    @Override
    protected void onStart() {
        super.onStart();
        //TODO 5) Create a quizfragment instance
        MainActivityFragment quizFragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.quizFragment);
        //TODO 6) This method is for updating the options
        quizFragment.upDateGuessRows(PreferenceManager.getDefaultSharedPreferences(this));
        //TODO 7) this method is for updating the regions
        quizFragment.upDateRegions(PreferenceManager.getDefaultSharedPreferences(this));
        //TODO ) Call a method to start the quiz
        quizFragment.startQuiz();
        Log.d("----","onStart()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
