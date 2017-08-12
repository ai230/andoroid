package com.example.yamamotoai.broadcastreceiverexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by yamamotoai on 2017-08-11.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("com.example.yamamotoai.broadcastreceiverexample.SOME_ACTION"))
            Toast.makeText(context,"SOME_ACTION is received!",Toast.LENGTH_SHORT).show();
        else
        {
            //ConnectivityManager is a class that answers queries about the state of network connectivity.
            //It also notifies applications when network connectivity changes.
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if(isConnected){
                Toast.makeText(context,"NETWORK IS CONNECTED",
                        Toast.LENGTH_SHORT).show();
            }else {

                Toast.makeText(context,"NETWORK IS CHANGED or DISCONNCTED",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
