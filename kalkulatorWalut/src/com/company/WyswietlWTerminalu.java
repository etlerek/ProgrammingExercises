package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class WyswietlWTerminalu {

    public boolean zamkniecie(){
        Scanner scan4 = new Scanner(System.in);
        System.out.println(" Jeżeli chcesz kontynuować wpisz dowolny przycisk\n Aby zamknąć program wpisz n");
        String czyZamknac = scan4.next();
        if((czyZamknac).equalsIgnoreCase("n")){
            return false;
        }
        return true;
    }

    public void piszWTerminalu(){

        Kalkulator kalkulator = new Kalkulator();
        WczytajURL obj = WczytajURL.getInstance();

        KolekcjaWalut kolekcja = new KolekcjaWalut(obj.odswiez());
        ArrayList<Waluta> waluty = kolekcja.getWaluty();

        boolean zapetlaj = true;

        while (zapetlaj) {

            for (Waluta waluta : waluty) {
                System.out.println(waluta.wyswietlKursy());
            }
            System.out.println("Kody walut: ");
            for (Waluta waluta : waluty) {
                System.out.print(waluta.wyswietlKody());
            }

            try {
                Scanner scan1 = new Scanner(System.in);
                System.out.println("\nPodaj kod pierwszej waluty:");
                String nazwaWaluty1 = scan1.nextLine().toUpperCase();

                for (int i = 0; i < waluty.size(); i++) {
                    if (nazwaWaluty1.equals(waluty.get(i).getKodWaluty())) {
                        kalkulator.setWaluta1(waluty.get(i));
                    }
                }

                Scanner scan2 = new Scanner(System.in);
                System.out.println("Podaj kod drugiej waluty:");
                String nazwaWaluty2 = scan2.nextLine().toUpperCase();


                if (nazwaWaluty2.equals(nazwaWaluty1)) {
                    System.out.println("Waluty sa takie same");
                    zapetlaj = zamkniecie();
                    if (zapetlaj == false) {
                        break;
                    }
                    continue;
                }

                for (int i = 0; i < waluty.size(); i++) {
                    if (nazwaWaluty2.equals(waluty.get(i).getKodWaluty())) {
                        kalkulator.setWaluta2(waluty.get(i));
                    }
                }

                Scanner scan3 = new Scanner(System.in);
                System.out.println("Podaj ilosc waluty do przeliczenia:");
                Double iloscMonet = Double.valueOf(scan3.nextLine());
                if (iloscMonet <= 0){
                    iloscMonet *= -1;
                    System.out.println("Ilość monet nie może być ujemna, zamieniono znak naprzeciwny");
                }

                System.out.println();
                System.out.println(iloscMonet + " " + nazwaWaluty1 + " to " + kalkulator.licz(iloscMonet) + " " + nazwaWaluty2);
                System.out.println();
            }

            catch (NullPointerException e){
                System.out.println("Błąd! Podałeś walutę która nie istnieje, sprawdź ponownie poprawność wpisanego kodu waluty\n");
            }
            catch(NumberFormatException e){
                System.out.println("Błąd! Ilość waluty musi być liczbą");
            }
            catch (Exception e){
                System.out.println("Błąd! Spróbuj ponownie");
            }
            finally {

                kalkulator.setWaluta1(null);
                kalkulator.setWaluta2(null);
                zapetlaj = zamkniecie();
            }
        }

    }
}
