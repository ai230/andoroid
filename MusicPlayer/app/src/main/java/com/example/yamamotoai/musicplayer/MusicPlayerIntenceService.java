package com.example.yamamotoai.musicplayer;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by yamamotoai on 2017-08-12.
 */

public class MusicPlayerIntenceService extends IntentService {

    MediaPlayer mediaPlayer;
    int mDuration;
    int mCurrentPosition;

    public MusicPlayerIntenceService() {
        super("MusicPlayerIntenceService");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("---","onCreate()");
        mediaPlayer = MediaPlayer.create(this, R.raw.music1);
//        mediaPlayer.setOnCompletionListener(this);
        mDuration = mediaPlayer.getDuration();
        mCurrentPosition = mediaPlayer.getCurrentPosition();

        Intent intent = new Intent();
        intent.setAction("android.mybroadcast");
        getApplicationContext().sendBroadcast(intent);

//        final Handler ha=new Handler();
//        ha.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                //call function
//                publishResult(getBaseContext(), mediaPlayer.getCurrentPosition());
//                ha.postDelayed(this, 100);
//                Log.d("---","run()");
//            }
//        }, 10000);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("---","onStartCommand()");
        playPause();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("---","onDestroy()");
        if(mediaPlayer.isPlaying())
            mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    private void playPause() {
        publishDuration(getApplicationContext(),mDuration);
        Log.d("---","playPause()");
        // if the music is playing then pause the music playback
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        // Music is paused, start, or resume playback
        else {
            // change the image of the play button to pause
            mediaPlayer.start();
        }
    }

    public static void publishDuration(Context context, int mDuration){
        Intent intent = new Intent("Broadcast");
        intent.putExtra("INTENT_TYPE", "SEEKBAR_RESULT");
        intent.putExtra("DURATION", mDuration);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }


}
