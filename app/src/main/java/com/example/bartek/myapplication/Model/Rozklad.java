package com.example.bartek.myapplication.Model;

public class Rozklad extends Bus {

    public String godzina_odjazdu, idprzystanek;

    public Rozklad(int idlinia_autobusowa, String przystanek_poczatkowy, String przystanek_koncowy,
                   String godzina_odjazdu, String idprzystanek) {
        super(idlinia_autobusowa, przystanek_poczatkowy, przystanek_koncowy);
        this.godzina_odjazdu = godzina_odjazdu;
        this.idprzystanek = idprzystanek;
    }

    public Rozklad(String godzina_odjazdu, String idprzystanek) {
        this.godzina_odjazdu = godzina_odjazdu;
        this.idprzystanek = idprzystanek;
    }

    public Rozklad(){
    }

    public String getGodzina_odjazdu() {
        return godzina_odjazdu;
    }

    public void setGodzina_odjazdu(String godzina_odjazdu) {
        this.godzina_odjazdu = godzina_odjazdu;
    }

    public String getIdprzystanek() {
        return idprzystanek;
    }

    public void setIdprzystanek(String idprzystanek) {
        this.idprzystanek = idprzystanek;
    }
}
