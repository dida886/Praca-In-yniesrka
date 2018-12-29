package com.example.dmain.pracainynierska.DataBase.Tabels;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dmain.pracainynierska.DataBase.DatabaseManager;
import com.example.dmain.pracainynierska.DataBase.Models.EkologiaModels;
import com.example.dmain.pracainynierska.DataBase.Models.UsterkaModels;
import com.example.dmain.pracainynierska.R;

import java.util.ArrayList;

/**
 * Created by dmain on 21.03.2018.
 */

public class UsterkaTable {

    public static final String TABLE_NAME = "USTERKA_TABLE";

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
        UsterkaTable.insert(new UsterkaModels(1, "Zniszczony przystanek autobusowy", 16122018,"Wrocławska 60, Olesno","Zniszczony przystanek autobusowy, nie ma gdzie usiąść",R.drawable.przystanek));

    }



    public static int insert(UsterkaModels le) {
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

    public static ArrayList<UsterkaModels> getAll() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<UsterkaModels> result = new ArrayList<>();

        if (cursor.getCount() == 0) {
            return result;
        }

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(UsterkaTable.ID_COL));
            String Title = cursor.getString(cursor.getColumnIndex(UsterkaTable.TITLE_COL));
            String Adres = cursor.getString(cursor.getColumnIndex(UsterkaTable.ADRES_COL));
            long Data = cursor.getLong(cursor.getColumnIndex(UsterkaTable.DATA_COL));
            String Opiss = cursor.getString(cursor.getColumnIndex(UsterkaTable.OPIS_COL));
            int image = cursor.getInt(cursor.getColumnIndex(UsterkaTable.IMAGE_COL));





            result.add(new UsterkaModels(id,Title,Data,Adres,Opiss,image));
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

