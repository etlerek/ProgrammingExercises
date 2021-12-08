package company.states.truck;

public class Free implements ITruckState{
    String free = "free";

    @Override
    public String handle(){
        return free;
    }
}
