package com.example.dmain.pracainynierska;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dmain.pracainynierska.Adapters.HistoryListAdapter;
import com.example.dmain.pracainynierska.DataBase.Models.ListPlaces;
import com.example.dmain.pracainynierska.DataBase.Tabels.PlacesTable;

import java.util.ArrayList;

public class Isue_History extends AppCompatActivity {
    HistoryListAdapter mainAdapter;
    ListView mListView;
    ListPlaces p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isue__history);
        mListView = findViewById(R.id.datelist);
        final ArrayList<ListPlaces> places = PlacesTable.getAll();
        mainAdapter = new HistoryListAdapter(getApplicationContext(),places);

        if (places.size() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();


        } else {
            mListView.setAdapter(mainAdapter);

        }


    }




}
