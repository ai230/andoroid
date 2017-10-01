package com.project.ai.todolist.Preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.project.ai.todolist.MainActivity;
import com.project.ai.todolist.R;
import com.project.ai.todolist.alert.NumberPickerDialogFragment;

/**
 * Created by yamamotoai on 2017-09-11.
 */

public class SettingActivityFragment extends PreferenceFragment implements NumberPickerDialogFragment.NumberPickerInterface{

    private Preference prefReminder, prefDay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        PreferenceManager.getDefaultSharedPreferences(getActivity()).registerOnSharedPreferenceChangeListener(preferenceChangeListener);
        toggleEnabled(MainActivity.NOTIFICATION_REMINDER);

        prefDay = findPreference(MainActivity.PREF_KEY_DAY);
        if(MainActivity.NOTIFICATION_DAYS == 1)
            prefDay.setSummary(getString(R.string.pref_days_summary1,MainActivity.NOTIFICATION_DAYS));
        else
            prefDay.setSummary(getString(R.string.pref_days_summary2,MainActivity.NOTIFICATION_DAYS));
            prefDay.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                NumberPickerDialogFragment numberPickerDialog = new NumberPickerDialogFragment();
                numberPickerDialog.setTargetFragment(SettingActivityFragment.this,0);
                numberPickerDialog.show(getFragmentManager(), "dialog");

                return true;
            }
        });


    }

    public SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    if(key.equals(MainActivity.PREF_KEY_REMINDER)){
                        MainActivity.NOTIFICATION_REMINDER = sharedPreferences.getBoolean(MainActivity.PREF_KEY_REMINDER, true);
                        toggleEnabled(MainActivity.NOTIFICATION_REMINDER);
                    }else if(key.equals(MainActivity.PREF_KEY_DAY)){
                        int i = sharedPreferences.getInt(MainActivity.PREF_KEY_DAY, 1);
                    }

                }
            };

    public void toggleEnabled(boolean boo) {
        getPreferenceScreen().findPreference(MainActivity.PREF_KEY_DAY).setEnabled(boo);
        prefReminder = findPreference(MainActivity.PREF_KEY_REMINDER);
        if(MainActivity.NOTIFICATION_REMINDER) {
            prefReminder.setSummary(R.string.pref_reminder_summary_on);
        }else {
            prefReminder.setSummary(R.string.pref_reminder_summary_off);
        }
    }


    @Override
    public void onReturnPickedValue(int value) {
        //Set Summary
        if(MainActivity.NOTIFICATION_DAYS == 1)
            prefDay.setSummary(getString(R.string.pref_days_summary1, value));
        else
            prefDay.setSummary(getString(R.string.pref_days_summary2, value));
    }
}
