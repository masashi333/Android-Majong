package com.example.admin.majong;

/**
 * Created by admin on 2014/11/18.
 */
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

    static final String DATABASE_NAME = "note2.db";
    static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "seiseki";
    public static final String COL_ID = "_id";
    public static final String COL_MEMBERID = "memberid";
    public static final String COL_GAMEID = "gameid";
    public static final String COL_SEISEKI = "seiseki";


    protected final Context context;
    protected DatabaseHelper dbHelper;
    protected SQLiteDatabase db;

    public DBAdapter(Context context){
        this.context = context;
        dbHelper = new DatabaseHelper(this.context);
    }

    //
    // SQLiteOpenHelper
    //

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(
                    "CREATE TABLE " + TABLE_NAME + " ("
                            + COL_GAMEID + " INTEGER default 0,"
                            + COL_MEMBERID + " INTEGER default 0,"
                            + COL_SEISEKI + " INTEGER default 0)"
                            );
        }

        @Override
        public void onUpgrade(
                SQLiteDatabase db,
                int oldVersion,
                int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

    }

    //
    // Adapter Methods
    //

    public DBAdapter open() {
        db = dbHelper.getWritableDatabase();
        return this;
    }


    public void close(){
        dbHelper.close();
    }


    //
    // App Methods
    //


    public boolean deleteAllSeiseki(){
        return db.delete(TABLE_NAME, null, null) > 0;
    }

    public boolean deleteSeiseki(int id){
        return db.delete(TABLE_NAME, COL_ID + "=" + id, null) > 0;
    }

    public Cursor getAllSeiseki(){
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public void saveSeiseki(int gameid,int memberid,int seiseki){
        ContentValues values = new ContentValues();
        values.put(COL_GAMEID,gameid);
        values.put(COL_MEMBERID,memberid);
        values.put(COL_SEISEKI,seiseki);
        db.insertOrThrow(TABLE_NAME, null, values);

    }
}