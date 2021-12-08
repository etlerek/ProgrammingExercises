package company;

import java.util.Random;

public class DiseasTransfer{

    public static void checkStates(Person illPerson, Person healthyPerson){
        Random random = new Random();
        if(healthyPerson.handleSub().equals("vulnerable") && illPerson.handleSub().equals("hasSymptoms")){
            healthyPerson.gettingIll();
        }
        else if(healthyPerson.handleSub().equals("vulnerable") && illPerson.handleSub().equals("doesntHave")){
            if(random.nextInt(100)<50){
                healthyPerson.gettingIll();
            }
        }

    }

    static void checkDistance(Person[] person){
        for(int i = 0; i < person.length; i++){
            for(int j = 0; j < person.length; j++){
                if(person[i].wektor.distanceBetween(person[j].wektor)<=10 && person[i].handle().equals("ill") && !(person[j].handle().equals("resist"))){
                    if(i != j){
                        //System.out.println("chora " +person[i].getId() + " zdrowa: " + person[j].getId() + " czas przebywania: " + person[j].getTimeToInfection());
                        person[j].increaseTimeToInfection();
                        if(person[j].getTimeToInfection() == 3) {
                            DiseasTransfer.checkStates(person[i], person[j]);
                            person[j].setTimeToInfection(0);
                        }
                    }

                }
                else if(person[i].wektor.distanceBetween(person[j].wektor)<=10 && person[j].handle().equals("ill") && !(person[i].handle().equals("resist"))){
                    if(i != j) {
                        //System.out.println("chora: " + person[j].getId() + " zdrowa: " + person[i].getId() + " czas przebywania: " + person[j].getTimeToInfection());
                        person[i].increaseTimeToInfection();
                        if(person[i].getTimeToInfection() == 3) {
                            DiseasTransfer.checkStates(person[j], person[i]);
                            person[i].setTimeToInfection(0);
                        }
                    }
                }
            }
        }
    }
}
