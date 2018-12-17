package com.example.biro.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.renderscript.Sampler;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

/**
 * Created by biro on 10/29/2016.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME="contact.db";
    private Context c;

    public DataBaseHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
        this.c=c;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " +Contract.ContactEntry.TABLE_NAME+ " (" +
                Contract.ContactEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Contract.ContactEntry.COLUMN_ID + " TEXT NOT NULL, "+
                Contract.ContactEntry.COULMN_NAME+" TEXT NOT NULL, "+
                Contract.ContactEntry.COULMN_PH_NUM+" TEXT NOT NULL "+
                ");"
        );
        Toast.makeText(c,"table created",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public String addContact()
    {

        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(Contract.ContactEntry.COLUMN_ID,"152");
        values.put(Contract.ContactEntry.COULMN_NAME,"Ereeny");
        values.put(Contract.ContactEntry.COULMN_PH_NUM,"012111111");

        long rowInserted= db.insert(Contract.ContactEntry.TABLE_NAME,null,values);
        String flag;
        if(rowInserted == -1)
            flag = "doesnt work";
        else
        flag = "row inserted work";

        db.close();
        return flag;
    }
}
