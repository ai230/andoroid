package com.example.yamamotoai.todolist.Notification;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by yamamotoai on 2017-09-07.
 */

public class HandleActionIntent extends IntentService{

    int todoId;
    public HandleActionIntent() {
        super("HandleActionIntent");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        todoId = intent.getIntExtra("requestCode", todoId);
        String action = intent.getAction();
        HandleAction.executeTask(this, action, todoId);

    }
}
