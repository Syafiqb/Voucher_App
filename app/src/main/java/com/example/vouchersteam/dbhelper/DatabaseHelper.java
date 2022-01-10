package com.example.vouchersteam.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    DatabaseHelper db;
    public static final String DATABASE_NAME = "VoucherApp.DB";
    public static final String TABLE__NAME = "User";
    public static final String COL_1 = "ID";
    public static final String COL_3 = "email";
    public static final String COL_4 = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE__NAME + "("
                + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_3 + " TEXT,"+ COL_4 +" TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int
            newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE__NAME);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_3, email);
        contentValues.put(COL_4, password);
        long res = db.insert(TABLE__NAME, null, contentValues);
        db.close();
        return res;
    }

    public boolean checkuser(String email, String password) {
        String[] columns = { COL_1} ;
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_3 + "=?" + " and " + COL_4 + "=?";
        String[] selectionArgs = { email, password };
        Cursor cursor = db.query(TABLE__NAME, columns, selection, selectionArgs,
                null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count>0)
            return true;
        else
            return false;
    }

}


