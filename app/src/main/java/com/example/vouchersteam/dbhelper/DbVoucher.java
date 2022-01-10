package com.example.vouchersteam.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbVoucher extends SQLiteOpenHelper {
    SQLiteDatabase db = this.getWritableDatabase();
    public static final String DATABASE_NAME = "voucher.DB";
    public static final String TABLE__NAME = "tbl";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "nama";
    public static final String COL_3 = "brand";
    public static final String COL_4 = "price";

    public DbVoucher(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE__NAME + "("
                + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT NOT NULL, "
                + COL_3 + " TEXT," + COL_4 + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int
            newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE__NAME);
        onCreate(sqLiteDatabase);
    }
}
