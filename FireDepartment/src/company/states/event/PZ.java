package company.states.event;

public class PZ implements IEventState{
    String PZ ="PZ";
    @Override
    public String handle() {
        return PZ;
    }
}
