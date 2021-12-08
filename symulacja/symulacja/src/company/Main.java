package company;

import company.gui.Frame;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Main {

    static void wypisz(Person[] person){
        for(int k = 0; k< person.length; k++) {
            System.out.println(person[k].handle() +" "+ person[k].handleSub()+ " " + Arrays.toString(person[k].wektor.getComponents()));
        }
    }

    public static void main(String[] args) {

        int NUMBER_OF_PEOPLE = 100;
        int mode = 0;
        int n = 700;
        int m = 700;
        int time = 0;
        Random random = new Random();

        Person[] person = new Person[NUMBER_OF_PEOPLE];
        for(int k = 0; k < person.length; k++) {
            person[k] = new Person(mode);
        }
        Frame frame = new Frame(n, m, person);
        frame.setVisible(true);

        while(true){

            frame.panel.repaint();
            person = frame.panel.getPerson();
//            Main.wypisz(person);
            DiseasTransfer.checkDistance(person);
            for(int i = 0; i < person.length; i++) {
                person[i].wektor.moveBy(n, m);
                if(person[i].wektor.getComponents()[0]< -2 || person[i].wektor.getComponents()[1]< -2 || person[i].wektor.getComponents()[0] > n+2 || person[i].wektor.getComponents()[1] > m+2) {
                    person[i] = new Person(mode);
                    if(random.nextInt(100)<50)
                        if(random.nextInt(100)<50) {
                            person[i].wektor.setX(random.nextInt(n));
                            person[i].wektor.setY(8);
                        }
                        else{
                            person[i].wektor.setX(random.nextInt(n));
                            person[i].wektor.setY(m-8);
                        }
                    else{
                        if(random.nextInt(100)<50) {
                            person[i].wektor.setX(8);
                            person[i].wektor.setY(random.nextInt(m));
                        }
                        else{
                            person[i].wektor.setX(n-8);
                            person[i].wektor.setY(random.nextInt(m));
                        }
                    }
//                    System.out.println("Nowy się pojawił");
                }
            }
//            for(int i = 0; i < person.length; i++)
//            {
//                if(person[i].handle().equals("ill"))
//                    person[i].increaseTimeToResist();
//            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            time++;
        }


    }
}
