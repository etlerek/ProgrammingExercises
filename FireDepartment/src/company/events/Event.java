package company.events;

import company.fireDeparments.IObserver;
import company.states.event.IEventState;
import company.states.event.MZ;
import company.states.event.PZ;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Event implements IEvent, ISubject, IEventState{

    private ArrayList<IObserver>list = new ArrayList<>();

    Random random = new Random();

    public double[] getCords() {
        return cords;
    }

    private double cords[] = new double[2];
    IEventState state;

    public Event(){
        this.cords[0] = random.nextDouble()* 0.196013757 + 49.95855025648944;
        this.cords[1] = random.nextDouble()* 0.336410276 + 19.688292482742394;

        if(random.nextInt(100)<30)
            state = new PZ();
        else{
            state = new MZ();
        }
    }

    @Override
    public String handle(){return state.handle();}

    public void print(){
        System.out.println(cords[0] +" "+ cords[1]+ " "+ handle());
    }

    @Override
    public void addObserver(IObserver obj) {
        if(obj != null)
            list.add(obj);
    }

    @Override
    public void removeObserver(IObserver obj) {
        if(obj != null)
            list.remove(obj);
    }

    @Override
    public void notifyObserver(IObserver obj) {

    }

    @Override
    public void notifyObservers() {
        Iterator<IObserver> it =  list.iterator();
        while(it.hasNext()){
            IObserver observer = it.next();
            observer.update(this);
        }
    }
}
