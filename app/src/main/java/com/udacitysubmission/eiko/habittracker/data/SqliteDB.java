package com.udacitysubmission.eiko.habittracker.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import com.udacitysubmission.eiko.habittracker.R;
import com.udacitysubmission.eiko.habittracker.data.InfoContract.InfoEntry;

/**
 * Created by eiko on 12/14/2016.
 */
public class SqliteDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "contact.db";
    private static final int DATABASE_VERSION = 1;

    public SqliteDB(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_INFO_TABLE = "CREATE TABLE " +
                InfoEntry.TABLE_NAME + " (" +
                InfoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                InfoEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                InfoEntry.COLUMN_PHONE + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_INFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public Cursor readAllHabits() {
        //Your code
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {InfoContract.InfoEntry.COLUMN_NAME,
                InfoContract.InfoEntry.COLUMN_PHONE,
                InfoContract.InfoEntry._ID};
        Cursor cursor = db.query(InfoContract.InfoEntry.TABLE_NAME,
                projection, null, null, null, null, null);

        return cursor;
    }
}
