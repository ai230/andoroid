package com.project.ai.todolist.alert;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.project.ai.todolist.R;

/**
 * Created by yamamotoai on 2017-09-22.
 */

public class DoneDialogFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Done?")
                .setMessage("Do you want to save and close this page?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        positiveBtnClicked();
                    }
                })
                .setNegativeButton("NO", null)
                .setIcon(R.drawable.launcher_icon)
                .create();
        return alertDialog;
    }

    public interface DoneDialogInterface{
        void onPositiveBtnClicked();
    }

    private void positiveBtnClicked(){
        DoneDialogInterface dialogInterface = (DoneDialogInterface) getTargetFragment();
        dialogInterface.onPositiveBtnClicked();
    }
}
