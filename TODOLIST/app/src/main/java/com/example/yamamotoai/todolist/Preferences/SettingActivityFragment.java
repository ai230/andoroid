package com.example.yamamotoai.todolist.Preferences;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.example.yamamotoai.todolist.Main.MainActivity;
import com.example.yamamotoai.todolist.R;
/**
 * Created by yamamotoai on 2017-09-11.
 */

public class SettingActivityFragment extends PreferenceFragment {

    NumberPicker picker;
    private Preference prefReminder, prefDay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        PreferenceManager.getDefaultSharedPreferences(getActivity()).registerOnSharedPreferenceChangeListener(preferenceChangeListener);
        toggleEnabled(MainActivity.NOTIFICATION_REMINDER);

        prefDay = findPreference(MainActivity.PREF_KEY_DAY);
        prefDay.setSummary(getString(R.string.pref_days_summary,MainActivity.NOTIFICATION_DAYS));
        prefDay.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                class MyDialog extends DialogFragment{

                    @Override
                    public Dialog onCreateDialog(Bundle savedInstanceState) {

                        int maxValue = getContext().getResources().getInteger(R.integer.number_picker_max_value);
                        int minValue = getContext().getResources().getInteger(R.integer.number_picker_min_value);

                        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view = layoutInflater.inflate(R.layout.pref_number_dialog, null);

                        picker = (NumberPicker) view.findViewById(R.id.numberPicker);
                        picker.setMaxValue(maxValue);
                        picker.setMinValue(minValue);
                        picker.setValue(MainActivity.NOTIFICATION_DAYS);

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle(R.string.picker_title)
                                .setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        int pickedValue = picker.getValue();
                                        //Set picker value into SharedPreference
                                        SharedPreferences prefs = getActivity().getSharedPreferences(MainActivity.PREF_KEY_DAY, Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.putInt(MainActivity.PREF_KEY_DAY, pickedValue);
                                        editor.commit();

//                                        SharedPreferences sharedPref = getActivity().getSharedPreferences(MainActivity.PREF_KEY_DAY, Context.MODE_PRIVATE);
//                                        int d = sharedPref.getInt(MainActivity.PREF_KEY_DAY, 1);

                                        //Set Summary
                                        prefDay.setSummary(getString(R.string.pref_days_summary, pickedValue));

                                        MainActivity.NOTIFICATION_DAYS = pickedValue;
                                    }
                                })
                                .setNegativeButton(R.string.cancel_button, null)
                                .setView(view);

                        return builder.create();
                    }
                }

                new MyDialog().show(getFragmentManager(), "dialog");

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


}
