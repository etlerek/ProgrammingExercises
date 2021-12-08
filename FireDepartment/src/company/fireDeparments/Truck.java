package company.fireDeparments;

import company.states.truck.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Truck implements ITruck, ITruckState {

    private ITruckState truckState;
    String departmentName;
    Timer timer = new Timer();
    Random random = new Random();

    public Truck(String departmentName){
        this.truckState = new Free();
        this.departmentName = departmentName;
    }

    public void setState(ITruckState state) {
        this.truckState = state;
        if(random.nextInt(100)<5){
            System.out.println("To był fałszywy alarm, jednostka odesłana spowrotem do " + departmentName);
            timer.schedule(new TimerTask() {public void run() {
                System.out.println("Jednostka wraca do bazy: " + departmentName);
                truckState = new Free();}}, (4000)+2000);
        }
        else{
            timer.schedule(new TimerTask() {public void run() {
                System.out.println("Jednostka wraca do bazy: " + departmentName);
                truckState = new Free();}}, (25000)+5000);
        }

    }


    @Override
    public String handle() {
        return truckState.handle();
    }
}
