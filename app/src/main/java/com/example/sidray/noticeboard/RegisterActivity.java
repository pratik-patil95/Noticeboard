package com.example.sidray.noticeboard;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {
    DbHelper mydb;
    EditText etname,etsem,etrollno,etemail,etpwd;
    Button btnAddData,btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mydb=new DbHelper(this);

        Toast.makeText(RegisterActivity.this,"Database created",Toast.LENGTH_LONG).show();
        etname=(EditText)findViewById(R.id.editTextname);
        etsem=(EditText)findViewById(R.id.editTextsem);
        etrollno=(EditText)findViewById(R.id.editTextrollno);
        etemail=(EditText)findViewById(R.id.editTextemail);
        etpwd=(EditText)findViewById(R.id.editTextpwd);
        btnAddData=(Button)findViewById(R.id.buttonadd);
        btnlogin=(Button)findViewById(R.id.buttonlogin);
        AddData();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }
    private void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etname.getText().toString()))
                    Toast.makeText(RegisterActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(etsem.getText().toString()))
                    Toast.makeText(RegisterActivity.this, "Enter Semester", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(etrollno.getText().toString()))
                    Toast.makeText(RegisterActivity.this, "Enter Roll No", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(etemail.getText().toString()))
                    Toast.makeText(RegisterActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(etpwd.getText().toString()))
                    Toast.makeText(RegisterActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                else {
                    boolean isInserted = mydb.insertData(etname.getText().toString(), etsem.getText().toString(), etrollno.getText().toString(), etemail.getText().toString(), etpwd.getText().toString());
                    if (isInserted == true)
                        Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(RegisterActivity.this, "Register not Successfully", Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}
