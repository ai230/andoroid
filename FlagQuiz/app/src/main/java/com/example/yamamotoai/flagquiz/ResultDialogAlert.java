package com.example.yamamotoai.flagquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by yamamotoai on 2017-08-03.
 */

public class ResultDialogAlert extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setMessage("RESULT IS " + MainActivityFragment.guessAns+
                 "\n" + "DO YOU WANT TO RESTART?")
                .setPositiveButton("RESTART", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Activity activity = getActivity();
                Toast.makeText(getActivity().getApplicationContext(),"RESTART QUIZ!! ",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // completely cleared the entire stack and made the new activity the only one in the app
                activity.finish();
                startActivity(intent);
            }

        }).setNegativeButton("QUIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity().getApplicationContext(),"FIN!! ",Toast.LENGTH_SHORT).show();
            }
        });
        return alertDialog.create();
    }
}
