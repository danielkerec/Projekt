package com.example.myapplication;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class Podrobnosti_restavracij extends FragmentActivity implements OnMapReadyCallback {

    EditText ime;
    EditText naslov;
    RatingBar rtnBar;
    ImageView nazaj;
    ImageView home;

    GoogleMap map;

    public String naslovzamaps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podrobnosti_restavracije);
        String name = getIntent().getStringExtra("EXTRA_NAME");
        String address = getIntent().getStringExtra("EXTRA_NASLOV");
        Bundle bundle = getIntent().getExtras();
        float rating = bundle.getFloat("OCENA");
        ime = (EditText) findViewById(R.id.edit_ime_restavracije);
        ime.setText(name);
        naslov = (EditText) findViewById(R.id.podrobnosti_naslov);
        naslov.setText(address);
        rtnBar = (RatingBar) findViewById(R.id.podrobnosti_rating);
        rtnBar.setRating(rating);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        naslovzamaps = address;
        naslov.setKeyListener(null);
        ime.setKeyListener(null);


        nazaj = (ImageView) findViewById(R.id.nazaj_podrobnosti);
        home = (ImageView) findViewById(R.id.podrobnosti_home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Podrobnosti_restavracij.this, MainActivity.class));
            }
        });

        nazaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        Geocoder coder = new Geocoder(this);
        List<Address> addresses;
        try{
            addresses = coder.getFromLocationName(naslovzamaps, 5);
            if(addresses != null && addresses.size()>0) {

                Address location = addresses.get(0);
                double lat = location.getLatitude();
                double lng = location.getLongitude();
                LatLng latLng = new LatLng(lat, lng);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                googleMap.addMarker(markerOptions);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            }else{
                Toast.makeText(Podrobnosti_restavracij.this, "Napaka v lokaciji", Toast.LENGTH_LONG).show();
            }
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
