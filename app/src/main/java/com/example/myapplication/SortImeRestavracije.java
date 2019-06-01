package com.example.myapplication;

import java.util.Comparator;

public class SortImeRestavracije implements Comparator<Restavracija_item> {

    public int compare(Restavracija_item prva, Restavracija_item druga){
        return prva.getIme().compareTo(druga.getIme());
    }
}
