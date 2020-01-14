package com.example.bartek.myapplication.Model;

public class Przystanek {
    public int idprzysanek, idlinia_autobusowa;
    public String nazwa_przystanku;

    public Przystanek(int idprzysanek, int idlinia_autobusowa, String nazwa_przystanku) {
        this.idprzysanek = idprzysanek;
        this.idlinia_autobusowa = idlinia_autobusowa;
        this.nazwa_przystanku = nazwa_przystanku;
    }

    public Przystanek() {
    }

    public int getIdprzysanek() {
        return idprzysanek;
    }

    public void setIdprzysanek(int idprzysanek) {
        this.idprzysanek = idprzysanek;
    }

    public int getIdlinia_autobusowa() {
        return idlinia_autobusowa;
    }

    public void setIdlinia_autobusowa(int idlinia_autobusowa) {
        this.idlinia_autobusowa = idlinia_autobusowa;
    }

    public String getNazwa_przystanku() {
        return nazwa_przystanku;
    }

    public void setNazwa_przystanku(String nazwa_przystanku) {
        this.nazwa_przystanku = nazwa_przystanku;
    }
}
