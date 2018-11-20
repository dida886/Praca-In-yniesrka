package com.example.dmain.pracainynierska.DataBase.Models;

import android.graphics.Bitmap;

/**
 * Created by dmain on 28.03.2018.
 */

public class ListPlaces {

    private String Title;
    private String Data;
    private String Adres;
    private String Opis;
    private Byte Image;
    private int Id;


    public ListPlaces(int id, String title, String data, String adres, String opis, Byte image) {
        this.Title = title;
        this.Id = id;
        this.Data = data;
        this.Adres =adres;
        this.Opis = opis;
        this.Image = image;
    }

    public Byte getImage() {
        return Image;
    }

    public void setImage(Byte image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
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
}




