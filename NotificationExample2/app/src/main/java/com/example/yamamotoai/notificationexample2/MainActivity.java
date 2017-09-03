package com.example.yamamotoai.notificationexample2;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.GregorianCalendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button startNotificatinBut, stopNotificationBut, alertButton;
    private NotificationManager notificationManager;
    private int notifID = 33;
    private boolean isNotifyActive = false;

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

        startNotificatinBut = (Button) findViewById(R.id.start_btn);
        stopNotificationBut = (Button) findViewById(R.id.stop_btn);
        alertButton = (Button) findViewById(R.id.alerm_btn);


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

    public void startNotification(View view) {

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(MainActivity.this)
                        .setContentTitle("Message")
                        .setContentText("New Message")
                        .setTicker("Alert New Message")
                        .setSmallIcon(R.drawable.ic_face);

        Intent moreInfoIntent = new Intent(MainActivity.this, MoreInfoNotification.class);

        //When you clicked back button on your device it goes proper place
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(MainActivity.this);
        taskStackBuilder.addParentStack(MoreInfoNotification.class);
        taskStackBuilder.addNextIntent(moreInfoIntent);

        //if intent ixist we want to update not create new one
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        notificationBuilder.setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifID, notificationBuilder.build());
        isNotifyActive = true;

    }

    public void stopNotification(View view) {

        if(isNotifyActive)
            notificationManager.cancel(notifID);

    }

    public void setAlarm(View view) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 5);
        Long alertTime = calendar.getTimeInMillis();
        Intent alertIntent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, 1, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //for the schedule
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime, pendingIntent);


    }

}
