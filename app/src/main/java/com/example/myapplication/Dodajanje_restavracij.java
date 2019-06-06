package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import static com.example.myapplication.Prikaz_restavracij.restavracije;

public class Dodajanje_restavracij extends AppCompatActivity {

    Button shrani;
    EditText ime;
    EditText naslov;
    RatingBar rtnBar;
    ImageView nazaj;
    ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodajanje_restavracij);

        final ArrayList<String> list;
        final SharedPreferences sp = getSharedPreferences("restavracije", MODE_PRIVATE);

        list = new ArrayList<>();

        shrani = (Button) findViewById(R.id.upravljanje_potrdi);
        naslov = (EditText) findViewById(R.id.naslov_restavracije);
        ime = (EditText) findViewById(R.id.ime_restavracije);
        rtnBar = (RatingBar) findViewById(R.id.dodajanje_rating);
        nazaj = (ImageView) findViewById(R.id.nazaj_dodajanje);
        home = (ImageView) findViewById(R.id.dodajanje_home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dodajanje_restavracij.this, MainActivity.class));
            }
        });

        nazaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        shrani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                naslov.getText().toString();
                ime.getText().toString();
                rtnBar.getRating();


                if(ime.getText().toString().isEmpty() || naslov.getText().toString().isEmpty())
                {
                    Toast.makeText(Dodajanje_restavracij.this, "Napaka pri vnosu!", Toast.LENGTH_LONG).show();
                }
                else{
                    restavracije.add(new Restavracija_item(ime.getText().toString(), naslov.getText().toString(), rtnBar.getRating()));

                    saveData();

                    Intent intent = new Intent(getBaseContext(), Prikaz_restavracij.class);
                    intent.putExtra("NAME", ime.getText());
                    intent.putExtra("NASLOV", naslov.getText());
                    intent.putExtra("OCENA", rtnBar.getNumStars());
                    startActivity(intent);


                }

            }
        });

    }
    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared_restavracije", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(restavracije);
        editor.putString("lista_restavracij", json);
        editor.apply();

    }
}
