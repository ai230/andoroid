package com.example.yamamotoai.todolist.Notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.example.yamamotoai.todolist.MainActivity;
import com.example.yamamotoai.todolist.R;

/**
 * Created by yamamotoai on 2017-09-02.
 */

//When you add or update todolist notification is set before 1 day.
public class NotificationReceiver extends BroadcastReceiver {

    private static final int ACTION_REQUEST_CODE1 = 14;
    private static final int ACTION_REQUEST_CODE2 = 15;


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

        //create pendingIntent for Notification
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                reqCode, new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_list)
                .setLargeIcon(getBitmapIcon(context, R.drawable.launcher_icon))
                .setContentTitle(msgTitle)
                .setContentText(msgText)
                .addAction(ignoreReminderAction(context))
                .addAction(DoneThisTodo(context));

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

    private NotificationCompat.Action DoneThisTodo(Context context){

        Intent intent1 = new Intent(context, HandleActionIntent.class);
        intent1.setAction(HandleAction.ACTION_DONE);

        //create pendingIntent for Action
        PendingIntent pendingIntent = PendingIntent.getService(
                context,
                ACTION_REQUEST_CODE1,
                intent1,
                PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Action action = new NotificationCompat.Action(
                R.drawable.ic_delete_black,
                "DONE THIS",
                pendingIntent);

        return action;
    }

    private NotificationCompat.Action ignoreReminderAction(Context context){

        Intent intent2 = new Intent(context, HandleActionIntent.class);
        intent2.setAction(HandleAction.ACTION_CHANGE);

        //create pendingIntent for Action
        PendingIntent pendingIntent = PendingIntent.getService(
                context,
                ACTION_REQUEST_CODE2,
                intent2,
                PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Action action = new NotificationCompat.Action(
                R.drawable.ic_event_note,
                "CHANGE DATE",
                pendingIntent);

        return action;
    }

    public static Bitmap getBitmapIcon(Context context, int icon){
        Resources res = context.getResources();
        Bitmap bitmapIcon = BitmapFactory.decodeResource(res, icon);
        return bitmapIcon;
    }
}