package com.tsb.gmailinboxtextcloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by IntelliJ IDEA.
 * User: Tyler
 * Date: 4/16/11
 * Time: 12:15 PM
 */
public class GITextConfig extends Activity {

    private Button saveButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gitc_config);
        saveButton = (Button)this.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent mailClient = new Intent(Intent.ACTION_VIEW);
                mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
                startActivity(mailClient);
            }
        });
    }


}
