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

    static final String DATABASE_NAME = "note4.db";
    static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "seiseki";
    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "title";
    public static final String COL_MEMBERID = "memberid";
    public static final String COL_GAMEID = "gameid";
    public static final String COL_SEISEKI = "seiseki";


    protected final Context context;
    protected DatabaseHelper dbHelper;
    protected SQLiteDatabase db;
    protected static String title;

    public DBAdapter(Context context,String title){
        this.context = context;
        this.title = title;
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
                            + COL_TITLE + " TEXT NOT NULL,"
                            + COL_GAMEID + " INTEGER default 0,"
                            + COL_MEMBERID + " INTEGER default 0,"
                            + COL_SEISEKI + " INTEGER default 0)"
                            );
            for(int i=1;i<=20;i++){
                for (int j = 1; j <= 3; j++) {
                    ContentValues values = new ContentValues();
                    values.put(COL_TITLE,title);
                    values.put(COL_GAMEID, i);
                    values.put(COL_MEMBERID, j);
                    values.put(COL_SEISEKI, 0);
                    db.insertOrThrow(TABLE_NAME, null, values);
                }
            }


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

    public boolean deleteSeiseki(int gameid){
        return db.delete(TABLE_NAME, COL_GAMEID + "=" + gameid, null) > 0;
    }

    public Cursor getAllSeiseki(){
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public void saveSeiseki(String title, int gameid,int memberid,int seiseki){
        ContentValues values = new ContentValues();
        values.put(COL_TITLE,title);
        values.put(COL_GAMEID,gameid);
        values.put(COL_MEMBERID,memberid);
        values.put(COL_SEISEKI,seiseki);
        db.insertOrThrow(TABLE_NAME, null, values);

    }
}