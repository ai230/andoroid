package com.example.yamamotoai.servicesexample;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
long seconds;
    private TextView mTextMessage;
    private Button startBtn, stopBtn, nextBtn, intentBtn;
    private EditText editTextSeconds;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        startBtn = (Button)findViewById(R.id.button_start);
        stopBtn = (Button)findViewById(R.id.button_stop);
//        nextBtn = (Button)findViewById(R.id.button_next);
        intentBtn = (Button)findViewById(R.id.button_intent);
        editTextSeconds = (EditText) findViewById(R.id.editText_seconds);

        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
//        nextBtn.setOnClickListener(this);
        intentBtn.setOnClickListener(this);

        MediaPlayer m = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        m.setLooping(true);
        Toast.makeText(this,"onCreated", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_start:
                Intent i = new Intent(this, MyServices.class);
                startService(i);
                break;
            case R.id.button_stop:
                i = new Intent(this, MyServices.class);
                stopService(i);
                break;
//            case R.id.button_next:
//                Intent i3 = new Intent(this, MyServices.class);
//                startService(i3);
//                break;

            case R.id.button_intent:
                seconds =  Long.parseLong(editTextSeconds.getText().toString());
                Intent i4 = new Intent(this, MyIntentServices.class);
                i4.putExtra("seconds",seconds);
                Toast.makeText(this,"Start service!",Toast.LENGTH_SHORT).show();
                startService(i4);
            default:
                break;
        }
    }
}
