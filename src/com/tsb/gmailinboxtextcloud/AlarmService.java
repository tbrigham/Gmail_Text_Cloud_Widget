package com.tsb.gmailinboxtextcloud;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by IntelliJ IDEA.
 * User: Tyler
 * Date: 4/16/11
 * Time: 6:34 PM
 */
public class AlarmService extends Service {

//    private AlarmManager am;
//    private PendingIntent pendingIntent;
//
////    public static String INCREMENT_COUNT_UPDATE = "INCREMENT_COUNT_UPDATE";
////    public static String RESET_COUNT_UPDATE = "RESET_COUNT_UPDATE";
////    public static String PAUSE_COUNT_UPDATE = "PAUSE_COUNT_UPDATE";
//
//
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    public void onReceive(Context context, Intent intent) {

    }
}
