package com.example.myapplication;

public class Restavracija_item {
    private String ime;
    private String naslov;

    public Restavracija_item(String ime, String naslov) {
        this.ime = ime;
        this.naslov = naslov;
    }
    public Restavracija_item(){
        ime = "";
        naslov = "";
    }

    public String getIme() {
        return ime;
    }

    public String getNaslov() {
        return naslov;
    }

    @Override
    public String toString(){
        return "Ime: " + ime + " naslov: " + naslov;
    }

}
