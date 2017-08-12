package com.example.yamamotoai.broadcastreceiverexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by yamamotoai on 2017-08-11.
 */

public class MyBroadcastReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_LOCALE_CHANGED)) {
            // update emergency string whenever locale changed
            Log.d("---","ACTION_LOCALE_CHANGED");
            Toast.makeText(context,"ACTION_LOCALE_CHANGED",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
