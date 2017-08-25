package com.example.yamamotoai.notificationexample;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by yamamotoai on 2017-08-25.
 */

public class HandleIntent extends IntentService{


    public HandleIntent() {
        super("HandleIntent");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        HandleAction.executeTask(this, action);
    }
}
