package com.example.geshuai.telebook;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class addActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editName;
    EditText editNumber;
    Spinner spinner;
    Button add;
    Button flush;
    Button back;
    String[] color;
    int i;
    private MyDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editName = (EditText)findViewById(R.id.add_name);
        editNumber = (EditText)findViewById(R.id.add_number);
        spinner = (Spinner)findViewById(R.id.add_spinner);
        add = (Button)findViewById(R.id.add_ok);
        flush = (Button)findViewById(R.id.add_flush);
        back = (Button)findViewById(R.id.add_back);
        add.setOnClickListener(this);
        flush.setOnClickListener(this);
        back.setOnClickListener(this);
        choice();
    }


    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_ok:
                databaseHelper = new MyDatabaseHelper
                        (this,"Information.db",null,1);
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                if(editNumber.getText().toString().equals("")
                        &&editName.getText().toString().equals("")){
                    Toast.makeText(this, "请先输入联系人信息！",
                            Toast.LENGTH_SHORT).show();
                }else{
                    values.put("name",editName.getText().toString());
                    values.put("number",editNumber.getText().toString());
                    values.put("relative",color[i].toString());
                    db.insert("information",null,values);
                    Toast.makeText(this, "已成功添加信息！", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.add_flush:
                editName.setText("");
                editNumber.setText("");
                break;
            case R.id.add_back:
                Intent intent = new Intent(addActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }

    }
    public void choice(){
        String []mitems=getResources().getStringArray
                (R.array.relativeType);
        ArrayAdapter<String> adapter=new
                ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mitems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(
                    AdapterView<?> adapterView, View view, int x, long l) {

                color = getResources().getStringArray(R.array.relativeType);
                i = x;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
    }


}

