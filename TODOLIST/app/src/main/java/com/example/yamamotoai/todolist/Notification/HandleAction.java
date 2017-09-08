package com.example.yamamotoai.todolist.Notification;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by yamamotoai on 2017-09-07.
 */

public class HandleAction {

    public static String ACTION_DONE = "DONETHIS";
    public static String ACTION_CHANGE = "CHANGEDATE";

    public static void executeTask(Context context, String action){

        if(ACTION_DONE.equals(action)){
            Toast.makeText(context, "clicked 1", Toast.LENGTH_SHORT).show();
        }else if(ACTION_CHANGE.equals(action)){
            Toast.makeText(context, "clicked 2", Toast.LENGTH_SHORT).show();
        }
    }

}
