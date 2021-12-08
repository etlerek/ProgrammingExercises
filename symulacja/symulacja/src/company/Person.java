package company;

import company.states.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Person implements IPerson{


    Random random = new Random();
    Timer timer = new Timer();

    private static int nextId = 0;
    private IPersonState personState = new StateHealthy();
    private IPersonState personSubState = new StateVulnerable();
    private int timeToInfection = 0;
    private int timeToResist = 0;
    private int requiredTimeToResist = random.nextInt(10)+20;
    private int randomNumber = random.nextInt(100);
    public Vector wektor;


    public Person(Person person){
        personState = person.getPersonState();
        personSubState = person.getPersonSubState();
        wektor = new Vector(person.wektor);
    }

    public Person(int mode){

        wektor = new Vector(random.nextInt(780), random.nextInt(780));
        if(mode == 0){
            this.personSubState = new StateVulnerable();

            if (random.nextInt(100) < 10) {
                this.gettingIll();

            }
            else {
                this.personState = new StateHealthy();
            }
        }
        if(mode == 1){
            if(randomNumber < 10){
                this.personSubState = new StateResist();
                this.personState = new StateHealthy();
            }
            else if(randomNumber >= 10 && randomNumber < 80){
                this.personState = new StateHealthy();
            }
            else{
                this.gettingIll();
            }
        }
    }

    public void setState(String state){
        if(state.equals("ill")) {
            this.gettingIll();
        }
        else if(state.equals("healthy"))
            personState = new StateHealthy();
    }

    public String handle(){
        return personState.handle();
    }

    public String handleSub(){
        return personSubState.handle();
    }

    public void gettingIll(){
        if(!this.handleSub().equals("resist")) {
            personState = new StateIll();
            if (random.nextInt(100) < 50) {
                personSubState = new StateHasSymptoms();
            } else {
                personSubState = new StateDoesntHaveSymptoms();
            }
        }
        timer.schedule(new TimerTask() {public void run() {personState = new StateHealthy();personSubState = new StateResist();}}, random.nextInt(10000)+20000);
    }

    public void increaseTimeToInfection(){
        this.timeToInfection++;
    }

    public void setTimeToInfection(int number){
        this.timeToInfection = number;
    }

    public int getTimeToInfection(){
        return timeToInfection;
    }

    public void increaseTimeToResist(){
        if(this.timeToResist >= this.requiredTimeToResist){
            this.personState = new StateHealthy();
            this.personSubState = new StateResist();
        }
        else{
            System.out.println(requiredTimeToResist);
            this.timeToResist++;
        }
    }

    public IPersonState getPersonState(){return personState;}
    public IPersonState getPersonSubState(){return personSubState;}

}
