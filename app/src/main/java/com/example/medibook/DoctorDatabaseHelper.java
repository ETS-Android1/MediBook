package com.example.medibook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DoctorDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "doctor.db";
    public static final String TABLE_NAME = "doctor_table";
    public static final String D_ID = "ID";
    public static final String D_NAME = "NAME";
    public static final String D_DATE = "JOINDATE";

    public DoctorDatabaseHelper(@Nullable Context context) { super(context,DATABASE_NAME, null, 1); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME + " (ID TEXT PRIMARY KEY,NAME TEXT,JOINDATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String id, String name, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(D_ID,id);
        contentValues.put(D_NAME,name);
        contentValues.put(D_DATE, date);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1) return false;
        else return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Integer deleteData (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[] { id });
    }

}
