package com.example.yamamotoai.musicplayer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by yamamotoai on 2017-08-12.
 */

public class StopMain extends Activity implements View.OnClickListener{

    final int MSG_START_TIMER = 0;
    final int MSG_STOP_TIMER = 1;
    final int MSG_UPDATE_TIMER = 2;

    StopWatch timer = new StopWatch();
    final int REFRESH_RATE = 100;

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
                    tvTextView.setText(""+ timer.getElapsedTimeMin() + ":" + timer.getElapsedTimeSecs());
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIMER,REFRESH_RATE); //text view is updated every second,
                    break;                                  //though the timer is still running
                case MSG_STOP_TIMER:
                    mHandler.removeMessages(MSG_UPDATE_TIMER); // no more updates.
                    timer.stop();//stop timer
                    tvTextView.setText(""+ timer.getElapsedTimeMin() + ":" + timer.getElapsedTimeSecs());
                    break;

                default:
                    break;
            }
        }
    };

    TextView tvTextView;
    ImageButton btnStart,btnStop;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTextView = (TextView)findViewById(R.id.textview_seekbar);

        btnStart = (ImageButton)findViewById(R.id.imagebutton_play);
        btnStop= (ImageButton)findViewById(R.id.imagebutton_stop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);

    }

    public void onClick(View v) {
        if(btnStart == v)
        {
            mHandler.sendEmptyMessage(MSG_START_TIMER);
        }else
        if(btnStop == v){
            mHandler.sendEmptyMessage(MSG_STOP_TIMER);
        }
    }
}
