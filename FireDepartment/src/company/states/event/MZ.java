package company.states.event;

public class MZ implements IEventState{
    String MZ = "MZ";

    @Override
    public String handle(){
        return MZ;
    }
}
