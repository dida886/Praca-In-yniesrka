package com.example.dmain.pracainynierska;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.CardView;

import android.view.View;
import android.view.Window;

public class ChooseTableActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView eko, usterka, hist;

    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().setTitle("Wybór zgłoszenia");
        //getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_choose_table);


        eko= (CardView)findViewById(R.id.ListaEkologia);
        usterka = (CardView)findViewById(R.id.ListaUsterka);


        eko.setOnClickListener(this);
        usterka.setOnClickListener(this);


    }
    public void onClick(View v) {

        Intent i;

        switch (v.getId()) {

            case R.id.ListaEkologia: i = new Intent(this, Isue_Ekologia.class);startActivity(i); break;
            case R.id.ListaUsterka: i = new Intent(this, Isue_Usterka.class);startActivity(i); break;

            default: break;

        }
    }
}


