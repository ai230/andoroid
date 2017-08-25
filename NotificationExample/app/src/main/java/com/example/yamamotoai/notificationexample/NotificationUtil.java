package com.example.yamamotoai.notificationexample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.app.Notification;

/**
 * Created by yamamotoai on 2017-08-25.
 */

public class NotificationUtil {

    //Notification ID used to access our notification
    //after we displayed it, this can be handy when we need to cancel the notification or update
    //number is aarbitary and can be set to anything

    private static final int REMINDER_NOTIFICATION_ID = 1138;
    private static final int REMINDER_PENDING_INTENT_ID = 3417;

    private static final int ACTION_DRINK_PENDER_INTENT = 1;
    private static final int ACTION_IGNORE_INTENT = 14;
    //helper methods called contentIntent with a single parameter content.
    //It should return a pending intent . this method will create a pending intent which triger
    //when notification is passed. this pending intent should open up the mainactivity
    //we need to start activity from the notification

    private static PendingIntent contentIntent (Context context){
        //create an intent that opens MainActivity
        Intent startActivityIntent = new Intent(context, Notification.class);
        //Create a pending intent using getactivity that will take the context passed as parameter
        //takes a ID for pending intent
        //takes intent created to open MainActivity
        //wnen the notification is trigerd has the


        return PendingIntent.getActivity(
                context,
                REMINDER_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_CANCEL_CURRENT
        );
    }

    //create a hellper method called largeIcon which takes in a Context as a parameter and
    //returns a Bitmap. Yhis method is nesessary to decode a bitmap needed for the notification
    public static Bitmap largeIcon(Context context){
        //get a Resource object from the context
        Resources res = context.getResources();
        //create and return a bitmap using BitmapFactory.decodeResource
        //passing in the resources object and R.drawable.ic_local_drink_black_24px
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.mipmap.ic_drink);
        return largeIcon;
    }

    public static void reminderUser(Context context){

        //NotificationCompact.Builder to create it
        // has color of R.colorPrimary
        // has small icon
        // has title (a string resource)
        // has content (summary again fom string resource)
        // has style e.g. bigText(), template
        // has defaults i.e. vibrate
        // uses the contentIntent
        // automatically cancel the notification when
        // user click it

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                        .setSmallIcon(R.mipmap.ic_drink)
                        .setLargeIcon(largeIcon(context))
                        .setContentTitle(context.getString(R.string.title))
                        .setContentText(context.getString(R.string.content))
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(
                                context.getString(R.string.content)))
                        .setDefaults(Notification.DEFAULT_VIBRATE)
                        .setContentIntent(contentIntent(context))
                        .addAction(drinkWaterAction(context))
                        .addAction(ignoreReminderAction(context))
                        .setAutoCancel(true);


        //If build version is greater than JELLY BEAN
        //than set priority

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        }

        //get NotificationManager ,using
        //context.getSystemService(Context.NOTIFICATION_SERVICE)
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        //Trigger the notification by calling notify
        //on the NotificationManager
        //pass the ID value and build()
        notificationManager.notify(REMINDER_NOTIFICATION_ID,notificationBuilder.build());
    }

    //add a static method to drink
    private static NotificationCompat.Action drinkWaterAction(Context context){

        //create a intent to lanch the reminder
        Intent drinkWaterIntent = new Intent(context, HandleIntent.class);
        //set action of intent
        drinkWaterIntent.setAction(HandleAction.ACTION_DRINK_WATER);
        // create a pending intent to lanch the intentService
        //PendingIntent is backgroud service
        PendingIntent drinkWaterPendingIntent = PendingIntent.getService(
                context,
                ACTION_DRINK_PENDER_INTENT,
                drinkWaterIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        //create an action for user to notify
        NotificationCompat.Action drinkWaterAction =
                new NotificationCompat.Action(R.mipmap.ic_drink,
                        "DRINK IT", drinkWaterPendingIntent);

        // return the action
        return drinkWaterAction;
    }

    private static NotificationCompat.Action ignoreReminderAction(Context context){
        //create a intent to lanch the reminder
        Intent ignoreDrinkWaterIntent = new Intent(context, HandleIntent.class);
        //set action of intent
        ignoreDrinkWaterIntent.setAction(HandleAction.ACTION_DRINK_WATER);
        // create a pending intent to lanch the intentService
        //PendingIntent is backgroud service
        PendingIntent drinkWaterPendingIntent = PendingIntent.getService(
                context,
                ACTION_IGNORE_INTENT,
                ignoreDrinkWaterIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        //create an action for user to notify
        NotificationCompat.Action ignoreDrinkWaterAction =
                new NotificationCompat.Action(R.mipmap.ic_drink,
                        "DISMISS", drinkWaterPendingIntent);

        // return the action
        return ignoreDrinkWaterAction;
    }

    public static void clearAllNotification(Context context){
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }




}
