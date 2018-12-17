package where.example.com.moviesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Antoun on 11/24/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME="Movie.db";
    private Context c;
    private static DataBaseHelper instance = null;

    public DataBaseHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
        this.c=c;
    }

    public static DataBaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseHelper(context);
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + Contract.MoviesEntry.TABLE_NAME+ " (" +
                        Contract.MoviesEntry.COLUMN_ID + " INTEGER PRIMARY KEY, "+
                        Contract.MoviesEntry.NAME + " TEXT NOT NULL, "+
                        Contract.MoviesEntry.DATE+" TEXT NOT NULL, "+
                        Contract.MoviesEntry.OVERVIEW+" TEXT NOT NULL, "+
                        Contract.MoviesEntry.POSTER+" TEXT NOT NULL, "+
                        Contract.MoviesEntry.VOTE+" TEXT NOT NULL "+
                        ");"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public String addMovies(Movies movie)
    {

        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(Contract.MoviesEntry.COLUMN_ID,movie.id);
        values.put(Contract.MoviesEntry.NAME,movie.title);
        values.put(Contract.MoviesEntry.DATE,movie.date);
        values.put(Contract.MoviesEntry.OVERVIEW,movie.overview);
        values.put(Contract.MoviesEntry.POSTER,movie.moviePoster);
        values.put(Contract.MoviesEntry.VOTE,movie.vote_avrg);


        long rowInserted= db.insert(Contract.MoviesEntry.TABLE_NAME,null,values);
        String flag;
        if(rowInserted == -1)
            flag = "doesnt work";
        else
            flag = "row inserted work";

        db.close();
        return flag;
    }

    public boolean found(Movies m) {
        SQLiteDatabase db = this.getReadableDatabase();
        db = this.getWritableDatabase();
        String columns[] = {Contract.MoviesEntry.COLUMN_ID, Contract.MoviesEntry.NAME, Contract.MoviesEntry.DATE, Contract.MoviesEntry.OVERVIEW, Contract.MoviesEntry.POSTER, Contract.MoviesEntry.VOTE};
        String selection = Contract.MoviesEntry.COLUMN_ID + " = '" + m.id + "'";
        Cursor c = db.query(Contract.MoviesEntry.TABLE_NAME, columns, selection, null, null, null, null);
        if (c.getCount() > 0) {
            c.close();
            db.close();
            return true;
        }
        else {
            c.close();
            db.close();

            return false;
        }
    }

    public void deleteMovies(Movies m) {
        SQLiteDatabase db = this.getReadableDatabase();
        db = this.getWritableDatabase();
        String where = Contract.MoviesEntry.COLUMN_ID+ "=?";
        String whereArgs[] = {"" + m.id};
        db.delete(Contract.MoviesEntry.TABLE_NAME, where, whereArgs);

        db.close();


    }

    public ArrayList<Movies> getAllMoviesView() {
        ArrayList<Movies> movies = new ArrayList<Movies>();
        SQLiteDatabase db = this.getReadableDatabase();
        db = this.getWritableDatabase();

        String[] column = {Contract.MoviesEntry.COLUMN_ID, Contract.MoviesEntry.NAME, Contract.MoviesEntry.DATE, Contract.MoviesEntry.OVERVIEW, Contract.MoviesEntry.POSTER, Contract.MoviesEntry.VOTE};
        Cursor c = db.query(Contract.MoviesEntry.TABLE_NAME, column, null, null, null, null, null);

        int id = c.getColumnIndex(Contract.MoviesEntry.COLUMN_ID);
        int name = c.getColumnIndex(Contract.MoviesEntry.NAME);
        int date = c.getColumnIndex(Contract.MoviesEntry.DATE);
        int overview = c.getColumnIndex(Contract.MoviesEntry.OVERVIEW);
        int poster = c.getColumnIndex(Contract.MoviesEntry.POSTER);
        int vote = c.getColumnIndex(Contract.MoviesEntry.VOTE);

        Movies[] movie = new Movies[c.getCount()];

        for(int i = 0 ; i<c.getCount();i++) {
            while (c.moveToNext()) {
                movie[i] = new Movies();
                movie[i].id = c.getString(id);
                movie[i].title = c.getString(name);
                movie[i].date = c.getString(date);
                movie[i].overview=c.getString(overview);
                movie[i].moviePoster = c.getString(poster);
                movie[i].vote_avrg = c.getString(vote);
                movies.add(movie[i]);
            }
        }



        c.close();
        db.close();

        return movies;
    }
}

