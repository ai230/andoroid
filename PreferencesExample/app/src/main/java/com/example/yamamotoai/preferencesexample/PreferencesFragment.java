package com.example.yamamotoai.preferencesexample;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by yamamotoai on 2017-07-30.
 */

public class PreferencesFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
