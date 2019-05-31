package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static com.example.myapplication.Prikaz_restavracij.restavracije;

public class Upravljanje_restavracij extends AppCompatActivity {

    Button shrani;
    EditText ime;
    EditText naslov;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upravljanje_restavracij);

        final ArrayList<String> list;
        final SharedPreferences sp = getSharedPreferences("restavracije", MODE_PRIVATE);

        list = new ArrayList<>();
        MyRecyclerViewAdapter app;

        shrani = (Button) findViewById(R.id.upravljanje_potrdi);
        naslov = (EditText) findViewById(R.id.naslov_restavracije);
        ime = (EditText) findViewById(R.id.ime_restavracije);

        shrani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                naslov.getText().toString();
                ime.getText().toString();

                if(ime.getText().toString().isEmpty() && naslov.getText().toString().isEmpty())
                {
                    Toast.makeText(Upravljanje_restavracij.this, "Napaka pri vnosu!", Toast.LENGTH_LONG).show();
                }
                else{
                    restavracije.add(new Restavracija_item(ime.getText().toString(), naslov.getText().toString()));
/*
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                    Gson gson = new Gson();
                    String json = gson.toJson(restavracije);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("restavracijo_save", json);
                    editor.apply();
                    editor.commit();
                    */




                }

                Intent intent = new Intent(getBaseContext(), Prikaz_restavracij.class);
                intent.putExtra("NAME", ime.getText());
                intent.putExtra("NASLOV", naslov.getText());
                startActivity(intent);

            }
        });

    }
}
