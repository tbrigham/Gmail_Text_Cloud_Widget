package com.tsb.gmailinboxtextcloud;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by IntelliJ IDEA.
 * User: Tyler
 * Date: 4/16/11
 * Time: 12:15 PM
 */
public class GITCAlarmManager extends Activity {

    private AlarmManager am;

    public static String INCREMENT_COUNT_UPDATE = "INCREMENT_COUNT_UPDATE";
    public static String RESET_COUNT_UPDATE = "RESET_COUNT_UPDATE";
    public static String PAUSE_COUNT_UPDATE = "PAUSE_COUNT_UPDATE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this.getApplicationContext(), "GITCAM onCreate() finished", Toast.LENGTH_LONG).show();
        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        setRepeatingAlarm(GITextCloud.class);
    }

    public void setOneTimeAlarm(java.lang.Class<?> cls) {
        Intent intent = new Intent(this, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (5 * 1000), pendingIntent);
    }

    public void setRepeatingAlarm(java.lang.Class<?> cls) {
        Intent intent = new Intent(this, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), (5 * 1000), pendingIntent);
    }


}
