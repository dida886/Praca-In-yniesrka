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

import static java.lang.Integer.valueOf;


public class HistoryListAdapter extends BaseAdapter {

    Context mContex;

   List<ListPlaces> eProduct;

    public HistoryListAdapter(Context mContex,  List<ListPlaces> eProduct) {
        this.mContex = mContex;
        this.eProduct = eProduct;

    }


    @Override
    public int getCount() {
        return eProduct.size();
    }

    @Override
    public Object getItem(int i) {
        return eProduct.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {


        View v = View.inflate(mContex, R.layout.history_adapters, null);
        TextView tvTitle = (TextView)v.findViewById(R.id.tytul_adapter);
        TextView tvData = (TextView)v.findViewById(R.id.data_adapter);
        TextView tvAdres = (TextView)v.findViewById(R.id.adres_adapter);
        TextView tvOpis = (TextView)v.findViewById(R.id.OPISTV);

        ImageView tvImage1= v.findViewById(R.id.imageView2);

        //Set text for TextView
        tvTitle.setText(String.valueOf(eProduct.get(i).getTitle()));
        tvData.setText(String.valueOf(eProduct.get(i).getMyDate()));
        tvAdres.setText(String.valueOf("Adres: "+eProduct.get(i).getAdres()));
        tvOpis.setText(String.valueOf(eProduct.get(i).getOpis()));

        tvImage1.setImageResource(valueOf(eProduct.get(i).getImage()));


        //Save product id to tag
        v.setTag(String.valueOf(eProduct.get(i).getId()));
        return v;
    }
}