package com.example.bartek.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bartek.myapplication.Adapter.BusAdapter;
import com.example.bartek.myapplication.Database.Database;

import java.util.ArrayList;
import java.util.List;

public class BusStop extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    BusAdapter adapter;
    TextView nazwa_przystanku;
    List<String> suggestList = new ArrayList<>();
    Database database;
    private BottomNavigationView mBottomNav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busstop);

        Bundle bundle = getIntent().getExtras();
        nazwa_przystanku = (TextView) findViewById(R.id.nazwa_przystanku);
        nazwa_przystanku.setText(bundle.getString("nazwa"));

        recyclerView = (RecyclerView) findViewById(R.id.recycler_bus);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        database = new Database(this);
        adapter = new BusAdapter(database.getRozklad(nazwa_przystanku.getText().toString()),
                this);
        recyclerView.setAdapter(adapter);

        mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
