package com.example.yamamotoai.preferencesexample2;

import android.content.SharedPreferences;
import android.hardware.camera2.params.Face;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by yamamotoai on 2017-07-28.
 */

public class PreferencesFragment extends PreferenceFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    String name1 = sp.getString("key",null);
    Boolean name2 = sp.getBoolean("key", false);

}
