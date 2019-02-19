package com.example.geshuai.telebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class alterActivity extends AppCompatActivity implements View.OnClickListener{
    EditText alter_name;
    Spinner alter_spinner;
    EditText alter_edit;
    Button alter_ok;
    Button alter_back;
    private MyDatabaseHelper databaseHelper;
    int m;
    String[] color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter);
        alter_name = (EditText)findViewById(R.id.alter_name);
        alter_edit = (EditText)findViewById(R.id.alter_edit);
        alter_spinner = (Spinner)findViewById(R.id.alter_spinner);
        alter_ok = (Button)findViewById(R.id.alter_ok);
        alter_back = (Button)findViewById(R.id.alter_back);
        alter_ok.setOnClickListener(this);
        alter_back.setOnClickListener(this);
        choice();
        databaseHelper = new MyDatabaseHelper(this,"Information.db",null,1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.alter_ok:
                if(alter_name.getText().toString().equals("")){
                    Toast.makeText(this, "请先输入要修改的联系人姓名！",
                            Toast.LENGTH_SHORT).show();
                }else{
                    int i = 0;
                    SQLiteDatabase database = databaseHelper.getWritableDatabase();
                    Cursor cursor = database.rawQuery("select * " +
                            "from information where name = ?",
                            new String[]{alter_name.getText().toString()} );
                    if (cursor.moveToFirst()) {
                        do {
                            i++;
                        } while (cursor.moveToNext());
                    }

                    if(i == 0){
                        Toast.makeText(this, "不存在该联系人！", Toast.LENGTH_SHORT).show();
                    }else{
                        if(color[m].toString().equals("姓名")){
                           database.execSQL("update information " +
                                   "set name = ? where name = ?",
                                   new String[]{alter_edit.getText()
                                           .toString(),alter_name.getText().toString()});

                        }
                        if(color[m].toString().equals("联系方式")){
                            database.execSQL("update information " +
                                    "set number = ? where name = ?",
                                    new String[]{alter_edit.getText()
                                            .toString(),alter_name.getText().toString()});
                        }
                        if(color[m].toString().equals("关系")){
                            database.execSQL("update information " +
                                    "set relative = ? where name = ?",
                                    new String[]{alter_edit.getText()
                                            .toString(),alter_name.getText().toString()});
                        }
                        Toast.makeText(this, "修改成功！", Toast.LENGTH_SHORT).show();

                    }cursor.close();

                }
                break;
            case R.id.alter_back:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }

    }
    public void choice(){
        String []mitems=getResources().getStringArray(R.array.updataType);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,mitems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        alter_spinner.setAdapter(adapter);
        alter_spinner.setOnItemSelectedListener(new AdapterView.
                OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view, int x, long l) {

                color = getResources().getStringArray(R.array.updataType);
                m = x;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
    }
}
