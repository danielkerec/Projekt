package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prikaz_restavracij extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    RecyclerView rcv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_restavracij);

        ArrayList<String> restavracije = new ArrayList<>();
        restavracije.add("Nona");
        restavracije.add("Ancora");

        RecyclerView recyclerView = findViewById(R.id.recycler_prikaz_restavracij);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, restavracije);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position){

            Intent intent = new Intent(getBaseContext(), Brisanje_restavracij.class);
            intent.putExtra("EXTRA_NAME", adapter.getItem(position));
            startActivity(intent);
    }

}
