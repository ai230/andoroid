package com.example.yamamotoai.todolist.alert;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.yamamotoai.todolist.R;

/**
 * Created by yamamotoai on 2017-09-23.
 */

public class DeleteDialogFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("DELETE")
                .setMessage("Do you want to delete permanently?")
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

    public interface DeleteDialogInterface{
        void onPositiveDeleteBtnClicked();
    }

    private void positiveBtnClicked(){
        DeleteDialogInterface dialogInterface = (DeleteDialogInterface) getTargetFragment();
        dialogInterface.onPositiveDeleteBtnClicked();
    }
}
