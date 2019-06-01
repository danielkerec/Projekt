package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import static com.example.myapplication.Prikaz_restavracij.restavracije;

public class Urejanje_restavracije extends AppCompatActivity {

    EditText ime;
    EditText naslov;
    RatingBar rtnBar;
    Button potrdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urejanje_restavracije);

        final ArrayList<String> list;
        final SharedPreferences sp = getSharedPreferences("restavracije", MODE_PRIVATE);

        list = new ArrayList<>();

        ime = (EditText) findViewById(R.id.urejanje_ime_restavracije);
        naslov = (EditText) findViewById(R.id.urejanje_naslov_restavracije);
        rtnBar = (RatingBar) findViewById(R.id.urejanje_dodajanje_rating);
        potrdi = (Button) findViewById(R.id.urejanje_potrdi);

        String name = getIntent().getStringExtra("EXTRA_NAME");
        String address = getIntent().getStringExtra("EXTRA_NASLOV");
        Bundle bundle = getIntent().getExtras();
        float rating = bundle.getFloat("OCENA");
        final int position = getIntent().getIntExtra("POSITION", -1);

        ime.setText(name.toString());
        naslov.setText(address.toString());
        rtnBar.setRating(rating);

        potrdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ime.getText().toString().isEmpty() && naslov.getText().toString().isEmpty())
                {
                    Toast.makeText(Urejanje_restavracije.this, "Napaka pri vnosu!", Toast.LENGTH_LONG).show();
                }else{
                    restavracije.set(position, new Restavracija_item(ime.getText().toString(), naslov.getText().toString(), rtnBar.getRating()));

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
