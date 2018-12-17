package where.example.com.readingapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import where.example.com.readingapp.Class.Read;
import where.example.com.readingapp.Class.User;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String ENCODING_SETTING = "PRAGMA encoding ='windows-1256'";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Library";
    private Context c;
    private static DataBaseHelper instance = null;

    public DataBaseHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
        this.c = c;
    }

    public static DataBaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseHelper(context);
        }
        return instance;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        if (!db.isReadOnly()) {
            db.execSQL(ENCODING_SETTING);
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + Contract.LibraryEntry.FIRST_TABLE_NAME + " (" +
                Contract.LibraryEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                Contract.LibraryEntry.NAME + " TEXT NOT NULL );"
        );

        db.execSQL("CREATE TABLE " + Contract.LibraryEntry.SECOND_TABLE_NAME + " (" +
                Contract.LibraryEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                Contract.LibraryEntry.FROM + " INTEGER NOT NULL, " +
                Contract.LibraryEntry.TO + " INTEGER NOT NULL, " +
                Contract.LibraryEntry.USER_ID + " INTEGER NOT NULL, " +
                " FOREIGN KEY (" + Contract.LibraryEntry.USER_ID + ") REFERENCES " + Contract.LibraryEntry.FIRST_TABLE_NAME + "(" + Contract.LibraryEntry.COLUMN_ID + "));"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addUsers() {

        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(Contract.LibraryEntry.COLUMN_ID, "1");
        values.put(Contract.LibraryEntry.NAME, "Jane");

        db.insert(Contract.LibraryEntry.FIRST_TABLE_NAME, null, values);

        values.put(Contract.LibraryEntry.COLUMN_ID, "2");
        values.put(Contract.LibraryEntry.NAME, "Ahmed");

        db.insert(Contract.LibraryEntry.FIRST_TABLE_NAME, null, values);

        db.close();
    }

    public void addReadPages() {

        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(Contract.LibraryEntry.COLUMN_ID, "1");
        values.put(Contract.LibraryEntry.FROM, "1");
        values.put(Contract.LibraryEntry.TO, "20");
        values.put(Contract.LibraryEntry.USER_ID, "1");

        db.insert(Contract.LibraryEntry.SECOND_TABLE_NAME, null, values);

        values.put(Contract.LibraryEntry.COLUMN_ID, "2");
        values.put(Contract.LibraryEntry.FROM, "33");
        values.put(Contract.LibraryEntry.TO, "47");
        values.put(Contract.LibraryEntry.USER_ID, "2");

        db.insert(Contract.LibraryEntry.SECOND_TABLE_NAME, null, values);

        values.put(Contract.LibraryEntry.COLUMN_ID, "3");
        values.put(Contract.LibraryEntry.FROM, "9");
        values.put(Contract.LibraryEntry.TO, "30");
        values.put(Contract.LibraryEntry.USER_ID, "1");

        db.insert(Contract.LibraryEntry.SECOND_TABLE_NAME, null, values);

        values.put(Contract.LibraryEntry.COLUMN_ID, "4");
        values.put(Contract.LibraryEntry.FROM, "39");
        values.put(Contract.LibraryEntry.TO, "67");
        values.put(Contract.LibraryEntry.USER_ID, "2");

        db.insert(Contract.LibraryEntry.SECOND_TABLE_NAME, null, values);

    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] column = {Contract.LibraryEntry.COLUMN_ID, Contract.LibraryEntry.NAME};
        Cursor c = db.query(Contract.LibraryEntry.FIRST_TABLE_NAME, column, null, null, null, null, null);

        int id = c.getColumnIndex(Contract.LibraryEntry.COLUMN_ID);
        int name = c.getColumnIndex(Contract.LibraryEntry.NAME);


        User[] users_arr = new User[c.getCount()];

        for (int i = 0; i < c.getCount(); i++) {
            while (c.moveToNext()) {
                users_arr[i] = new User();
                users_arr[i].id = c.getInt(id);
                users_arr[i].name = c.getString(name);
                users.add(users_arr[i]);
            }
        }


        c.close();
        db.close();

        return users;
    }

    public ArrayList<Read> getAllReads() {
        ArrayList<Read> reads = new ArrayList<Read>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] column = {Contract.LibraryEntry.COLUMN_ID, Contract.LibraryEntry.FROM, Contract.LibraryEntry.TO, Contract.LibraryEntry.USER_ID};
        Cursor c = db.query(Contract.LibraryEntry.SECOND_TABLE_NAME, column, null, null, null, null, Contract.LibraryEntry.FROM + " ASC");

        int id = c.getColumnIndex(Contract.LibraryEntry.COLUMN_ID);
        int from = c.getColumnIndex(Contract.LibraryEntry.FROM);
        int to = c.getColumnIndex(Contract.LibraryEntry.TO);
        int user_id = c.getColumnIndex(Contract.LibraryEntry.USER_ID);

        Read[] reads_arr = new Read[c.getCount()];

        for (int i = 0; i < c.getCount(); i++) {
            while (c.moveToNext()) {
                reads_arr[i] = new Read();
                reads_arr[i].id = c.getInt(id);
                reads_arr[i].from = c.getInt(from);
                reads_arr[i].to = c.getInt(to);
                reads_arr[i].user_id = c.getInt(user_id);
                reads.add(reads_arr[i]);
            }
        }

        c.close();
        db.close();

        return reads;
    }

    public ArrayList<Read> getReadsById(int id) {
        ArrayList<Read> reads = new ArrayList<Read>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = Contract.LibraryEntry.USER_ID + " = '" + id + "'";
        String[] column = {Contract.LibraryEntry.FROM, Contract.LibraryEntry.TO, Contract.LibraryEntry.USER_ID};
        Cursor c = db.query(Contract.LibraryEntry.SECOND_TABLE_NAME, column, selection, null, null, null, Contract.LibraryEntry.FROM + " ASC");

        int from = c.getColumnIndex(Contract.LibraryEntry.FROM);
        int to = c.getColumnIndex(Contract.LibraryEntry.TO);
        int user_id = c.getColumnIndex(Contract.LibraryEntry.USER_ID);

        Read[] reads_arr = new Read[c.getCount()];

        for (int i = 0; i < c.getCount(); i++) {
            while (c.moveToNext()) {
                reads_arr[i] = new Read();
                reads_arr[i].from = c.getInt(from);
                reads_arr[i].to = c.getInt(to);
                reads_arr[i].user_id = c.getInt(user_id);
                reads.add(reads_arr[i]);
            }
        }

        c.close();
        db.close();

        return reads;
    }

    public String getUserById(int id) {
        String usr_name = new String();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = Contract.LibraryEntry.COLUMN_ID + " = '" + id + "'";
        String[] column = {Contract.LibraryEntry.NAME};
        Cursor c = db.query(Contract.LibraryEntry.FIRST_TABLE_NAME, column, selection, null, null, null, null);

        int name = c.getColumnIndex(Contract.LibraryEntry.NAME);
        if (c.getCount() > 0) {
            c.moveToFirst();
            usr_name = c.getString(name);
        }
        c.close();
        db.close();

        return usr_name;
    }

}

