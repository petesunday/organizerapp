package com.example.bartek.myapplication.Model;

public class Bus {

    public int idlinia_autobusowa;
    public String przystanek_poczatkowy, przystanek_koncowy;

    public Bus(int idlinia_autobusowa, String przystanek_poczatkowy, String przystanek_koncowy) {
        this.idlinia_autobusowa = idlinia_autobusowa;
        this.przystanek_poczatkowy = przystanek_poczatkowy;
        this.przystanek_koncowy = przystanek_koncowy;
    }

    public Bus() {
    }

    public int getIdlinia_autobusowa() {
        return idlinia_autobusowa;
    }

    public void setIdlinia_autobusowa(int idlinia_autobusowa) {
        this.idlinia_autobusowa = idlinia_autobusowa;
    }

    public String getPrzystanek_poczatkowy() {
        return przystanek_poczatkowy;
    }

    public void setPrzystanek_poczatkowy(String przystanek_poczatkowy) {
        this.przystanek_poczatkowy = przystanek_poczatkowy;
    }

    public String getPrzystanek_koncowy() {
        return przystanek_koncowy;
    }

    public void setPrzystanek_koncowy(String przystanek_koncowy) {
        this.przystanek_koncowy = przystanek_koncowy;
    }
}
