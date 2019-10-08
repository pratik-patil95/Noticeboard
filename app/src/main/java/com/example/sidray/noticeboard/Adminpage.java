package com.example.sidray.noticeboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Adminpage extends AppCompatActivity {
 Button buttonnotice,buttonlogout,circular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);
        buttonnotice=(Button)findViewById(R.id.btnnotice);
        buttonlogout=(Button)findViewById(R.id.btnlogout);
        circular=findViewById(R.id.btncircular);
        buttonnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminpage.this,NoticeActivity.class);
                startActivity(intent);
            }
        });
        buttonlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }
    public void circular(View view)
    {
        Intent intent=new Intent(this,admincircular.class);
        startActivity(intent);
    }

    private void logout(){

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
