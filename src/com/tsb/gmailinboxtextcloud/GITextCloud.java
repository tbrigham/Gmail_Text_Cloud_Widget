package com.tsb.gmailinboxtextcloud;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class GITextCloud extends AppWidgetProvider {
    private static int count = 0;
    private static int widgetCount = 0;
    public static final String INCREMENT_COUNT_UPDATE = "INCREMENT_COUNT_UPDATE";

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        count = 0;
        setOneTimeAlarm(this.getClass(), context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals("android.appwidget.action.APPWIDGET_ENABLED")) {
        } else if (intent.getAction().equals("android.appwidget.action.APPWIDGET_DISABLED")) {
        } else if (intent.getAction().equals(INCREMENT_COUNT_UPDATE)) {
            count++;
            refreshViews(context);
            Toast.makeText(context, "widget #" + widgetCount, Toast.LENGTH_SHORT).show();
            if (widgetCount != 0) {
                setOneTimeAlarm(this.getClass(), context);
            }
        } else {
            //Toast.makeText(context, "count = " + count + " | " + intent.getAction(), Toast.LENGTH_LONG).show();
        }
    }

    /** Called when the activity is first created. */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private void refreshViews(Context context) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.main);
        rv.setTextViewText(R.id.widget_textview, "" + count);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        ComponentName cName = new ComponentName(context, GITextCloud.class);
        widgetCount = manager.getAppWidgetIds(cName).length;
        manager.updateAppWidget(cName, rv);
    }

    private void setOneTimeAlarm(java.lang.Class<?> cls, Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        intent.setAction(INCREMENT_COUNT_UPDATE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (3 * 1000), pendingIntent);
    }


    public int someMethod() {
        return 1;
    }


}
