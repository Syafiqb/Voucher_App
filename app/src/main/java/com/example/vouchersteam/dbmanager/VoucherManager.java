package com.example.vouchersteam.dbmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.vouchersteam.dbhelper.DbVoucher;

public class VoucherManager {
    private DbVoucher dbHelper;
    private Context context;
    private SQLiteDatabase database;
    public VoucherManager(Context c) {
        context = c;
    }
    public VoucherManager open() throws SQLException {
        dbHelper = new DbVoucher(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }
    // Query insert data
    public void insert(String name, String brand, String price) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DbVoucher.COL_2, name);
        contentValue.put(DbVoucher.COL_3, brand);
        contentValue.put(DbVoucher.COL_4, price);
        database.insert(DbVoucher.TABLE__NAME, null, contentValue);
    }
    // Query ambil/read data
    public Cursor fetch() {
        String[] columns = new String[] {DbVoucher.COL_1, DbVoucher.COL_2,
                DbVoucher.COL_3, DbVoucher.COL_4 };
        Cursor cursor = database.query(DbVoucher.TABLE__NAME, columns, null,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    // Query update data
    public int update(long ID, String namers, String loc, String stat) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbVoucher.COL_1, ID);
        contentValues.put(DbVoucher.COL_2, namers);
        contentValues.put(DbVoucher.COL_3, loc);
        contentValues.put(DbVoucher.COL_4, stat);
        int i = database.update(DbVoucher.TABLE__NAME, contentValues,
                DbVoucher.COL_1 + " = " + ID, null);
        return i;
    }
    // Query delete data
    public void delete(long ID) {
        database.delete(DbVoucher.TABLE__NAME, DbVoucher.COL_1 + "=" + ID,
                null);
    }
}
