package com.tsb.fistfulofneurons.gmailinboxtextcloud;

import android.appwidget.AppWidgetManager;
import android.widget.Toast;

/**
 * Created by IntelliJ IDEA.
 * User: Tyler
 * Date: 6/16/11
 * Time: 10:35 PM
 */
public class AppWidgetIDHandler {

    //todo make this class work for me

    private static int MY_STATIC_APPWIDGET_ID = 0;

    public static int getApWidgetID() {
        return MY_STATIC_APPWIDGET_ID;
    }

    public static void setAppWidgetID(int newID) {
        AppWidgetIDHandler.MY_STATIC_APPWIDGET_ID = newID;
    }

    public static void incrementAppWidgetID() {
        MY_STATIC_APPWIDGET_ID++;
    }
}
