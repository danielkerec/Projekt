package com.example.myapplication;

public class Restavracija_item {
    private String ime;
    private String naslov;
    private float ocena;

    public Restavracija_item(String ime, String naslov, float ocena) {
        this.ime = ime;
        this.naslov = naslov;
        this.ocena = ocena;
    }
    public Restavracija_item(){
        ime = "";
        naslov = "";
        ocena = (float) 0.0;
    }

    public String getIme() {
        return ime;
    }

    public String getNaslov() {
        return naslov;
    }

    public float getOcena() {
        return ocena;
    }

    public void setOcena(float ocena) {
        this.ocena = ocena;
    }

    @Override
    public String toString(){
        return "Ime: " + ime + " naslov: " + naslov;
    }

}
