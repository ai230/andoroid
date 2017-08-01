package com.example.yamamotoai.flagquiz;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by yamamotoai on 2017-07-31.
 */

public class SettingsActivityFragment extends PreferenceFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
