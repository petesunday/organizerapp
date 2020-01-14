package com.example.bartek.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.example.bartek.myapplication.Model.Bus;
import com.example.bartek.myapplication.Model.Notatka;
import com.example.bartek.myapplication.Model.Przystanek;
import com.example.bartek.myapplication.Model.Rozklad;
import com.example.bartek.myapplication.Model.Wydarzenie;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "database.db";
    private static final int DB_VER = 1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }

    public List<Przystanek> getPrzystanek() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"idprzystanek", "nazwa_przystanku", "idlinia_autobusowa"};
        String tableName = "przystanek";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null,
                null, null);
        List<Przystanek> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Przystanek przystanek = new Przystanek();
                przystanek.setIdprzysanek(cursor.getInt(cursor.getColumnIndex("idprzystanek")));
                przystanek.setIdlinia_autobusowa(cursor.getInt(cursor
                        .getColumnIndex("idlinia_autobusowa")));
                przystanek.setNazwa_przystanku(cursor.getString(cursor
                        .getColumnIndex("nazwa_przystanku")));

                result.add(przystanek);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public List<String> getPrzystanekNames() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"nazwa_przystanku"};
        String tableName = "przystanek";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null,
                null, null);
        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                result.add(cursor.getString(cursor.getColumnIndex("nazwa_przystanku")));
            } while (cursor.moveToNext());
        }
        return result;
    }

    public List<Przystanek> getPrzystanekByName(String name) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"idprzystanek", "nazwa_przystanku", "idlinia_autobusowa"};
        String tableName = "przystanek";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, "nazwa_przystanku LIKE ?",
                new String[]{"%" + name + "%"}, null, null, null);
        List<Przystanek> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Przystanek przystanek = new Przystanek();
                przystanek.setIdprzysanek(cursor.getInt(cursor.getColumnIndex("idprzystanek")));
                przystanek.setIdlinia_autobusowa(cursor.getInt(cursor
                        .getColumnIndex("idlinia_autobusowa")));
                przystanek.setNazwa_przystanku(cursor.getString(cursor
                        .getColumnIndex("nazwa_przystanku")));

                result.add(przystanek);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public String getPrzystanekById(String id, String przystanek) {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String tablePrzystanek = "przystanek";
        String tableBus = "linia_autobusowa";

        qb.setTables(tablePrzystanek + " JOIN " + tableBus + " ON " + tablePrzystanek
                + ".idprzystanek=" + tableBus + "." + przystanek);
        String[] sqlSelect = {tablePrzystanek + ".nazwa_przystanku"};


        Cursor cursor = qb.query(db, sqlSelect, tablePrzystanek + ".idprzystanek=" + id,
                null, null, null, null);
        cursor.moveToFirst();
        String result = cursor.getString(cursor.getColumnIndex("nazwa_przystanku"));

        return result;
    }

    public List<Rozklad> getRozklad(String przystanek) {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();


        String[] sqlSelect = {"rozklad_jazdy.godzina_odjazdu", "rozklad_jazdy.idlinia_autobusowa",
                "linia_autobusowa.przystanek_poczatkowy", "linia_autobusowa.przystanek_koncowy"};
        String tableName = "rozklad_jazdy JOIN linia_autobusowa ON rozklad_jazdy" +
                ".idlinia_autobusowa=linia_autobusowa.idlinia_autobusowa JOIN przystanek " +
                "ON rozklad_jazdy.idprzystanek=przystanek.idprzystanek";
        String where = "przystanek.nazwa_przystanku='" + przystanek +
                "' AND rozklad_jazdy.godzina_odjazdu>='" + getCurrentTimeUsingCalendar() + "'";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, where, null,
                "rozklad_jazdy.godzina_odjazdu", null, null);
        List<Rozklad> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Rozklad rozklad = new Rozklad();

                rozklad.setIdlinia_autobusowa(cursor.getInt(cursor
                        .getColumnIndex("idlinia_autobusowa")));
                rozklad.setPrzystanek_poczatkowy(getPrzystanekById(cursor
                        .getString(cursor.getColumnIndex("przystanek_poczatkowy")),
                        "przystanek_poczatkowy"));
                rozklad.setPrzystanek_koncowy(getPrzystanekById(cursor
                        .getString(cursor.getColumnIndex("przystanek_koncowy")),
                        "przystanek_koncowy"));
                rozklad.setGodzina_odjazdu(cursor.getString(cursor
                        .getColumnIndex("godzina_odjazdu")));

                result.add(rozklad);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public List<Wydarzenie> getEvent(String typ) {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"idwydarzenie", "nazwa", "data", "miejsce", "typ_wydarzenia"};
        String tableName = "wydarzenie";
        String where = "typ_wydarzenia LIKE '" + typ + "'";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, where, null, "data",
                null, null);
        List<Wydarzenie> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Wydarzenie wydarzenie = new Wydarzenie();

                wydarzenie.setIdwydarzenie(cursor.getInt(cursor.getColumnIndex("idwydarzenie")));
                wydarzenie.setNazwa(cursor.getString(cursor.getColumnIndex("nazwa")));
                wydarzenie.setData(cursor.getString(cursor.getColumnIndex("data")));
                wydarzenie.setMiejsce(cursor.getString(cursor.getColumnIndex("miejsce")));
                wydarzenie.setTyp_wydarzenia(cursor.getString(cursor
                        .getColumnIndex("typ_wydarzenia")));

                result.add(wydarzenie);
            } while (cursor.moveToNext());
        }
        return result;

    }

    public boolean setNotatkaEvent(String data, String text) {

        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db1 = getReadableDatabase();

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables("notatka");
        Cursor cursor = qb.query(db1, new String[]{"idnotatka"}, null, null,
                null, null, null);
        int id = 0;
        if (cursor.moveToLast()) {
            id = cursor.getInt(cursor.getColumnIndex("idnotatka")) + 1;
        }

        String[] splited = data.split("\\s+");

        String notatka = text + " godz. " + splited[1];

        ContentValues cv = new ContentValues();
        cv.put("idnotatka", id);
        cv.put("data", splited[0]);
        cv.put("text", notatka);
        cv.put("godzina", splited[1]);


        long result = db.insert("notatka", null, cv);
        db.close();

        if (result == -1) {
            return false;
        } else return true;

    }

    public List<Notatka> getNotatka(String dzien) {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"idnotatka", "data", "text", "godzina"};
        String tableName = "notatka";

        String where = "data='" + dzien + "'";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, where, null, "godzina",
                null, null);
        List<Notatka> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Notatka notatka = new Notatka();

                notatka.setIdnotatka(cursor.getInt(cursor.getColumnIndex("idnotatka")));
                notatka.setData(cursor.getString(cursor.getColumnIndex("data")));
                notatka.setText(cursor.getString(cursor.getColumnIndex("text")));
                notatka.setGodzina(cursor.getString(cursor.getColumnIndex("godzina")));

                result.add(notatka);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public String getCurrentTimeUsingCalendar() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }
}
