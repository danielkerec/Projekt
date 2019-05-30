package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Brisanje_restavracij extends AppCompatActivity {

    EditText ime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brisanje_restavracij);
        String name = getIntent().getStringExtra("EXTRA_NAME");
        ime = (EditText) findViewById(R.id.edit_ime_restavracije);
        ime.setText(name);
    }
}
