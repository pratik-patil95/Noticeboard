package com.example.sidray.noticeboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "College.db";
    public static final String TABLE_NAME = "Registerdetails";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "SEM";
    public static final String COL4 = "ROLLNO";
    public static final String COL5 = "EMAIL";
    public static final String COL6 = "PASSWORD";

    public static final String TABLE_NAME2 = "ImageDetails";
    public static final String COL7 = "IMAGE";
    public static final String COL8 = "SUBJECT";
    public static final String TABLE_NAME1 = "Noticedetails";
    public static final String COL11 = "ID";
    public static final String COL22 = "DATE";
    public static final String COL33 = "SUBJECT";
    public static final String COL44 = "MESSAGE";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String query = "Create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SEM TEXT, ROLLNO TEXT, EMAIL TEXT, PASSWORD TEXT)";
        db.execSQL(query);
        String query1 = "Create table " + TABLE_NAME1 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, SUBJECT TEXT, MESSAGE TEXT)";
        db.execSQL(query1);
        String query2 = "Create table " + TABLE_NAME2 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,SUBJECT TEXT,IMAGE BLOB)";
        db.execSQL(query2);
    }

    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
    }
    public boolean insertImage(String sub,byte[] image)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL8,sub);
        cv.put(COL7,image);
        long result = db.insert(TABLE_NAME2, null, cv);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertData(String name, String sem, String rollno, String email, String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
            //cv.put(COL1,id);
            cv.put(COL2, name);
            cv.put(COL3, sem);
            cv.put(COL4, rollno);
            cv.put(COL5, email);
            cv.put(COL6, pwd);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1)
            return false;
        else
            return true;

    }
    public boolean adminData(String date,String sub,String msg)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        //cv.put(COL1,id);
        cv.put(COL22,date);
        cv.put(COL33,sub);
        cv.put(COL44,msg);

        long result=db.insert(TABLE_NAME1,null,cv);
        if (result==-1)
            return false;
        else
            return true;
    }
     public Cursor getImageData()
     {
         SQLiteDatabase db=this.getWritableDatabase();
         Cursor res=db.rawQuery("Select * from ImageDetails",null);
         return res;
     }
    public Cursor getImageData1(String sub)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select IMAGE from ImageDetails where SUBJECT=?",new String[]{sub});
        return res;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from " +TABLE_NAME,null);
        return res;
    }
    public Cursor getnoticeData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from " +TABLE_NAME1,null);
        return res;
    }


}

