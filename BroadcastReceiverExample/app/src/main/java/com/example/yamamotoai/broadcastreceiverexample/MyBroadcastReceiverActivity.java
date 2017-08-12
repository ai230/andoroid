package com.example.yamamotoai.broadcastreceiverexample;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by yamamotoai on 2017-08-11.
 */

public class MyBroadcastReceiverActivity extends AppCompatActivity implements View.OnClickListener {

    MyBroadcastReceiver myBroadcastReceiver;
    MyBroadcastReceiver2 myBroadcastReceiver2;
    IntentFilter intentFilter;
    IntentFilter intentFilter2;
    Button buttonBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast);

        myBroadcastReceiver = new MyBroadcastReceiver();
        intentFilter = new IntentFilter("com.example.yamamotoai.broadcastreceiverexample.SOME_ACTION");
        buttonBroadcast = (Button) findViewById(R.id.button);
        buttonBroadcast.setOnClickListener(this);

        myBroadcastReceiver2 = new MyBroadcastReceiver2();
        intentFilter2 = new IntentFilter("ACTION_LOCALE_CHANGED");

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myBroadcastReceiver, intentFilter);
        registerReceiver(myBroadcastReceiver2, intentFilter2);
    }

    @Override
    public void onClick(View view) {
        Log.d("---","clicked");
        Intent i = new Intent("com.example.yamamotoai.broadcastreceiverexample.SOME_ACTION");
        sendBroadcast(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadcastReceiver);
        unregisterReceiver(myBroadcastReceiver2);
    }
}
