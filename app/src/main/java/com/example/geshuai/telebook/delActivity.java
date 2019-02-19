package com.example.geshuai.telebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class delActivity extends AppCompatActivity implements View.OnClickListener {
    EditText delEdit;
    Button del_ok;
    Button del_back;
    private MyDatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del);
        delEdit = (EditText)findViewById(R.id.del_edit);
        del_ok = (Button)findViewById(R.id.del_button);
        del_back = (Button)findViewById(R.id.del_back);
        del_ok.setOnClickListener(this);
        del_back.setOnClickListener(this);
        databaseHelper = new MyDatabaseHelper(this,"Information.db",null,1);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.del_button:
                if(delEdit.getText().toString().equals("")){
                    Toast.makeText(this, "请先输入要删除的联系人姓名！",
                            Toast.LENGTH_SHORT).show();
                }else{
                    int i = 0;
                    SQLiteDatabase database = databaseHelper.
                            getWritableDatabase();
                    Cursor cursor = database.rawQuery("select * " +
                            "from information where name = ?",new
                            String[]{delEdit.getText().toString()} );
                    if (cursor.moveToFirst()) {
                        do {
                            i++;
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                    if(i == 0){
                        Toast.makeText(this, "不存在该联系人！",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        database.delete("information","name = ?",new
                                String[]{delEdit.getText().toString()});
                        Toast.makeText(this, "已成功删除该联系人！",
                                Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case R.id.del_back:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
