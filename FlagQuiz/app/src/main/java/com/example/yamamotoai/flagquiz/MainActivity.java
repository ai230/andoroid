package com.example.yamamotoai.flagquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Context context;
    //TODO 1) keys for reading data from SharedPreference, two strings are keynames
    public static final String CHOICES = "pref_numberOfChoices";//value = 6
    public static final String REGIONS = "pref_regionsToInclude";//value = asia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        context = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> temp = sp.getStringSet("pref_regionsToInclude",new HashSet<String>());

         Log.d("in main :",temp.toString());
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

    public Context getContext() {
        return context;
    }

}
