package com.example.geshuai.telebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button add;
    Button del;
    Button alert;
    Button search;
    public MyDatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = (Button)findViewById(R.id.add1);
        del = (Button)findViewById(R.id.del);
        alert = (Button)findViewById(R.id.alter);
        search = (Button)findViewById(R.id.search);
        add.setOnClickListener(this);
        del.setOnClickListener(this);
        alert.setOnClickListener(this);
        search.setOnClickListener(this);
        databaseHelper = new MyDatabaseHelper(this,"Information.db",null,1);
        databaseHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add1:
                Intent intent = new Intent(MainActivity.this,addActivity.class);
                startActivity(intent);
                break;
            case R.id.del:
                Intent intent1 = new Intent(MainActivity.this,delActivity.class);
                startActivity(intent1);
                break;
            case R.id.alter:
                Intent intent2 = new Intent(MainActivity.this,alterActivity.class);
                startActivity(intent2);
                break;
            case R.id.search:
                Intent intent3 = new Intent(MainActivity.this,searchActivity.class);
                startActivity(intent3);
                break;
        }

    }
}
