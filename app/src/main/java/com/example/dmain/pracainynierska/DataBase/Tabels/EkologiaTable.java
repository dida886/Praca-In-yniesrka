package com.example.dmain.pracainynierska.DataBase.Tabels;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dmain.pracainynierska.R;
import com.example.dmain.pracainynierska.DataBase.DatabaseManager;
import com.example.dmain.pracainynierska.DataBase.Models.EkologiaModels;

import java.util.ArrayList;

/**
 * Created by dmain on 21.03.2018.
 */

public class EkologiaTable {

    public static final String TABLE_NAME = "EKOLOGIA_TABLE";

    private static final String ID_COL = "ID";
    private static final String TITLE_COL = "TITLE";
    private static final String DATA_COL = "DATA";
    private static final String ADRES_COL = "ADRES";
    private static final String OPIS_COL = "OPIS";
    private static final String IMAGE_COL = "IMAGE";

    public static String createTable() {
        return "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COL + " TEXT,"
                + DATA_COL + " TEXT,"
                + ADRES_COL + " TEXT,"
                + OPIS_COL + " TEXT,"
                + IMAGE_COL + " INT" +
                ")";
    }
    public static void insertPredefinedData(){
        EkologiaTable.insert(new EkologiaModels(1, "Szkodliwy dym z komina", 12122018,"Młyńska 56, Olesno","U sąsiada wydobywa się szkodliwy dym, Nie można oddychać",R.drawable.dym));

    }



    public static int insert(EkologiaModels le) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE_COL, le.getTitle());
        contentValues.put(ADRES_COL, le.getAdres());
        contentValues.put(OPIS_COL, le.getOpis());
        contentValues.put(DATA_COL, le.getData());
        contentValues.put(IMAGE_COL, le.getImage());

        int id = (int)db.insert(TABLE_NAME, null, contentValues);
        DatabaseManager.getInstance().closeDatabase();

        return id;
    }

    public static ArrayList<EkologiaModels> getAll() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<EkologiaModels> result = new ArrayList<>();

        if (cursor.getCount() == 0) {
            return result;
        }

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EkologiaTable.ID_COL));
            String Title = cursor.getString(cursor.getColumnIndex(EkologiaTable.TITLE_COL));
            String Adres = cursor.getString(cursor.getColumnIndex(EkologiaTable.ADRES_COL));
            int Data = cursor.getInt(cursor.getColumnIndex(EkologiaTable.DATA_COL));
            String Opiss = cursor.getString(cursor.getColumnIndex(EkologiaTable.OPIS_COL));
            int image = cursor.getInt(cursor.getColumnIndex(EkologiaTable.IMAGE_COL));





            result.add(new EkologiaModels(id,Title,Data,Adres,Opiss,image));
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return result;
    }
    public static int deleteItem(int position){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(TABLE_NAME,ID_COL+"="+String.valueOf(position),null);

        //Cursor cursor = db.rawQuery("DELETE FROM " + TABLE_NAME + " WHERE " + ID_COL + " = " + position, null);



        //cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return position;



    }

}

