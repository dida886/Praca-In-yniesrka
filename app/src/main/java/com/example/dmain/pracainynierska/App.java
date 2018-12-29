package com.example.dmain.pracainynierska;

import android.app.Application;
import android.content.Context;

import com.example.dmain.pracainynierska.DataBase.DBHelper;
import com.example.dmain.pracainynierska.DataBase.DatabaseManager;
import com.example.dmain.pracainynierska.DataBase.Tabels.EkologiaTable;
import com.example.dmain.pracainynierska.DataBase.Tabels.UsterkaTable;


public class App extends Application {
    private static Context context;
    private static DBHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DBHelper(context);
        DatabaseManager.initializeInstance(dbHelper);

        if (EkologiaTable.getAll().size() == 0){
            EkologiaTable.insertPredefinedData();
        }
        if (UsterkaTable.getAll().size() == 0){
            UsterkaTable.insertPredefinedData();
        }
    }

    public static Context getContext() {
        return context;
    }

}