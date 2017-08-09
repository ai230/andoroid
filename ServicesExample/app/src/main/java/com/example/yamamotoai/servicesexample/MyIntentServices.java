package com.example.yamamotoai.servicesexample;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by yamamotoai on 2017-08-09.
 */

public class MyIntentServices extends IntentService {
    long seconds;
    public MyIntentServices() {
        super("MyIntentServices");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        seconds = intent.getExtras().getLong("seconds");
        long milliSeconds = seconds * 1000;
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"onDestroy!",Toast.LENGTH_SHORT).show();
    }
}
