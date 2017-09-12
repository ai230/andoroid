package com.example.yamamotoai.todolist.Preferences;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;

import com.example.yamamotoai.todolist.Main.MainActivity;
import com.example.yamamotoai.todolist.R;


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

}
