package com.project.ai.todolist.alert;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.project.ai.todolist.R;

/**
 * Created by yamamotoai on 2017-08-05.
 */

public class AlertDialogFragment extends DialogFragment {

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
