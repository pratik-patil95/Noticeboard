package com.example.sidray.noticeboard;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class image extends AppCompatActivity {
    ImageView imageView;
    DbHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        mydb=new DbHelper(this);
        imageView=findViewById(R.id.imageView9);
        Intent intent=getIntent();
        String subject=intent.getStringExtra("msg");
        final Cursor res=mydb.getImageData1(subject);

        byte[] image=new byte[0];
        while(res.moveToNext())
        {
            image=res.getBlob(0);
        }
        Bitmap bitmap=BitmapFactory.decodeByteArray(image,0,image.length);
        imageView.setImageBitmap(bitmap);

    }
}
