package com.example.yamamotoai.todolist.Notification;

import android.app.AlarmManager;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.yamamotoai.todolist.AddEditFragment;
import com.example.yamamotoai.todolist.data.DatabaseHandler;

import java.util.Calendar;

/**
 * Created by yamamotoai on 2017-09-09.
 */

public class NotificationUtil {


    public static void notificationActionDoneThisTodo(Context context, int todoId){

        //Delete the TODO
        String[] ids = new String[1];
        ids[0] = String.valueOf(todoId);
        DatabaseHandler db = new DatabaseHandler(context);
        db.deleteFromDatabase(ids);

        cancelNotification(context, todoId);

    }


    public static void setNotification(Context context, int days, int reqCode, String todoDate, String todoTitle) {

        int sec = (days - 1) * 86400;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, sec);
        Long alertTime = calendar.getTimeInMillis();
        Intent alarmIntent = new Intent(context, NotificationReceiver.class);
        alarmIntent.putExtra("reqCode", String.valueOf(reqCode));
        alarmIntent.putExtra("todoDate", todoDate);
        alarmIntent.putExtra("todoTitle", todoTitle);
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
