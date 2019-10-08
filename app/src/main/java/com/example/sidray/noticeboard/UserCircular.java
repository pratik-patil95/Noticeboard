package com.example.sidray.noticeboard;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserCircular extends AppCompatActivity {
    ListView list;
    DbHelper mydb;
    ArrayList<String> aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_circular);
        try {
            mydb = new DbHelper(this);
            list = findViewById(R.id.list);
            final Cursor res = mydb.getImageData();
            if (res.getCount() == 0) {
                Toast.makeText(this, "Error Nothing Found", Toast.LENGTH_SHORT).show();
                return;
            }
            aa = new ArrayList<>();
            while (res.moveToNext()) {
                aa.add(res.getString(1));
            }
            ArrayAdapter<String> arr = new ArrayAdapter<String>(UserCircular.this, android.R.layout.simple_list_item_1, aa);
            list.setAdapter(arr);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(getApplicationContext(),image.class);
                    intent.putExtra("msg",aa.get(position));
                    startActivity(intent);
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
