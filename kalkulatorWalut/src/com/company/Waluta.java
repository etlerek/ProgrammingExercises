package com.company;

public class Waluta implements IWaluta {
    private String nazwaWaluty;
    private Integer przelicznik;
    private String kodWaluty;
    private Double kursSredni;

    public Waluta(String nazwaWaluty, String przelicznik, String kodWaluty, String kursSredni) {
        this.nazwaWaluty = nazwaWaluty;
        this.przelicznik = Integer.parseInt(przelicznik);
        this.kodWaluty = kodWaluty;
        this.kursSredni = Double.parseDouble(kursSredni.replace(",","."));
    }

    public String getNazwaWaluty() { return nazwaWaluty; }

    public Integer getPrzelicznik() {
        return przelicznik;
    }

    public String getKodWaluty() {
        return kodWaluty;
    }

    public Double getKursSredni() {
        return kursSredni;
    }

    @Override
    public String wyswietlKursy(){
        return getKodWaluty() + " - " + getNazwaWaluty() + " kurs: " + getKursSredni() + " przelicznik: " + getPrzelicznik();
    }

    @Override
    public String wyswietlKody() {
        return getKodWaluty() + ", ";
    }

}
