package com.mohan.caliber.appcracker;

/**
 * Created by mohan on 13/05/17.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public DBManager read() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getReadableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }

    public void insert(int _id,String item,String category,int unit,int price) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._ID, _id);//
        contentValue.put(DatabaseHelper.ITEM, item);
        contentValue.put(DatabaseHelper.CATEGORY, category);
        contentValue.put(DatabaseHelper.UNIT, unit);
        contentValue.put(DatabaseHelper.PRICE, price);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }
    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.ITEM, DatabaseHelper.CATEGORY,DatabaseHelper.UNIT,DatabaseHelper.PRICE };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
public Cursor sum(){
    String h = "SELECT SUM("+DatabaseHelper.PRICE+") FROM "+ DatabaseHelper.TABLE_NAME;
  Cursor cursor = database.rawQuery(h,null);
    if (cursor != null) {
        cursor.moveToFirst();
    }
    return cursor;
}
    public Cursor select(){
        String h = "SELECT SUM("+DatabaseHelper.PRICE+") FROM "+ DatabaseHelper.TABLE_NAME +" WHERE CATEGORY LIKE 'Vegetables'";
        Cursor cursor = database.rawQuery(h,null);
        if ((cursor != null)){
            cursor.moveToFirst();

        }
        return cursor;

    }
    public   Cursor setlist(){

        String h1 = "SELECT * FROM EXPENSES WHERE CATEGORY LIKE 'Vegetables'";
        Cursor cursor3 = database.rawQuery(h1,null);
        if ((cursor3 != null)){
            cursor3.moveToFirst();
        }
        return cursor3;
    }
    public int update(int _id, String item,String category,int unit,int price) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper._ID, _id);//
        contentValues.put(DatabaseHelper.ITEM, item);
        contentValues.put(DatabaseHelper.CATEGORY, category);
        contentValues.put(DatabaseHelper.UNIT, unit);
        contentValues.put(DatabaseHelper.PRICE, price);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(int _id,int count) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
        Log.v("before if delete", String.valueOf(_id));
        if (_id!=count) {
            Log.d("inside if",String.valueOf(count));

            for (int i = _id; i <= count; i++) {
                Log.v("inside for ",String.valueOf(i));
                int idno = i+1;
                int newid = idno-1;
                Log.v(String.valueOf(idno),String.valueOf(newid));
                //  UPDATE EXPENSES SET _id = 'newid' WHERE _id = idno;
                database.execSQL("UPDATE " + DatabaseHelper.TABLE_NAME + " SET _id='" + newid +"' WHERE _id="+idno);//replace idno to newid
                Log.v("Data Updated ",String.valueOf(i));
            }
        }
    }
    public Cursor select2(){
        String h = "SELECT SUM("+DatabaseHelper.PRICE+") FROM "+ DatabaseHelper.TABLE_NAME +" WHERE CATEGORY LIKE 'Fruits'";
        Cursor cursor = database.rawQuery(h,null);
        if ((cursor != null)){
            cursor.moveToFirst();

        }
        return cursor;

    }
    public   Cursor setlist2(){

        String h1 = "SELECT * FROM EXPENSES WHERE CATEGORY LIKE 'Fruits'";
        Cursor cursor3 = database.rawQuery(h1,null);
        if ((cursor3 != null)){
            cursor3.moveToFirst();
        }
        return cursor3;
    }
    public Cursor select3(){
        String h = "SELECT SUM("+DatabaseHelper.PRICE+") FROM "+ DatabaseHelper.TABLE_NAME +" WHERE CATEGORY LIKE 'Transport'";
        Cursor cursor = database.rawQuery(h,null);
        if ((cursor != null)){
            cursor.moveToFirst();

        }
        return cursor;

    }
    public   Cursor setlist3(){

        String h1 = "SELECT * FROM EXPENSES WHERE CATEGORY LIKE 'Transport'";
        Cursor cursor3 = database.rawQuery(h1,null);
        if ((cursor3 != null)){
            cursor3.moveToFirst();
        }
        return cursor3;
    }
    public Cursor select4(){
        String h = "SELECT SUM("+DatabaseHelper.PRICE+") FROM "+ DatabaseHelper.TABLE_NAME +" WHERE CATEGORY LIKE 'Other'";
        Cursor cursor = database.rawQuery(h,null);
        if ((cursor != null)){
            cursor.moveToFirst();

        }
        return cursor;

    }
    public   Cursor setlist4(){
        String h1 = "SELECT * FROM EXPENSES WHERE CATEGORY LIKE 'Other'";
        Cursor cursor3 = database.rawQuery(h1,null);
        if ((cursor3 != null)){
            cursor3.moveToFirst();
        }
        return cursor3;
    }
}
