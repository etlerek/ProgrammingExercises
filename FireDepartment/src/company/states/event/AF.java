package company.states.event;

public class AF implements IEventState{
    String AF = "AF";
    @Override
    public String handle(){
        return AF;
    }
}
