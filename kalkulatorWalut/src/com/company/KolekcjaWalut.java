package com.company;

import java.util.ArrayList;

public class KolekcjaWalut {

    private ArrayList<Waluta> waluty = null;

    public KolekcjaWalut(ArrayList<Waluta> waluty){
        this.waluty = waluty;
    }

    public ArrayList<Waluta> getWaluty() {
        return waluty;
    }

    public void setWaluty(ArrayList<Waluta> waluty) {
        this.waluty = waluty;
    }

}
