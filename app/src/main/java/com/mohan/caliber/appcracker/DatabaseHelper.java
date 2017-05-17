package com.mohan.caliber.appcracker;

/**
 *  Created by mohan on 13/05/17..
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.INTEGER;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "EXPENSES";


    // Table columns
    public static final String _ID = "_id";
    public static final String ITEM = "item";
    public static final String CATEGORY = "category";
    public static final String UNIT = "unit";
    public static final String PRICE = "price";
    //Vegetables table columns


    // Database Information
    static final String DB_NAME = "MOHAN_EXPENSES.DB";
    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    //private  static  final String CREATE_TABLE = "create table EXPENSES (_id INTEGER PRIMARY KEY AUTOINCREMENT, item TEXT NOT NULL, category TEXT NOT NULL, uint INTEGER, price INTEGER)";
  /*  private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ITEM + " TEXT NOT NULL, " + CATEGORY + " TEXT NOT NULL," + UNIT + " INTEGER," + PRICE + " INTEGER);";*/
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER, " + ITEM + " TEXT NOT NULL, " + CATEGORY + " TEXT NOT NULL," + UNIT + " INTEGER," + PRICE + " INTEGER);";
//Creating category table
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
