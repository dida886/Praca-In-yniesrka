package com.example.dmain.pracainynierska;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.CardView;

import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView map1, map2, hist;

    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);


        map1= (CardView)findViewById(R.id.mapcardviev1);
        map2 = (CardView)findViewById(R.id.mapcardviev2);
        hist = (CardView)findViewById(R.id.histcard);

        map1.setOnClickListener(this);
        map2.setOnClickListener(this);
        hist.setOnClickListener(this);

    }
    public void onClick(View v) {

        Intent i;

        switch (v.getId()) {

            case R.id.mapcardviev1: i = new Intent(this, MapsActivityEkologia.class);startActivity(i); break;
            case R.id.mapcardviev2: i = new Intent(this, MapsActivityUsterka.class);startActivity(i); break;
            case R.id.histcard: i = new Intent(this, ChooseTableActivity.class);startActivity(i); break;

            default: break;

        }
    }
}


