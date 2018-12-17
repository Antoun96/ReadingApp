package com.example.antoun.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Point;

import java.util.List;

/**
 * Created by Antoun on 3/31/2016.
 */
public class DataBase extends SQLiteOpenHelper {
    private static final String Users_data = "itworx.db";
    private static final String table = "agenda";
    private static final String col_subject = "SUBJECT";
    private static final String col_day = "DAY";
    private static final String col_time = "TIME";


    public DataBase(Context context) {
        super(context, Users_data, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table" + table + "ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME VARCHAR(40) NOT NULL , EMAIL VARCHAR (60) NOT NULL , PASSWORD VARCHAR (12) NOT NULL , PHONE VARCHAR (20) NOT NULL");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + Users_table);
        onCreate(db);
    }

    public boolean insertData(String NAME, String EMAIL, String PASSWORD, String PHONE) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_name, NAME);
        contentValues.put(col_email, EMAIL);
        contentValues.put(col_password, PASSWORD);
        contentValues.put(col_phone, PHONE);
        long result = db.insert(Users_table, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor row = db.rawQuery("select * from "+ Users_table ,null);
        return row;
    }
}
