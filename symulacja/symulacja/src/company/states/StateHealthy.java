package company.states;

public class StateHealthy implements IPersonState {
    @Override
    public String handle() {
        return "healthy";
    }
}
