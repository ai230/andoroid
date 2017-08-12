package com.example.yamamotoai.servicesexample;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by yamamotoai on 2017-08-08.
 */

public class MyServices extends Service{

    MediaPlayer myPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myPlayer = MediaPlayer.create(this,R.raw.music1);
        Toast.makeText(this,"onCreated", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this,"Service Started", Toast.LENGTH_SHORT).show();
        myPlayer.start();
//        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
        myPlayer.stop();
    }
}
