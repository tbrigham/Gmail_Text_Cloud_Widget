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
    //private instance vars
    private static int count = 0;
    private static int widgetCount = 0;

    //public constants
    public static final String INCREMENT_COUNT_UPDATE = "INCREMENT_COUNT_UPDATE";
    public static final String LAUNCH_GMAIL_GAPPS = "LAUNCH_GMAIL_GAPPS";
    public static final int DEFAULT_MAX_EMAIL_COUNT = 10;



    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        count = 0;
        //set the first alarm
        setOneTimeAlarm(this.getClass(), context, 1, INCREMENT_COUNT_UPDATE);

    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals("android.appwidget.action.APPWIDGET_ENABLED")) {
            //do nothing
        }
        else if (intent.getAction().equals("android.appwidget.action.APPWIDGET_DISABLED")) {
            //do nothing
        }
        else if (intent.getAction().equals(INCREMENT_COUNT_UPDATE)) {
            //increment count, update views, and start another alarm
            refreshViews(context, R.id.unread_inbox_count, R.layout.gitc_html, "" + count);

            //Toast.makeText(context, "widget #" + widgetCount, Toast.LENGTH_SHORT).show();
            if (widgetCount != 0) {
                setOneTimeAlarm(this.getClass(), context, 3, INCREMENT_COUNT_UPDATE);
            }

            count++;
        }
        else if (intent.getAction().equals(LAUNCH_GMAIL_GAPPS)) {
            //launch gmail application
            count = 0;
            refreshViews(context, R.id.unread_inbox_count, R.layout.gitc_html, "" + count);
            Toast.makeText(context, "widget clicked", Toast.LENGTH_SHORT).show();
        }
        else {
            //Toast.makeText(context, "count = " + count + " | " + intent.getAction(), Toast.LENGTH_LONG).show();
        }
    }

    /** Called when the activity is first created. */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Toast.makeText(context, "onUpdate()", Toast.LENGTH_SHORT).show();

        //super.onUpdate(context, appWidgetManager, appWidgetIds);

        //attach an onClick intent to the layout
        final Intent onClick = new Intent(context, GITextCloud.class);
        onClick.setAction(LAUNCH_GMAIL_GAPPS);
        PendingIntent onClickPending = PendingIntent.getBroadcast(context, 0, onClick, 0);
        RemoteViews rv1 = new RemoteViews(context.getPackageName(), R.layout.gitc_html);
        rv1.setOnClickPendingIntent(R.id.full_widget, onClickPending);

        while (appWidgetIds.length == 0) {
            //do nothing
        }

        for (int appWidgetId : appWidgetIds) {
            appWidgetManager.updateAppWidget(appWidgetId, rv1);
        }
    }

    private void refreshViews(Context context, int viewID, int layoutID, String text) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), layoutID);
        rv.setTextViewText(viewID, text);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        ComponentName cName = new ComponentName(context, GITextCloud.class);
        widgetCount = manager.getAppWidgetIds(cName).length;
        manager.updateAppWidget(cName, rv);
    }


    /* setOneTimeAlarm
     *
     * @param
     *      cls         - the target class for the intent
     *      context     - the context for which the intent will be broadcast
     *      seconds     - the time in seconds for the alarm to go off
     *
     *  @return
     *      nothing
     *
     */
    private void setOneTimeAlarm(java.lang.Class<?> cls, Context context, int seconds, String action) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (seconds * 1000), pendingIntent);
    }




/* DEPRECATED CODE */

///*
//
//
//    */
///* DEPRECATED -- FOR TESTING ONLY
//     *
//     *
//     *//*
//
//    private void refreshViewByID(Context context, int viewID, String upDate) {
//        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.main);
//        rv.setTextViewText(viewID, upDate);
//        AppWidgetManager manager = AppWidgetManager.getInstance(context);
//        ComponentName cName = new ComponentName(context, GITextCloud.class);
//        widgetCount = manager.getAppWidgetIds(cName).length;
//        manager.updateAppWidget(cName, rv);
//    }
//
//
//    */
///* DEPRECATED -- FOR TESTING ONLY
//     *
//     *
//     *//*
//
//    private void refreshViews(Context context) {
//        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.main);
//        rv.setTextViewText(R.id.textview0, "" + count);
//        AppWidgetManager manager = AppWidgetManager.getInstance(context);
//        ComponentName cName = new ComponentName(context, GITextCloud.class);
//        widgetCount = manager.getAppWidgetIds(cName).length;
//        manager.updateAppWidget(cName, rv);
//    }
//
//    */
///*
//     * refreshAllViews(Context context)
//     *
//     * ONLY USE WITH main.xml LAYOUT
//     * ONLY USE WITH main.xml LAYOUT
//     * ONLY USE WITH main.xml LAYOUT
//     *
//     *//*
//
//    private void refreshAllViews(Context context) {
//        int intCount = count;
//        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.main);
//        AppWidgetManager manager = AppWidgetManager.getInstance(context);
//        ComponentName cName = new ComponentName(context, GITextCloud.class);
//        widgetCount = manager.getAppWidgetIds(cName).length;
//
//        if (intCount > 0) {
//            rv.setTextViewText(R.id.textview0, "" + intCount);
//        }
//        intCount--;
//
//        if (intCount > 0) {
//            rv.setTextViewText(R.id.textview1, "" + intCount);
//        }
//        intCount--;
//
//        if (intCount > 0) {
//            rv.setTextViewText(R.id.textview2, "" + intCount);
//        }
//        intCount--;
//
//        if (intCount > 0) {
//            rv.setTextViewText(R.id.textview3, "" + intCount);
//        }
//        intCount--;
//
//        if (intCount > 0) {
//            rv.setTextViewText(R.id.textview4, "" + intCount);
//        }
//        intCount--;
//
//        if (intCount > 0) {
//            rv.setTextViewText(R.id.textview5, "" + intCount);
//        }
//        intCount--;
//
//        if (intCount > 0) {
//            rv.setTextViewText(R.id.textview6, "" + intCount);
//        }
//        intCount--;
//
//        if (intCount > 0) {
//            rv.setTextViewText(R.id.textview7, "" + intCount);
//        }
//        intCount--;
//
//        if (intCount > 0) {
//            rv.setTextViewText(R.id.textview8, "" + intCount);
//        }
//        intCount--;
//
//        if (intCount > 0) {
//            rv.setTextViewText(R.id.textview9, "" + intCount);
//        }
//
//        manager.updateAppWidget(cName, rv);
//
//    }
//
//*/

}
