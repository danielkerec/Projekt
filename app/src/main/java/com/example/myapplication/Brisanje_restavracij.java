package com.example.myapplication;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class Brisanje_restavracij extends FragmentActivity implements OnMapReadyCallback {

    EditText ime;
    EditText naslov;

    GoogleMap map;

    public String naslovzamaps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brisanje_restavracij);
        String name = getIntent().getStringExtra("EXTRA_NAME");
        String address = getIntent().getStringExtra("EXTRA_NASLOV");
        ime = (EditText) findViewById(R.id.edit_ime_restavracije);
        ime.setText(name);
        naslov = (EditText) findViewById(R.id.podrobnosti_naslov);
        naslov.setText(address);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        naslovzamaps = address;
        naslov.setKeyListener(null);
        ime.setKeyListener(null);

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        Geocoder coder = new Geocoder(this);
        List<Address> addresses;
        try{
            addresses = coder.getFromLocationName(naslovzamaps, 5);
            if(addresses == null){
            }
            Address location = addresses.get(0);
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            LatLng latLng = new LatLng(lat, lng);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            googleMap.addMarker(markerOptions);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
/*
        LatLng marker = new LatLng(46.684857,16.219615);
        map.addMarker(new MarkerOptions().position(marker).title("Perunika"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 17));*/
    }
}
