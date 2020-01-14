package com.example.bartek.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bartek.myapplication.Database.Database;

import java.util.regex.Pattern;

public class NoteActivity extends AppCompatActivity {

    EditText notka;
    EditText godzina;
    Button save_note;
    String dzien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        notka = (EditText) findViewById(R.id.input_note);
        godzina = (EditText) findViewById(R.id.godzina_note);
        save_note = (Button) findViewById(R.id.zapisz_note);
        Bundle bundle = getIntent().getExtras();
        dzien = bundle.getString("dzien");

        final Database database = new Database(this);
        final Pattern timePattern = Pattern.compile("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");

        notka.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    return;
                }
                if (notka.getText().toString().isEmpty()) {
                    Toast.makeText(NoteActivity.this, "Notatka nie może być pusta",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        godzina.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    return;
                }
                if (notka.getText().toString().isEmpty()) {
                    Toast.makeText(NoteActivity.this, "Pole godziny nie moze byc puste",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        save_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (notka.getText().toString().trim().length() == 0 || !godzina.getText()
                        .toString().matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]")) {

                    Toast.makeText(NoteActivity.this,
                            "Puste pole, bądź zły format godziny", Toast.LENGTH_SHORT).show();

                }

                if (notka.getText().toString().trim().length() > 0 && godzina.getText()
                        .toString().matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]")) {

                    Toast.makeText(NoteActivity.this, "Wszystko ok",
                            Toast.LENGTH_SHORT).show();

                    String data = dzien + " " + godzina.getText().toString();
                    boolean insertData = database.setNotatkaEvent(data, notka.getText().toString());

                    finish();
                }
            }
        });
    }
}
