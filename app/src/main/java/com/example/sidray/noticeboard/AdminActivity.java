package com.example.sidray.noticeboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {
    private Button login;
    EditText username,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        login=(Button)findViewById(R.id.btn);
        username=(EditText)findViewById(R.id.txtuser);
        pwd=(EditText)findViewById(R.id.txtpwd);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void login() {
        String user = username.getText().toString().trim();
        String pass = pwd.getText().toString().trim();
        if (user.equals("kle") && pass.equals("123")) {
            Toast.makeText(this, "Username and Password Matched", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(AdminActivity.this,Adminpage.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Username and Password Do not Matched!!!", Toast.LENGTH_LONG).show();
        }
    }
}
