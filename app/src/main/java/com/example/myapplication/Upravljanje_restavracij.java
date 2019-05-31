package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

public class Upravljanje_restavracij extends AppCompatActivity {

    Button shrani;
    EditText ime;
    EditText naslov;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upravljanje_restavracij);

        shrani = (Button) findViewById(R.id.upravljanje_potrdi);
        naslov = (EditText) findViewById(R.id.naslov_restavracije);
        ime = (EditText) findViewById(R.id.ime_restavracije);

        shrani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                naslov.getText().toString();
                ime.getText().toString();


                Intent intent = new Intent(getBaseContext(), Prikaz_restavracij.class);
                intent.putExtra("NAME", ime.toString());
                intent.putExtra("NASLOV", naslov.toString());
                startActivity(intent);
            }
        });

    }
}
