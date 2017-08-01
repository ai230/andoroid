package com.example.yamamotoai.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yamamotoai on 2017-07-30.
 */

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new PreferencesFragment())
                .commit();
    }
}
