package company.states;

public class StateHasSymptoms implements IPersonState {
    @Override
    public String handle() {
        return "hasSymptoms";
    }
}
