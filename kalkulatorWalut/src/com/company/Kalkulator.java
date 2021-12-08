package com.company;

public class Kalkulator {
    private Waluta waluta1 = null;
    private Waluta waluta2 = null;


    public void setWaluta1(Waluta waluta1) {
        this.waluta1 = waluta1;
    }
    public void setWaluta2(Waluta waluta2) {
        this.waluta2 = waluta2;
    }

    public Waluta getWaluta1() { return waluta1; }
    public Waluta getWaluta2() {
        return waluta2;
    }

    public void print(){
        System.out.println(waluta1.wyswietlKursy());
        System.out.println(waluta2.wyswietlKursy());

    }

    public double licz(double ilosc){
        Double wynik = (waluta1.getKursSredni() * waluta2.getPrzelicznik())/ (waluta2.getKursSredni()* waluta1.getPrzelicznik()) * ilosc;
        return Math.round(wynik*100.0)/100.0;
    }

}
