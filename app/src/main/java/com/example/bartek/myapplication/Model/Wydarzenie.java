package com.example.bartek.myapplication.Model;

public class Wydarzenie {
    public int idwydarzenie;
    public String nazwa, data, miejsce, typ_wydarzenia;

    public Wydarzenie(int idwydarzenie, String nazwa, String data, String miejsce,
                      String typ_wydarzenia) {
        this.idwydarzenie = idwydarzenie;
        this.nazwa = nazwa;
        this.data = data;
        this.miejsce = miejsce;
        this.typ_wydarzenia = typ_wydarzenia;
    }

    public Wydarzenie() {
    }

    public int getIdwydarzenie() {
        return idwydarzenie;
    }

    public void setIdwydarzenie(int idwydarzenie) {
        this.idwydarzenie = idwydarzenie;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(String miejsce) {
        this.miejsce = miejsce;
    }

    public String getTyp_wydarzenia() {
        return typ_wydarzenia;
    }

    public void setTyp_wydarzenia(String typ_wydarzenia) {
        this.typ_wydarzenia = typ_wydarzenia;
    }
}

