package com.example.dmain.pracainynierska;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dmain.pracainynierska.Adapters.EkologiaAdapter;
import com.example.dmain.pracainynierska.DataBase.Models.EkologiaModels;
import com.example.dmain.pracainynierska.DataBase.Tabels.EkologiaTable;

import java.util.ArrayList;

public class Isue_Ekologia extends AppCompatActivity {
    EkologiaAdapter mainAdapter;
    ListView mListView;
    EkologiaModels p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ekologia);
        getSupportActionBar().setTitle("Lista zgłoszeń ");
        mListView = findViewById(R.id.datelist);
        final ArrayList<EkologiaModels> places = EkologiaTable.getAll();
        mainAdapter = new EkologiaAdapter(getApplicationContext(),places);

        if (places.size() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();


        } else {
            mListView.setAdapter(mainAdapter);

        }


    }




}
