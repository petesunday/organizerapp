package com.example.bartek.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bartek.myapplication.Adapter.DayAdapter;
import com.example.bartek.myapplication.Database.Database;

public class DayActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNav;
    private DayAdapter adapter;
    private TextView data;
    private Button addNote;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        data = (TextView) findViewById(R.id.day_data);

        Bundle bundle = getIntent().getExtras();
        data.setText(bundle.getString("data"));

        recyclerView = (RecyclerView) findViewById(R.id.recycler_day_data);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        database = new Database(this);

        adapter = new DayAdapter(database.getNotatka(data.getText().toString()), this);
        recyclerView.setAdapter(adapter);

        addNote = (Button) findViewById(R.id.dodaj_notatke);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dayToNote = new Intent(DayActivity.this, NoteActivity.class);
                dayToNote.putExtra("dzien", data.getText().toString());
                startActivity(dayToNote);
            }
        });

        mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });
    }

    private void selectFragment(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_cal:
                Intent intent = new Intent(this, CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_bus:
                Intent intent1 = new Intent(this, BusActivity.class);
                startActivity(intent1);
                break;
            case R.id.menu_events:
                Intent intent2 = new Intent(this, EventActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
