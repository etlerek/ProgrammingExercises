package company.states;

public class StateVulnerable implements IPersonState {
    @Override
    public String handle() {
        return "vulnerable";
    }
}
