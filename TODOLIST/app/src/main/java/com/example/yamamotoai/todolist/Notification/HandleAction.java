package com.example.yamamotoai.todolist.Notification;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.yamamotoai.todolist.AddEditFragment;

/**
 * Created by yamamotoai on 2017-09-07.
 */

public class HandleAction {

    public static String ACTION_DONE = "DONETHIS";

    public static void executeTask(Context context, String action, int todoId){

        if(ACTION_DONE.equals(action)){

            NotificationUtil.notificationActionDoneThisTodo(context, todoId);

        }
    }

}
