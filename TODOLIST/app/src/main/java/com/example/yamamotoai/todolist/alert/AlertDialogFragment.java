package com.example.yamamotoai.todolist.alert;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.yamamotoai.todolist.R;

/**
 * Created by yamamotoai on 2017-08-05.
 */

public class AlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Alert")
                .setMessage("Please enter Title!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setIcon(R.drawable.launcher_icon)
                .create();
        return alertDialog;
    }

}
