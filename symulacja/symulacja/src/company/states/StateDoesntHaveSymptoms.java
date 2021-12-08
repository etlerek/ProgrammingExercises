package company.states;

public class StateDoesntHaveSymptoms implements IPersonState {
    @Override
    public String handle() {
        return "doesntHave";
    }
}
