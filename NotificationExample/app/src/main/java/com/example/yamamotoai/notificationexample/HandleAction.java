package com.example.yamamotoai.notificationexample;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * Created by yamamotoai on 2017-08-25.
 */

public class HandleAction {

    public static final String ACTION_DRINK_WATER = "drink";
    public static final String ACTION_IGNORE = "dismiss";

    public static void executeTask(Context context, String action){
        if(ACTION_DRINK_WATER.equals(action)){

            NotificationUtil.clearAllNotification(context);

        }else if(ACTION_IGNORE.equals(action)){

            NotificationUtil.clearAllNotification(context);

        }
    }

}
