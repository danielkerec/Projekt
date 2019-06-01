package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button prikaziRestavracije;
    Button upravljajRestavracije;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prikaziRestavracije = (Button) findViewById(R.id.prikazi_restavracije);
        upravljajRestavracije = (Button) findViewById(R.id.dodaj_restavracijo);

        upravljajRestavracije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Dodajanje_restavracij.class));
            }
        });

        prikaziRestavracije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Prikaz_restavracij.class));
            }
        });
    }
}
