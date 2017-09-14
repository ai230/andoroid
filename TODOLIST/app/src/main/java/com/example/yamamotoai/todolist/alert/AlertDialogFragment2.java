package com.example.yamamotoai.todolist.alert;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import com.example.yamamotoai.todolist.R;

/**
 * Created by yamamotoai on 2017-08-07.
 */

public class AlertDialogFragment2 extends DialogFragment{
//when you pressed save button and the new group you entered is blank, it is called
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Group is blank")
                .setMessage("Please enter group.")
                .setPositiveButton("OK", null)
                .setIcon(R.drawable.launcher_icon)
                .create();
        return alertDialog;
    }

}
