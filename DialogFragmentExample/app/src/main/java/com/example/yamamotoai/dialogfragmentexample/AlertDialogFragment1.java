package com.example.yamamotoai.dialogfragmentexample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by yamamotoai on 2017-07-26.
 */

public class AlertDialogFragment1 extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
            .setTitle("Titel..")
            .setMessage("Message..")
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            })
            .create();
    return alertDialog;
    }


}
