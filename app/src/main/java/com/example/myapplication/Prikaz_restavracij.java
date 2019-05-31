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

public class Prikaz_restavracij extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_restavracij);

        final ArrayList<Restavracija_item> restavracije = new ArrayList<>();
        restavracije.add(new Restavracija_item("Perunika", "Kranjčeva ulica 14a, 9226 Moravske Toplice"));
        restavracije.add(new Restavracija_item("Nona", "MB"));

        mRecyclerView = findViewById(R.id.recycler_prikaz_restavracij);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MyRecyclerViewAdapter(restavracije);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        mAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getBaseContext(), Brisanje_restavracij.class);
                String name = restavracije.get(position).getIme();
                String naslov = restavracije.get(position).getNaslov();
                intent.putExtra("EXTRA_NAME", name);
                intent.putExtra("EXTRA_NASLOV", naslov);
                startActivity(intent);

            }

            @Override
            public void onDeleteClick(int position) {
                Toast.makeText(Prikaz_restavracij.this, "deleted", Toast.LENGTH_LONG).show();
            }
        });




        /*
        RecyclerView recyclerView = findViewById(R.id.recycler_prikaz_restavracij);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, restavracije);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        */
    }
/*
    @Override
    public void onItemClick(View view, int position){

            Intent intent = new Intent(getBaseContext(), Brisanje_restavracij.class);
            intent.putExtra("EXTRA_NAME", mAapter.getItem(position));
            startActivity(intent);
    }
*/


}
