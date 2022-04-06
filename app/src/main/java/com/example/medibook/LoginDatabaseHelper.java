package com.example.medibook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LoginDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "login.db";
    public static final String TABLE_NAME = "login_table";
    public static final String USER_CRED = "USERNAME";
    public static final String PASS_CRED = "PASSWORD";

    public LoginDatabaseHelper(@Nullable Context context) { super(context, DATABASE_NAME, null, 1);  }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME + " (USERNAME TEXT,PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertCredentials(String user, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_CRED,user);
        contentValues.put(PASS_CRED,pass);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1) return false;
        else return true;
    }

    public Cursor getAllCredentials(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

}
