package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;

public class Prikaz_restavracij extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ImageView sortirajPoImenu;



    static ArrayList<Restavracija_item> restavracije = new ArrayList<>();
    double currentLatitude, currentLongtitude;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_restavracij);

        loadData();
        sortPoImenu();

        mRecyclerView = findViewById(R.id.recycler_prikaz_restavracij);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MyRecyclerViewAdapter(restavracije);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        mAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getBaseContext(), Podrobnosti_restavracij.class);
                String name = restavracije.get(position).getIme();
                String naslov = restavracije.get(position).getNaslov();
                float rtnBar = restavracije.get(position).getOcena();
                intent.putExtra("EXTRA_NAME", name);
                intent.putExtra("EXTRA_NASLOV", naslov);
                intent.putExtra("OCENA", rtnBar);
                startActivity(intent);

            }

            @Override
            public void onDeleteClick(int position) {
                restavracije.remove(position);
                saveData();

                finish();
                overridePendingTransition(0,0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);

            }

            @Override
            public void onUrediClick(int position){
                Intent intent = new Intent(getBaseContext(), Urejanje_restavracije.class);
                String name = restavracije.get(position).getIme();
                String naslov = restavracije.get(position).getNaslov();
                float rtnBar = restavracije.get(position).getOcena();
                intent.putExtra("EXTRA_NAME", name);
                intent.putExtra("EXTRA_NASLOV", naslov);
                intent.putExtra("OCENA", rtnBar);
                intent.putExtra("POSITION", position);
                startActivity(intent);
            }

        });
    }


    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared_restavracije", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("lista_restavracij", null);
        Type type = new TypeToken<ArrayList<Restavracija_item>>(){}.getType();
        restavracije = gson.fromJson(json, type);

        if(restavracije == null){
            restavracije = new ArrayList<>();
        }
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared_restavracije", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(restavracije);
        editor.putString("lista_restavracij", json);
        editor.apply();

    }

    private void sortPoImenu(){
        Collections.sort(restavracije, new SortImeRestavracije());

    }

}
