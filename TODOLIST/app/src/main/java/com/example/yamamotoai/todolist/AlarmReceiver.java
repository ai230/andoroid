package com.example.yamamotoai.todolist;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

/**
 * Created by yamamotoai on 2017-09-02.
 */

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        String todoTitle = intent.getStringExtra("todoTitle");
        String todoDate = intent.getStringExtra("todoDate");
        String msgText = todoDate + " " + todoTitle;
        String msgTitle = "You have something to do!";
        String msgTicker = "TODOLIST";

        createNotification(context, msgTitle, msgText, msgTicker);
    }

    public void createNotification(Context context, String msg, String msgText, String msgAlert){

        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0, new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_list)
                .setLargeIcon(getBitmapIcon(context, R.drawable.launcher_icon))
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

    public static Bitmap getBitmapIcon(Context context, int icon){

        Resources res = context.getResources();
        Bitmap bitmapIcon = BitmapFactory.decodeResource(res, icon);
        return bitmapIcon;

    }
}
