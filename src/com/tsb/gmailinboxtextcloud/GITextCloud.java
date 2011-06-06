package com.tsb.gmailinboxtextcloud;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import org.mcavallo.opencloud.Cloud;

public class GITextCloud extends AppWidgetProvider {
    //private instance vars
    private static int count = 0;
    private static int widgetCount = 0;
    private static Cloud gitc;


    //public constants
    public static final String INCREMENT_COUNT_UPDATE = "GITC_INCREMENT_COUNT_UPDATE";
    public static final String LAUNCH_GMAIL_GAPPS = "GITC_LAUNCH_GMAIL_GAPPS";
    public static final String TAG = "GITextCloud";
    public static final int DEFAULT_MAX_EMAIL_COUNT = 10;



    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        count = 0;
        gitc = new Cloud();
        gitc.setMaxTagsToDisplay(DEFAULT_MAX_EMAIL_COUNT);
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
        if (intent.getAction().equals(INCREMENT_COUNT_UPDATE)) {
            //increment count, update views, and start another alarm
            refreshViews(context, R.id.unread_inbox_count, R.layout.gitc_widget_html, "" + count);

            //Toast.makeText(context, "widget #" + widgetCount, Toast.LENGTH_SHORT).show();
            if (widgetCount != 0) {
                setOneTimeAlarm(this.getClass(), context, 3, INCREMENT_COUNT_UPDATE);
            }

            count++;
        }
        else if (intent.getAction().equals(LAUNCH_GMAIL_GAPPS)) {
            //launch gmail application
            count = 0;
            Intent mailClient = new Intent(Intent.ACTION_VIEW);
            mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
            mailClient.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mailClient);
        }
        else if (intent.getAction().equals("mobi.intuitit.android.hpp.ACTION_READY")) {
            //attach an onClick intent to the layout
            final Intent onClick = new Intent(context, GITextCloud.class);
            onClick.setAction(LAUNCH_GMAIL_GAPPS);
            PendingIntent onClickPending = PendingIntent.getBroadcast(context, 0, onClick, 0);
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.gitc_widget_html);
            rv.setOnClickPendingIntent(R.id.full_widget, onClickPending);

            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            int[] ids = manager.getAppWidgetIds(new ComponentName(context, GITextCloud.class));
            for (int id : ids) {
                manager.updateAppWidget(id, rv);
            }
        }
        else {
            //android.util.Log.i(TAG, intent.getAction());
        }
        android.util.Log.i(TAG, intent.getAction());
    }

    /** Called when the activity is first created. */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        gitc.clear();

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
}
