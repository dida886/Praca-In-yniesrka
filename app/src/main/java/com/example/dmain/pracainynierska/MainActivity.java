package com.example.dmain.pracainynierska;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    private CardView histcard, bmicard,addexcard, mapcard, myresult;

    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);






       /* // Deklaracja Card

        histcard = (CardView) findViewById(R.id.histcardview);
        bmicard = (CardView)findViewById(R.id.bmicardview);
        addexcard = (CardView)findViewById(R.id.addcardview);
        mapcard = findViewById(R.id.mapcardviev);
        myresult = findViewById(R.id.my_result_card);

        // Dodawanie activitów do card

        histcard.setOnClickListener(this);
        bmicard.setOnClickListener(this);
        addexcard.setOnClickListener(this);
        mapcard.setOnClickListener(this);
        myresult.setOnClickListener(this);

*/

    }



    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }








   /* @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()) {
            case R.id.histcardview: i = new Intent(this, HistoryActivity.class);startActivity(i); break;
            case R.id.bmicardview: i = new Intent(this, Calculator_BMI_Activity.class);startActivity(i); break;
            case R.id.addcardview: i = new Intent(this, StoperwatchActivity.class);startActivity(i); break;
            case R.id.mapcardviev: i = new Intent(this, Map2Activity.class);startActivity(i); break;
            case R.id.my_result_card: i = new Intent(this, MyResult_Activity.class);startActivity(i); break;

            default: break;

        }*/
    }

