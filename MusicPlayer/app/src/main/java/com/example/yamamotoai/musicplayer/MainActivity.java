package com.example.yamamotoai.musicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Create an object for Media Player
    MediaPlayer player;
    ImageButton playButton, stopButton, resetButton;
    boolean play_reset = true;
    private SeekBar seekbar;

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

        //Instantiate an object of media player
        player = MediaPlayer.create(this,  R.raw.music1);

        playButton = (ImageButton) this.findViewById(R.id.imagebutton_play);
        stopButton = (ImageButton) this.findViewById(R.id.imagebutton_stop);
        resetButton = (ImageButton) this.findViewById(R.id.imagebutton_reset);
        seekbar = (SeekBar)findViewById(R.id.seekBar);

        playButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);

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
                // If the user presses the play/pause button
                // if no player instance is available, then create a media player first
                if (play_reset) {
                    play_reset = false;
                    player.setLooping(false); // Set looping
                }
                playPause();

                break;
            case R.id.imagebutton_stop:
                if (!play_reset) {
                    // If the user presses the stop button
                    player.stop();
                    // change the image of the imagebutton_paused button to imagebutton_play
                    playButton.setImageResource(R.drawable.play);
                    Toast.makeText(this, R.string.stopped, Toast.LENGTH_SHORT).show();
                    try {
                        player.prepare();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.imagebutton_reset:
                if (!play_reset) {
                    // If the user presses the reset button
                    player.reset();
                    // change the image of the imagebutton_play button to imagebutton_pause
                    playButton.setImageResource(R.drawable.play);
                    Toast.makeText(this, R.string.reset, Toast.LENGTH_SHORT).show();
                    // Release media instance to system
                    player.release();
                    play_reset = true;
                    break;
                }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.reset();
        // change the image of the play button to play
        playButton.setImageResource(R.drawable.play);
        Toast.makeText(this, R.string.reset, Toast.LENGTH_SHORT).show();
        // Release media instance to system
        player.release();
        play_reset = true;
    }

        // Toggle between the buttonplay and pause
    private void playPause() {
        // if the music is playing then pause the music playback
        if(player.isPlaying()) {
            player.pause();
            // change the image of the play button to play
            playButton.setImageResource(R.drawable.play);
            Toast.makeText(this, R.string.paused, Toast.LENGTH_SHORT).show();
        }
            // Music is paused, start, or resume playback
        else {
            // change the image of the play button to pause
            playButton.setImageResource(R.drawable.pause);
            player.start();
            Toast.makeText(this, R.string.isPlaying, Toast.LENGTH_SHORT).show();
        }
    }

}
