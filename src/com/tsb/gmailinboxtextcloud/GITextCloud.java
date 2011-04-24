package com.tsb.gmailinboxtextcloud;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class GITextCloud extends AppWidgetProvider {

    private static int count = 0;
    private static int widgetCount = 0;
    //private static GITextQueue<String> textViewQueue;
    public static final String INCREMENT_COUNT_UPDATE = "INCREMENT_COUNT_UPDATE";
    public static final int DEFAULT_MAX_EMAIL_COUNT = 10;


    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        count = 0;
        //textViewQueue = new GITextQueue<String>();
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
            //refreshViews(context);
            refreshAllViews(context);
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

    private void refreshAllViews(Context context) {
        int intCount = count;
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.main);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        ComponentName cName = new ComponentName(context, GITextCloud.class);
        widgetCount = manager.getAppWidgetIds(cName).length;

        if (intCount > 0) {
            rv.setTextViewText(R.id.textview0, "" + count);
        }
        intCount--;

        if (intCount > 0) {
            rv.setTextViewText(R.id.textview1, "" + count);
        }
        intCount--;

        if (intCount > 0) {
            rv.setTextViewText(R.id.textview2, "" + count);
        }
        intCount--;

        if (intCount > 0) {
            rv.setTextViewText(R.id.textview3, "" + count);
        }
        intCount--;

        if (intCount > 0) {
            rv.setTextViewText(R.id.textview4, "" + count);
        }
        intCount--;

        if (intCount > 0) {
            rv.setTextViewText(R.id.textview5, "" + count);
        }
        intCount--;

        if (intCount > 0) {
            rv.setTextViewText(R.id.textview6, "" + count);
        }
        intCount--;

        if (intCount > 0) {
            rv.setTextViewText(R.id.textview7, "" + count);
        }
        intCount--;

        if (intCount > 0) {
            rv.setTextViewText(R.id.textview8, "" + count);
        }
        intCount--;

        if (intCount > 0) {
            rv.setTextViewText(R.id.textview9, "" + count);
        }

        manager.updateAppWidget(cName, rv);

    }

    private void refreshViewByID(Context context, int viewID, String upDate) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.main);
        rv.setTextViewText(viewID, upDate);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        ComponentName cName = new ComponentName(context, GITextCloud.class);
        widgetCount = manager.getAppWidgetIds(cName).length;
        manager.updateAppWidget(cName, rv);
    }

    private void refreshViews(Context context) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.main);
        rv.setTextViewText(R.id.textview0, "" + count);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        ComponentName cName = new ComponentName(context, GITextCloud.class);
        widgetCount = manager.getAppWidgetIds(cName).length;
        manager.updateAppWidget(cName, rv);
    }

    private void refreshViews(Context context, int viewID, String text) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.main);
        rv.setTextViewText(viewID, text);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        ComponentName cName = new ComponentName(context, GITextCloud.class);
        widgetCount = manager.getAppWidgetIds(cName).length;
        manager.updateAppWidget(cName, rv);
    }

    private void setOneTimeAlarm(java.lang.Class<?> cls, Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        intent.setAction(INCREMENT_COUNT_UPDATE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (3 * 1000), pendingIntent);
    }


}
