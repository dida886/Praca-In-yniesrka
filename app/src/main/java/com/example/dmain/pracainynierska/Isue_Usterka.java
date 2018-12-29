package com.example.dmain.pracainynierska;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dmain.pracainynierska.Adapters.EkologiaAdapter;
import com.example.dmain.pracainynierska.Adapters.UsterkaAdapter;
import com.example.dmain.pracainynierska.DataBase.Models.EkologiaModels;
import com.example.dmain.pracainynierska.DataBase.Models.UsterkaModels;
import com.example.dmain.pracainynierska.DataBase.Tabels.EkologiaTable;
import com.example.dmain.pracainynierska.DataBase.Tabels.UsterkaTable;

import java.util.ArrayList;

public class Isue_Usterka extends AppCompatActivity {
    UsterkaAdapter mainAdapter;
    ListView mListView;
    UsterkaModels p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usterka);
        getSupportActionBar().setTitle("Lista zgłoszeń ");
        mListView = findViewById(R.id.datelist);
        final ArrayList<UsterkaModels> places = UsterkaTable.getAll();
        mainAdapter = new UsterkaAdapter(getApplicationContext(),places);

        if (places.size() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();


        } else {
            mListView.setAdapter(mainAdapter);

        }


    }




}
