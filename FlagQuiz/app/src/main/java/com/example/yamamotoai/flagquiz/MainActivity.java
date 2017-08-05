package com.example.yamamotoai.flagquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;//Add
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

//    Context context;
    //Setting keys for reading data from SharedPreference
    public static final String CHOICES = "pref_numberOfChoices";//value = 6
    public static final String REGIONS = "pref_regionsToInclude";//value = asia

    public boolean phoneSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        context = this;
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
        Set<String> temp = sp.getStringSet(REGIONS,new HashSet<String>());
        for(String r: temp){
            Log.d("---temp",r.toString());
        }
        //For tablet
        int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        if(screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE)
            phoneSize = false;

        if(phoneSize){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        //Listnener that when you changed the preferences and will reset quiz
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Create a quizfragment instance
        MainActivityFragment quizFragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.quizFragment);
        //For updating the guessButtons
        quizFragment.upDateGuessRows(PreferenceManager.getDefaultSharedPreferences(this));
        //For updating the regions
        quizFragment.upDateRegions(PreferenceManager.getDefaultSharedPreferences(this));
        //Start the quiz
        quizFragment.startQuiz();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    public SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        //sharedPreferences has all the values of prefernces
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            MainActivityFragment quizFragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.quizFragment);
            //Check which preference is changed
            if(key.equals(CHOICES)){
                quizFragment.upDateGuessRows(sharedPreferences);
                quizFragment.startQuiz();
            }else if(key.equals(REGIONS)){
                quizFragment.upDateRegions(sharedPreferences);
                quizFragment.startQuiz();
            }
            Toast.makeText(getApplicationContext(),R.string.reset_quiz,Toast.LENGTH_SHORT).show();
        }
    };
}
