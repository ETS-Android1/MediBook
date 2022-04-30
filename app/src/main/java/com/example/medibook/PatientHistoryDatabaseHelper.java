package com.example.medibook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PatientHistoryDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "history.db";
    public static final String TABLE_NAME = "patient_history_table";
    public static final String P_ID = "ID";
    public static final String P_DATE = "DATE";
    public static final String P_REMARKS ="REMARKS";
    public static final String P_MEDICATION ="MEDICATION";

    public PatientHistoryDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME + " (ID TEXT,DATE TEXT,REMARKS TEXT,MEDICATION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String id, String date, String remarks, String medication){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(P_ID,id);
        contentValues.put(P_DATE,date);
        contentValues.put(P_REMARKS, remarks);
        contentValues.put(P_MEDICATION, medication);

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
