package company.states;

public class StateIll implements IPersonState {
    @Override
    public String handle() {
        return "ill";
    }
}
