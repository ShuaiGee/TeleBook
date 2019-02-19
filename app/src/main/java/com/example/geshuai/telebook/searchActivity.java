package com.example.geshuai.telebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.example.geshuai.telebook.R.id.search_edit;

public class searchActivity extends AppCompatActivity implements
        View.OnClickListener {
    EditText searEdit;
    Button sear_ok;
    Button sear_back;
    TextView display;
    TextView display2;
    TextView display3;
    String name_;
    String number_;
    String relative_;
    private MyDatabaseHelper databaseHelper;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        databaseHelper = new MyDatabaseHelper(this, "Information.db", null, 1);
        searEdit = (EditText) findViewById(search_edit);
        sear_ok = (Button) findViewById(R.id.search_button);
        sear_back = (Button) findViewById(R.id.search_back);
        display = (TextView) findViewById(R.id.display);
        display2 = (TextView) findViewById(R.id.display2);
        display3 = (TextView) findViewById(R.id.display3);
        sear_ok.setOnClickListener(this);
        sear_back.setOnClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button:
                if (searEdit.getText().toString().equals("")) {
                    Toast.makeText(this, "请先输入查询信息！", Toast.LENGTH_SHORT).show();
                } else {
                    int i = 0;
                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
                    Cursor cursor = db.rawQuery("select * from information where" +
                            " name = " +
                            "?",new String[]{searEdit.getText().toString()} );
                    if (cursor.moveToFirst()) {
                      do {
                          name_ = cursor.getString(cursor.getColumnIndex("name"));
                          number_ = cursor.getString(cursor.getColumnIndex("number"));
                          relative_ = cursor.getString(cursor.getColumnIndex("relative"));
                          i++;
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                    if (i == 0) {
                        Toast.makeText(this, "未查询到该联系人！", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        display.setText(name_);
                        display2.setText(number_);
                        display3.setText(relative_);
                    }
                }
                break;
            case R.id.search_back:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("search Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
