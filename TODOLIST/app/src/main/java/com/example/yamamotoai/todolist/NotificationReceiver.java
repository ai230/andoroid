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

//When you add or update todolist notification is set before 1 day.
public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        int reqCode = Integer.parseInt(intent.getStringExtra("reqCode"));
        String todoTitle = intent.getStringExtra("todoTitle");
        String todoDate = intent.getStringExtra("todoDate");
        String msgText = todoDate + " " + todoTitle;
        String msgTitle = "MyTodolist";

        createNotification(context, msgTitle, msgText, reqCode);
    }

    public void createNotification(Context context, String msgTitle, String msgText, int reqCode){

        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                reqCode, new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_list)
                .setLargeIcon(getBitmapIcon(context, R.drawable.launcher_icon))
                .setContentTitle(msgTitle)
                .setContentText(msgText);

        mBuilder.setContentIntent(pendingIntent);
        //How the person is notify
        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        //Cancel notification, it will stop automatically
        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //notification is set one by one as different id(reqCode)
        mNotificationManager.notify(reqCode, mBuilder.build());


    }

    public static Bitmap getBitmapIcon(Context context, int icon){
        Resources res = context.getResources();
        Bitmap bitmapIcon = BitmapFactory.decodeResource(res, icon);
        return bitmapIcon;
    }
}
