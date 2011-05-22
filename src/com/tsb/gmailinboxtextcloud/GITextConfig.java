package com.tsb.gmailinboxtextcloud;

import android.app.ListActivity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created by IntelliJ IDEA.
 * User: Tyler
 * Date: 4/16/11
 * Time: 12:15 PM
 */
public class GITextConfig extends ListActivity {

    private int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    private static final String[] LIST_ITEMS_TITLES = new String[] {
            "Gmail Account", "Sync Content", "Show Most Recent"
    };

    private static final String[] LIST_ITEMS_SUB = new String[] {
            "1 ", "2 ", "3 "
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(RESULT_CANCELED);
        setContentView(R.layout.gitc_config);

        ListMenuAdapter lma = new ListMenuAdapter(this, LIST_ITEMS_TITLES, LIST_ITEMS_SUB);
        setListAdapter(lma);

        findViewById(R.id.create_button).setOnClickListener(mOnClickListener);

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If they gave us an intent without the widget id, just bail.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            // Make sure we pass back the original appWidgetId
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_OK, resultValue);
            finish();
        }
    };

    @Override
    protected void onListItemClick (ListView l, View v, int position, long id) {
        ListMenuItem item = (ListMenuItem) getListView().getItemAtPosition(position);
        Toast.makeText(this, item.getHeader() , Toast.LENGTH_SHORT).show();
    }




    private class ListMenuItem {

        private String header;
        public String footer;

        public ListMenuItem (String header, String footer) {
            this.header = header;
            this.footer = footer;
        }


        public String getHeader() {
            return header;
        }
    }

    @SuppressWarnings({"CanBeFinal", "SameParameterValue"})
    private class ListMenuAdapter extends BaseAdapter {

        private ListMenuItem[] menuItems;
        private Context mContext;

        public ListMenuAdapter (Context context, String[] headers, String[] footers) {
            mContext = context;

            menuItems = new ListMenuItem[headers.length];
            for (int i = 0; i < menuItems.length; i++) {
                menuItems[i] = new ListMenuItem(headers[i], footers[i]);
            }

        }

        public int getCount() {
            return menuItems.length;
        }

        public Object getItem(int i) {
            if (i >= menuItems.length || i < 0 ) {
                return null;
            } else {
                return menuItems[i];
            }
        }

        public long getItemId(int i) {
            return i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            LinearLayout itemLayout;
            ListMenuItem item = menuItems[i];

            itemLayout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.list_item, viewGroup, false);

            TextView tvHeader = (TextView) itemLayout.findViewById(R.id.txt1);
            tvHeader.setText(item.getHeader());

            TextView tvFooter = (TextView) itemLayout.findViewById(R.id.txt2);
            tvFooter.setText(item.footer);

            return itemLayout;
        }

    }
}
