package com.example.medibook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PatientDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "patient.db";
    public static final String TABLE_NAME = "patient_table";
    public static final String P_ID = "ID";
    public static final String P_NAME = "NAME";
    public static final String P_MOBILE ="MOBILE";
    public static final String P_ADDRESS ="ADDRESS";
    public static final String P_AGE ="AGE";
    public static final String P_BLOODGROUP ="BLOODGROUP";
    public static final String P_SEX ="SEX";

    public PatientDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME + " (ID TEXT PRIMARY KEY,NAME TEXT,MOBILE TEXT,ADDRESS TEXT, AGE TEXT, BLOODGROUP TEXT, SEX TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String id, String name, String mobile, String address, String age, String bg, String sex){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(P_ID,id);
        contentValues.put(P_NAME,name);
        contentValues.put(P_MOBILE, mobile);
        contentValues.put(P_ADDRESS, address);
        contentValues.put(P_AGE, age);
        contentValues.put(P_BLOODGROUP, bg);
        contentValues.put(P_SEX, sex);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1) return false;
        else return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Boolean searchPatient(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where ID = "+ id,null );
        if(res.getCount()==0) return false;
        else return true;
    }
    public Integer deleteData (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[] { id });
    }

}
