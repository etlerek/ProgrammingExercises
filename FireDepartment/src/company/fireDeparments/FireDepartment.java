package company.fireDeparments;

import company.events.Event;
import company.states.truck.Busy;
import company.strategies.IStrategy;

import java.util.*;


public class FireDepartment implements IStrategy, IObserver, Comparable<FireDepartment> {


    private double cords[] = new double[2];
    private ArrayList<Truck> trucks = new ArrayList<>();
    private String name;
    private double distance;
    private IStrategy strategy;

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public double getDistance() {
        return distance;
    }

    public FireDepartment(double x, double y, String name){
        this.cords[0] = x;
        this.cords[1] = y;
        this.name = name;
        int i = 0;
        while(i<5){
            trucks.add(new Truck(name));
            i++;
        }
    }

    public String getName(){
        return this.name;
    }

    public int getCarsNumber(){
        int number = 0;
        for (Truck truck: trucks) {
            if(truck.handle() == "free")
                number++;
        }
        return number;
    }

    @Override
    public int execute() {
        int val = 0;
        int i = 0;
        Iterator<Truck> it =  trucks.iterator();
        while(it.hasNext() && val < strategy.execute()){
            Truck truck = it.next();
            if(truck.handle() == "free") {
                truck.setState(new Busy());
                val++;
            }
            i++;
        }
//        System.out.println("Potrzeba wozów: " + (strategy.execute()-val));
        return strategy.execute() - val;
    }

    @Override
    public Double update(Event event) {
        event.handle();
        this.distance = Math.sqrt(Math.pow(this.cords[0]-event.getCords()[0], 2)+Math.pow(this.cords[1]-event.getCords()[1], 2));
        return this.distance*100;
    }

    public void print(){
        System.out.println(name + " " + "[" +cords[0] +", "+ cords[1] +"]");
    }

    @Override
    public int compareTo(FireDepartment o) {
        if(this.distance > o.getDistance()){
            return 1;
        }
        else if(this.distance < o.getDistance()){
            return -1;
        }
        else
            return 0;
    }
}
