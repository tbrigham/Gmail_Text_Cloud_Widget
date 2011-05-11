package com.tsb.gmailinboxtextcloud;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by IntelliJ IDEA.
 * User: Tyler
 * Date: 4/16/11
 * Time: 12:15 PM
 */
public class GITextConfig extends ListActivity {

    private static final String[] LIST_ITEMS_TITLES = new String[] {
            "Gmail Account", "Sync Content", "Show Most Recent", "ITEM", "ITEM", "ITEM", "ITEM", "ITEM", "ITEM", "ITEM", "ITEM", "ITEM", "ITEM"
    };

    private static final String[] LIST_ITEMS_SUB = new String[] {
            "1 ", "2 ", "3 ", "***", "***", "***", "***", "***", "***", "***", "***", "***", "***"
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gitc_config);

        ListMenuAdapter lma = new ListMenuAdapter(this, LIST_ITEMS_TITLES, LIST_ITEMS_SUB);

        setListAdapter(lma);

        Button launchWebPage = (Button)findViewById(R.id.create_button);

        launchWebPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent( Intent.ACTION_VIEW , Uri.parse("http://www.fistfulofneurons.com/") ));
            }
        });

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
