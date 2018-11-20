package com.example.dmain.pracainynierska.Adapters;

import android.graphics.Bitmap;
import android.widget.BaseAdapter;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.dmain.pracainynierska.DataBase.Models.ListPlaces;
import com.example.dmain.pracainynierska.R;

import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;

import static java.lang.Integer.valueOf;

public class HistoryListAdapter extends BaseAdapter {

    private Context mContex;

    private List<ListPlaces> eProduct;
    int [] Colors;
    int[] Icon;


    public HistoryListAdapter(Context mContex,  List<ListPlaces> eProduct, int[] icon) {
        this.mContex = mContex;

        this.Icon= icon;
        /*this.Colors=Colors;*/
        this.eProduct = eProduct;

    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View v = View.inflate(mContex, R.layout.history_adapter, null);
        TextView tvTitle = (TextView)v.findViewById(R.id.tytul_adapter);
        TextView tvData = (TextView)v.findViewById(R.id.data_adapter);
        TextView tvAdres = (TextView)v.findViewById(R.id.adres_adapter);


        ImageView tvImage1= v.findViewById(R.id.imageView2);

        //Set text for TextView
        tvTitle.setText(String.valueOf(eProduct.get(position).getTitle()));
        tvData.setText(String.valueOf(eProduct.get(position).getData()));
        tvAdres.setText(String.valueOf(eProduct.get(position).getAdres()));

        //tvImage1.setImageResource(Bitmap.valueOf(eProduct.get(position).getImage()));


        //Save product id to tag
        v.setTag(String.valueOf(eProduct.get(position).getId()));
        return v;
    }
}