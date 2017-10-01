package com.project.ai.todolist.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.project.ai.todolist.MainActivity;


/**
 * Created by yamamotoai on 2017-09-11.
 */

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingActivityFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
            SharedPreferences sharedPref = getSharedPreferences(MainActivity.PREF_KEY_DAY, Context.MODE_PRIVATE);
            MainActivity.NOTIFICATION_DAYS = sharedPref.getInt(MainActivity.PREF_KEY_DAY, 1);
    }

}