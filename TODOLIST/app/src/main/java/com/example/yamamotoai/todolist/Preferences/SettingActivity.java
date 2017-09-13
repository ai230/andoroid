package com.example.yamamotoai.todolist.Preferences;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.example.yamamotoai.todolist.Main.MainActivity;
import com.example.yamamotoai.todolist.R;


/**
 * Created by yamamotoai on 2017-09-11.
 */

public class SettingActivity extends AppCompatActivity {

    boolean fromAddEditFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        fromAddEditFragment = intent.getBooleanExtra("AccessFromAddEditFragment",false);
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingActivityFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(fromAddEditFragment){
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            sharedPref = getSharedPreferences(MainActivity.PREF_KEY_DAY, Context.MODE_PRIVATE);
            MainActivity.NOTIFICATION_DAYS = sharedPref.getInt(MainActivity.PREF_KEY_DAY, 1);
        }
    }
}