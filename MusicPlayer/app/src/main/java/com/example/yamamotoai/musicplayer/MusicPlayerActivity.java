package com.example.yamamotoai.musicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by yamamotoai on 2017-08-12.
 */

public class MusicPlayerActivity extends AppCompatActivity implements View.OnClickListener{

    // Create an object for Media Player
//    MediaPlayer player;
    private ImageButton playButton, stopButton, resetButton;
    private SeekBar seekbar;
    private TextView tv;
    boolean isPaused = false;
    boolean isPlaying = false;
    private Intent intent;

    final int MSG_START_TIMER = 0;
    final int MSG_STOP_TIMER = 1;
    final int MSG_UPDATE_TIMER = 2;

    StopWatch timer = new StopWatch();
    final int REFRESH_RATE = 100;
    int mDuration;
    String mDurationString;

    MediaPlayer player;

    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_START_TIMER:
                    timer.start(); //start timer
                    mHandler.sendEmptyMessage(MSG_UPDATE_TIMER);
                    break;

                case MSG_UPDATE_TIMER:
                    tv.setText(String.format("%02d:%02d", timer.getElapsedTimeMin(), timer.getElapsedTimeSecs()) + "/" + mDurationString);
                    seekbar.setProgress((int)(timer.getElapsedTimeMili())/10);
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIMER,REFRESH_RATE); //text view is updated every second,
                    break;//though the timer is still running
                case MSG_STOP_TIMER:
                    mHandler.removeMessages(MSG_UPDATE_TIMER); // no more updates.
                    timer.stop();//stop timer
                    tv.setText(String.format("%02d:%02d", timer.getElapsedTimeMin(), timer.getElapsedTimeSecs()) + "/" + mDurationString);
                    seekbar.setProgress((int)(timer.getElapsedTimeMili())/mDuration);
                    break;

                default:
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        playButton = (ImageButton) findViewById(R.id.imagebutton_play);
        stopButton = (ImageButton) findViewById(R.id.imagebutton_stop);
        resetButton = (ImageButton) findViewById(R.id.imagebutton_reset);
        seekbar = (SeekBar)findViewById(R.id.seekBar);
        tv = (TextView) findViewById(R.id.textview_seekbar);

        playButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);

        intent = new Intent(this, MusicPlayerIntenceService.class);
        seekbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imagebutton_play:
                mHandler.sendEmptyMessage(MSG_START_TIMER);
                isPlaying = true;
                //if music is playing music will be paused
                if(!isPaused) {
                    isPaused = true;
                    playButton.setImageResource(R.drawable.pause);
                    Toast.makeText(this, R.string.isPlaying, Toast.LENGTH_SHORT).show();
                }
                //if music is paused music will be playing
                else {
                    timer.pause();
                    isPaused = false;
                    playButton.setImageResource(R.drawable.play);
                    Toast.makeText(this, R.string.paused, Toast.LENGTH_SHORT).show();
                }

                startService(intent);
                break;
            case R.id.imagebutton_stop:
                musicStop();

                break;
            case R.id.imagebutton_reset:

                if(!isPaused){
                    stopService(intent);
                    startService(intent);
                }else{
                    stopService(intent);
                    playButton.setImageResource(R.drawable.play);
                }
                Toast.makeText(this, R.string.reset, Toast.LENGTH_SHORT).show();
        }
    }

    public void seekbar(){

        player = MediaPlayer.create(this, R.raw.music1);
        mDuration = player.getDuration()/1000;
        int mDurationMin = (mDuration/60)%60;
        int mDurationSec = mDuration%60;
        seekbar.setMax(mDuration);
        mDurationString = String.format("%02d:%02d", mDurationMin, mDurationSec);
        tv.setText("00:00" + "/" + mDurationString);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                progress_value = progress;
                Toast.makeText(MusicPlayerActivity.this, "onProgressChanged!", Toast.LENGTH_SHORT).show();
                Log.d("---onProgressChanged1", timer.getElapsedTimeMili() / 10 + " " + seekbar.getMax());

                if ((int) timer.getElapsedTimeMili() / 10 >= seekbar.getMax()) {
                    Log.d("---onProgressChanged2", timer.getElapsedTimeMili() / 10 + " " + seekbar.getMax());
                    musicStop();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MusicPlayerActivity.this,"onStartTrackingTouch!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv.setText(String.format("%02d:%02d", timer.getElapsedTimeMin(), timer.getElapsedTimeSecs()) + "/" + mDurationString);
                Toast.makeText(MusicPlayerActivity.this,"onStopTrackingTouch!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void musicStop(){
        if(isPaused) {
            isPaused = false;
            playButton.setImageResource(R.drawable.play);
        }
        mHandler.sendEmptyMessage(MSG_STOP_TIMER);
        Toast.makeText(this, R.string.stopped, Toast.LENGTH_SHORT).show();
        stopService(intent);
    }


}
