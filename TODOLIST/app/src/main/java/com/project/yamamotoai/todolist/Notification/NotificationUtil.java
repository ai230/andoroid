package com.project.yamamotoai.todolist.Notification;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.project.yamamotoai.todolist.MainActivity;
import com.project.yamamotoai.todolist.data.DatabaseHandler;

import java.util.Calendar;

/**
 * Created by yamamotoai on 2017-09-09.
 */

public class NotificationUtil {

    public static String REQUEST_CODE = "requestCode";
    public static String REQUEST_TODO_DATE = "todoDate";
    public static String REQUEST_TODO_TITLE = "todoTitle";

    public static void notificationActionDoneThisTodo(Context context, int todoId){

        //If delete
//        String[] ids = new String[1];
//        ids[0] = String.valueOf(todoId);

        //Update database isDone = true;
        DatabaseHandler db = new DatabaseHandler(context);
        db.updateDatabaseDoneToTrue(todoId);
        //If delete
//        db.deleteFromDatabase(ids);

        cancelNotification(context, todoId);

    }


    public static void setNotification(Context context, int days, int reqCode, String todoDate, String todoTitle) {

        int sec = (days - MainActivity.NOTIFICATION_DAYS) * 86400;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, sec);
        Long alertTime = calendar.getTimeInMillis();
        Intent alarmIntent = new Intent(context, NotificationReceiver.class);
        alarmIntent.putExtra(REQUEST_CODE, String.valueOf(reqCode));
        alarmIntent.putExtra(REQUEST_TODO_DATE, todoDate);
        alarmIntent.putExtra(REQUEST_TODO_TITLE, todoTitle);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context , reqCode, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        //for the schedule
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime, pendingIntent);

    }

    public static void cancelNotification(Context context, int reqCode){
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(reqCode);
    }

}
