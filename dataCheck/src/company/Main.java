package company;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Terminal terminal = new Terminal();
        boolean loop = true;

        while (loop) {
            for (int i = 0; i < 10; ++i) System.out.println();
            int choice;
            System.out.println();
            System.out.println("//////////////////////////////////");
            System.out.println("//\t1 - zmień dane osoby        //");
            System.out.println("//\t2 - wprowadź nową osobę     //");
            System.out.println("//\t3 - zapisz listę osób       //");
            System.out.println("//\t4 - wypisz w terminalu      //");
            System.out.println("//\t5 - wprowadź testowe osoby  //");
            System.out.println("//\t0 - wyjście                 //");
            System.out.println("//////////////////////////////////");

            System.out.print("Wprowadź liczbę: ");
            Scanner intScanner = new Scanner(System.in);
            Scanner doubleScanner = new Scanner(System.in);
            doubleScanner.useLocale(Locale.US);
            Scanner stringScanner = new Scanner(System.in);

            try {
                choice = intScanner.nextInt();
            }
            catch (Exception e){
                System.out.println("Wprowadzona wartość nie jest liczbą całkowitą!");
                continue;
            }

            switch (choice){
                case 0:
                    loop = false;
                    break;
                case 1:
                    System.out.print("Wprowadź numer osoby którą chcesz edytować: ");
                    int index = intScanner.nextInt();
                    System.out.print("Podaj imię osoby: ");
                    String name = stringScanner.nextLine();
                    System.out.print("Podaj nazwisko osoby: ");
                    String surname = stringScanner.nextLine();
                    terminal.editPerson(index, name, surname);
                    break;
                case 2:
                    String[] data = new String[2];
                    double[] dataDouble = new double[2];
                    System.out.print("Podaj imię osoby: ");
                    data[0] = stringScanner.nextLine();
                    System.out.print("Podaj nazwisko osoby: ");
                    data[1] = stringScanner.nextLine();
                    try
                    {
                        System.out.print("Podaj współrzędną x: ");
                        dataDouble[0] = doubleScanner.nextDouble();
                        System.out.print("Podaj współrzędną y: ");
                        dataDouble[1] = doubleScanner.nextDouble();
                    }
                    catch (Exception InputMismatchException){
                        System.out.println("Podana współrzędna nie była liczbą");
                        break;
                    }
                    terminal.addPerson(data[0],data[1],dataDouble[0],dataDouble[1]);
                    break;
                case 3:
                    terminal.printToFile();
                    break;
                case 4:
                    terminal.printToConsole();
                    break;
                case 5:
                    terminal.addPerson("dwight", "schRUTE", 53.11, 23.61);
                    terminal.addPerson("michael", "scott", 52.99, 22.99);
                    terminal.addPerson("ANDY", "bernard", 53.123, 23.33);
                    terminal.addPerson("john", "xinA", 52.923, 23.13);
                    terminal.addPerson("tHe", "WoK", 52.923, 23.13);
                    break;
                default:
                    break;
            }
        }
    }
}
