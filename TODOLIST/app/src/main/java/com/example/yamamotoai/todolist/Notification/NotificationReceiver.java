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

    private static final int ACTION_REQUEST_CODE = 14;


    int reqCode;
    @Override
    public void onReceive(Context context, Intent intent) {

        reqCode = Integer.parseInt(intent.getStringExtra(NotificationUtil.REQUEST_CODE));
        String todoTitle = intent.getStringExtra(NotificationUtil.REQUEST_TODO_TITLE);
        String todoDate = intent.getStringExtra(NotificationUtil.REQUEST_TODO_DATE);
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
        intent1.putExtra(NotificationUtil.REQUEST_CODE,reqCode);

        //create pendingIntent for Action
        PendingIntent pendingIntent = PendingIntent.getService(
                context,
                ACTION_REQUEST_CODE,
                intent1,
                PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Action action = new NotificationCompat.Action(
                R.drawable.ic_delete_black,
                "DONE THIS",
                pendingIntent);

        return action;
    }

    public static Bitmap getBitmapIcon(Context context, int icon){
        Resources res = context.getResources();
        Bitmap bitmapIcon = BitmapFactory.decodeResource(res, icon);
        return bitmapIcon;
    }
}
