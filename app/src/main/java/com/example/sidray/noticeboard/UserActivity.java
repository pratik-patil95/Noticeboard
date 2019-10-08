package com.example.sidray.noticeboard;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {
    SQLiteDatabase mydb;
    SQLiteOpenHelper openHelper;
    EditText email,pwd;
    Button btnlgn;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        openHelper = new DbHelper(this);
        mydb = openHelper.getReadableDatabase();
        btnlgn=(Button)findViewById(R.id.btnulogin);
        email=(EditText)findViewById(R.id.txtemail);
        pwd=(EditText)findViewById(R.id.txtpwd);

        btnlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });
    }
    public void login() {
        String email1 = email.getText().toString();
        String pass = pwd.getText().toString();
        //Toast.makeText(this,""+email1+""+pass,Toast.LENGTH_SHORT).show();
        cursor=mydb.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME + " WHERE " + DbHelper.COL5+"=? AND "+ DbHelper.COL6+"=? ", new String[]{email1,pass});
        if (cursor!=null)
        {
            if(cursor.getCount()>0)
            {
                cursor.moveToNext();
                Toast.makeText(getApplicationContext(),"Login Successfully !!!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(UserActivity.this,Userhomepage.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(),"" + "Login Error",Toast.LENGTH_LONG).show();
            }
        }
    }
}
