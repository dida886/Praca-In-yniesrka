package com.example.dmain.pracainynierska.DataBase;

import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

import com.example.dmain.pracainynierska.DataBase.Tabels.EkologiaTable;
import com.example.dmain.pracainynierska.DataBase.Tabels.UsterkaTable;


public class DBHelper  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 9;
    private static final String DATABASE_NAME = "PLACES.db";
    private static final String TAG = DBHelper.class.getSimpleName();

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EkologiaTable.createTable());
        db.execSQL(UsterkaTable.createTable());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        db.execSQL("DROP TABLE IF EXISTS " + EkologiaTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UsterkaTable.TABLE_NAME);

        onCreate(db);
    }

}
