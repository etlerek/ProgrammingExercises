package company;

import company.events.Event;
import company.fireDeparments.FireDepartment;
import company.strategies.SendOne;
import company.strategies.SendThree;
import company.strategies.SendTwo;

import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();

        int count = 0;
        ArrayList<FireDepartment> departments = new ArrayList<>();
        departments.add(new FireDepartment(50.05995, 19.94308, "jrg1"));
        departments.add(new FireDepartment(50.03341, 19.93579, "jrg2"));
        departments.add(new FireDepartment(50.07559, 19.88775, "jrg3"));
        departments.add(new FireDepartment(50.0377, 20.00569, "jrg4"));
        departments.add(new FireDepartment(50.09224, 19.92201, "jrg5"));
        departments.add(new FireDepartment(50.01612, 20.0101, "jrg6"));
        departments.add(new FireDepartment(50.09407, 19.97741, "jrg7"));
        departments.add(new FireDepartment(50.07675, 20.03000, "psp "));
        departments.add(new FireDepartment(50.07324, 19.78613, "bali"));
        departments.add(new FireDepartment(49.96842, 19.79943, "skaw"));

        System.out.println("Współrzędne jednostek ratunkowych:");
        for(int j = 0; j< departments.size();j++)
            departments.get(j).print();
        System.out.println();

        while(count < 100) {
            //tworze zdarzenie
            Event event = new Event();

            //przypisuje do niego obserwatorów
            for(int j = 0; j< departments.size();j++)
                event.addObserver(departments.get(j));

            //powiadamiam obserwatorow
            event.notifyObservers();

            //sortuje obserwatorow po odleglosci
            Collections.sort(departments);
            System.out.println("\nZdarzenie: X = "+ event.getCords()[0] + ", Y = " + event.getCords()[1] + " typ: " + event.handle());
            for (int j = 0; j < departments.size(); j++) {
                System.out.println(departments.get(j).getName() +"\t odległośc do zdarzenia: " + Math.round((71*departments.get(j).getDistance())*100.0)/100.0 + "km  "+ "\t dostępnych wozów: " +departments.get(j).getCarsNumber());
            }

            while (true){
                int index = 0;
                int check;
                if (event.handle().equals("MZ")) {
                    departments.get(index).setStrategy(new SendTwo());
                    System.out.println(departments.get(index).getName().toUpperCase(Locale.ROOT)+" WYSYLA JEDNOSTKI");
                    check = departments.get(index).execute();
                    while(check > 0){
                        index++;
                        System.out.println(departments.get(index-1).getName().toUpperCase(Locale.ROOT)+" WYSLANO " + check + " WOZY ZA MALO" );
                        if (check == 1) {
                            departments.get(index).setStrategy(new SendOne());
                            check = departments.get(index).execute();
                        }
                        if (check == 2) {
                            departments.get(index).setStrategy(new SendTwo());
                            check = departments.get(index).execute();
                        }
                        System.out.println(departments.get(index).getName().toUpperCase(Locale.ROOT)+" DOSYLA BRAKUJĄCE WOZY" );
                    }
                }
                if (event.handle().equals("PZ")) {
                    departments.get(index).setStrategy(new SendThree());
                    System.out.println(departments.get(index).getName().toUpperCase(Locale.ROOT)+" WYSYLA JEDNOSTKI");
                    check = departments.get(index).execute();
                    while(check > 0) {
                        index++;
                        System.out.println(departments.get(index-1).getName().toUpperCase(Locale.ROOT)+" WYSLANO " + check + " WOZY ZA MALO" );
                        if (check == 1) {
                            departments.get(index).setStrategy(new SendOne());
                            check = departments.get(index).execute();
                        }
                        if (check == 2) {
                            departments.get(index).setStrategy(new SendTwo());
                            check = departments.get(index).execute();
                        }
                        if (check == 3) {
                            departments.get(index).setStrategy(new SendThree());
                            check = departments.get(index).execute();
                        }
                        System.out.println(departments.get(index).getName().toUpperCase(Locale.ROOT)+" DOSYLA BRAKUJĄCE WOZY" );
                    }
                }
                break;
            }

//            System.out.println("Zdarzenie: X = "+ event.getCords()[0] + ", Y = " + event.getCords()[1] + " typ: " + event.handle());
//            for (int j = 0; j < departments.size(); j++) {
//                System.out.println(departments.get(j).getName() +"\t odległośc do zdarzenia: " + Math.round(71*departments.get(j).getDistance()*100)/100 + "km"+ "\t pozostało aut: " +departments.get(j).getCarsNumber());
//            }

            System.out.println();
            Thread.sleep(random.nextInt(5000)+500);
            count++;
        }

    }
}
