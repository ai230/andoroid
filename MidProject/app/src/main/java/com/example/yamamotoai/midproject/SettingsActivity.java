package com.example.yamamotoai.midproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yamamotoai on 2017-08-01.
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
