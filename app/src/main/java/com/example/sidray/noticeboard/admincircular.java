package com.example.sidray.noticeboard;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class admincircular extends AppCompatActivity {
    int flag=0;
    Button add,save;
    ImageView imageView;
    EditText sub;
    int REQUEST_CODE_GALLERY=999;
    DbHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admincircular);
        mydb=new DbHelper(this);
        add=findViewById(R.id.choose);
        save=findViewById(R.id.save);
        imageView=findViewById(R.id.imageView8);
        sub=findViewById(R.id.sub);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(admincircular.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY);
            }
        });
    }
    public void saveImage(View view) {
        if (TextUtils.isEmpty(sub.getText().toString()))
            Toast.makeText(admincircular.this, "Enter Subject With Date", Toast.LENGTH_SHORT).show();
        else if (flag==0)
            Toast.makeText(admincircular.this, "Enter Image", Toast.LENGTH_SHORT).show();
        else {
            boolean result = mydb.insertImage(sub.getText().toString(), imageViewToByte(imageView));

            if (result == true) {
                Toast.makeText(admincircular.this, "Image Is Saved", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(admincircular.this, UserActivity.class);
                PendingIntent pending = PendingIntent.getActivity(admincircular.this, 0, intent, 0);
                @SuppressLint({"NewApi", "LocalSuppress"}) Notification noti = new Notification.Builder(admincircular.this).setContentTitle("Circular Notification")

                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pending)
                        .build();

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(0, noti);
            } else
                Toast.makeText(admincircular.this, "Image Is Not Saved", Toast.LENGTH_SHORT).show();
        }
    }
    public static byte[] imageViewToByte(ImageView image){
        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        ((Bitmap) bitmap).compress(Bitmap.CompressFormat.JPEG,10,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==REQUEST_CODE_GALLERY){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
            else{
                Toast.makeText(this,"You dont have permission to access file location",Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode== REQUEST_CODE_GALLERY && resultCode==RESULT_OK && data!=null)
        {
            Uri uri=data.getData();
            try{
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
                flag=1;
            }
            catch(FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
