package com.example.dmain.pracainynierska.DataBase.Models;


import android.icu.text.SimpleDateFormat;

/**
 * Created by dmain on 28.03.2018.
 */

public class ListPlaces {

    private String Title;
    private long Data;
    private String Adres;
    private String Opis;
    private int Image;
    private int Id;


    public ListPlaces(int id, String title, long data, String adres, String opis, int image) {
        this.Title = title;
        this.Id = id;
        this.Data = data;
        this.Adres =adres;
        this.Opis = opis;
        this.Image = image;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public long getData() {
        return Data;
    }

    public void setData(long data) {
        Data = data;
    }

    public String getAdres() {
        return Adres;
    }

    public void setAdres(String adres) {
        Adres = adres;
    }

    public String getOpis() {
        return Opis;
    }

    public void setOpis(String opis) {
        Opis = opis;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String toString() {


        return String.format(String.valueOf(this.Title));
    }
    public String getMyDate(){

        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        return String.format(formatter.format(this.Data));

    }
}




