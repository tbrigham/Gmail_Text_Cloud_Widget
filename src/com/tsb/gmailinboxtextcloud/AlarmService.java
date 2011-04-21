package com.tsb.gmailinboxtextcloud;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
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

    private AlarmManager am;
    private PendingIntent pendingIntent;

//    public static String INCREMENT_COUNT_UPDATE = "INCREMENT_COUNT_UPDATE";
//    public static String RESET_COUNT_UPDATE = "RESET_COUNT_UPDATE";
//    public static String PAUSE_COUNT_UPDATE = "PAUSE_COUNT_UPDATE";


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
	public void onCreate() {
		Toast.makeText(this, "My Service Created", Toast.LENGTH_LONG).show();
        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
	}

    @Override
	public void onDestroy() {
		Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
        am.cancel(pendingIntent);
	}

    @Override
	public void onStart(Intent intent, int startid) {
		Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
        setRepeatingAlarm(GITextCloud.class);
	}

    public void setOneTimeAlarm(java.lang.Class<?> cls) {
        Intent intent = new Intent(this, cls);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (5 * 1000), pendingIntent);
    }

    public void setRepeatingAlarm(java.lang.Class<?> cls) {
        Intent intent = new Intent(this, cls);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), (5 * 1000), pendingIntent);
    }
}
