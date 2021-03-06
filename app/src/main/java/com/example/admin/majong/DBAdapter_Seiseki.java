package com.example.admin.majong;

/**
 * Created by admin on 2014/11/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;

public class DBAdapter_Seiseki {

    static String DATABASE_NAME = "seiseki.db";
    static final int DATABASE_VERSION = 1;

    public static String TABLE_NAME="seiseki";
    public static final String COL_ID = "_id";
    public static final String COL_MEMBER = "member";
    public static final String COL_GAMEID = "gameid";
    public static final String COL_SEISEKI = "seiseki";


    protected final Context context;
    protected DatabaseHelper dbHelper;
    protected SQLiteDatabase db;
    protected static String title;
    protected static String date;
    static String name[];

    public DBAdapter_Seiseki(Context context, String title,String date,String name[]){
        this.context = context;
        this.title = title;
        this.date = date;
        DATABASE_NAME = "seiseki" +title;
        this.name = name;
        for(int i=0;i<=2;i++) {
            System.out.println("メンバー一覧：" + name[i]);
        }
        dbHelper = new DatabaseHelper(this.context);
    }
    public DBAdapter_Seiseki(Context context, String title,String date){
        this.context = context;
        this.title = title;
        this.date = date;
        DATABASE_NAME = "seiseki" +title;
        dbHelper = new DatabaseHelper(this.context);
    }
    public DBAdapter_Seiseki(Context context, String title){
        this.context = context;
        this.title = title;
        DATABASE_NAME = "seiseki" +title;
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
                            + COL_MEMBER + " TEXT,"
                            + COL_SEISEKI + " INTEGER default 0)"
                            );
            for(int i=1;i<=22;i++){
                for (int j = 0; j <= 2; j++) {
                    ContentValues values = new ContentValues();
                    values.put(COL_GAMEID, i);
                    values.put(COL_MEMBER, name[j]);
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

    public DBAdapter_Seiseki open() {
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

    public void saveSeiseki(int gameid,String member,int seiseki){
        ContentValues values = new ContentValues();
        values.put(COL_GAMEID,gameid);
        values.put(COL_MEMBER,member);
        values.put(COL_SEISEKI,seiseki);
        db.insertOrThrow(TABLE_NAME, null, values);

    }
    public String[] getMemberlist(){
        return name;
    }
}