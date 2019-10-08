package com.example.sidray.noticeboard;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Userhomepage extends AppCompatActivity {
    DbHelper mydb;
    Button btnview,buttonulogout,circular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhomepage);

        mydb=new DbHelper(this);
        circular=findViewById(R.id.btncircular);
        btnview=findViewById(R.id.buttonnb);
        buttonulogout=findViewById(R.id.btnulogout);

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewdata();
            }
        });
        buttonulogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }
    public void viewList(View view)
    {
        Intent intent=new Intent(this,UserCircular.class);
        startActivity(intent);
    }
    public void viewdata(){


        Cursor res=mydb.getnoticeData();
        if (res.getCount()==0){
            Toast.makeText(Userhomepage.this,"Error: nothing formed",Toast.LENGTH_LONG).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id:" + res.getString(0) + "\n");
            buffer.append("Date:" + res.getString(1) + "\n");
            buffer.append("Subject:" + res.getString(2) + "\n");
            buffer.append("Message:" + res.getString(3) + "\n");

        }
        showMessage("Notices",buffer.toString());



    }

    private void showMessage(String title, String Message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    private void logout(){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}


