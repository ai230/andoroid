package com.example.yamamotoai.todolist.Notification;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by yamamotoai on 2017-09-07.
 */

public class HandleActionIntent extends IntentService{

    public HandleActionIntent() {
        super("HandleActionIntent");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String action = intent.getAction();
        HandleAction.executeTask(this, action);

    }
}
