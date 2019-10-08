package com.example.sidray.noticeboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;



public class NoticeActivity extends AppCompatActivity {
    DbHelper mydb;
    EditText etdate,etsub,etmsg;
    Button btnAddData,btnview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        mydb=new DbHelper(this);

        Toast.makeText(NoticeActivity.this,"Database created",Toast.LENGTH_LONG).show();
        etdate=findViewById(R.id.editTextdate);
        etsub=findViewById(R.id.editTextsub);
        etmsg=findViewById(R.id.editTextmsg);


        btnAddData=findViewById(R.id.buttonadd);
        btnview=findViewById(R.id.buttonviewall);
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                viewAll();
            }
        });


        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData();
            }
        });

    }
    public void AddData() {
        if (TextUtils.isEmpty(etdate.getText().toString()))
            Toast.makeText(NoticeActivity.this, "Enter Date", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(etsub.getText().toString()))
            Toast.makeText(NoticeActivity.this, "Enter Subject", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(etmsg.getText().toString()))
            Toast.makeText(NoticeActivity.this, "Enter Message", Toast.LENGTH_SHORT).show();
        else {
            boolean isInserted = mydb.adminData(etdate.getText().toString(), etsub.getText().toString(), etmsg.getText().toString());
            if (isInserted == true) {
                Toast.makeText(NoticeActivity.this, "Notice inserted", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(NoticeActivity.this, UserActivity.class);
                PendingIntent pending = PendingIntent.getActivity(NoticeActivity.this, 0, intent, 0);
                @SuppressLint({"NewApi", "LocalSuppress"}) Notification noti = new Notification.Builder(NoticeActivity.this).setContentTitle("Notice Notification")

                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pending)
                        .build();

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(0, noti);
            } else
                Toast.makeText(NoticeActivity.this, "Notice not inserted", Toast.LENGTH_SHORT).show();
        }
    }




     public void viewAll(){


                Cursor res=mydb.getnoticeData();
                if (res.getCount()==0){
                    Toast.makeText(NoticeActivity.this,"Error: nothing formed",Toast.LENGTH_LONG).show();
return;
                }

                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Id:" + res.getString(0) + "\n");
                        buffer.append("Date:" + res.getString(1) + "\n");
                        buffer.append("Subject:" + res.getString(2) + "\n");
                        buffer.append("Message:" + res.getString(3) + "\n");

                }
                showMessage("Data",buffer.toString());



    }

    private void showMessage(String title, String Message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}

