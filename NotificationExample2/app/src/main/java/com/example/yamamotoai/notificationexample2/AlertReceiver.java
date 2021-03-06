package com.example.yamamotoai.notificationexample2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by yamamotoai on 2017-09-01.
 */

public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        createNotification(context, "Times up", "5 seconds has passd", "Alert");
    }

    public void createNotification(Context context, String msg, String msgText, String msgAlert){

        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0, new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_face)
                .setContentTitle(msg)
                .setTicker(msgAlert)
                .setContentText(msgText);

        mBuilder.setContentIntent(pendingIntent);
        //How the person is notify
        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        //cancel notification, it will stop automatically
        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, mBuilder.build());


    }
}
