package company.states.truck;

public class Busy implements ITruckState{
    String busy = "busy";

    @Override
    public String handle(){
        return busy;
    }
}
