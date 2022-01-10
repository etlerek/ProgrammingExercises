package company.cards;

public interface ICardStates {
    enum State{
        HIDDEN,
        SHOWN;
    }

    State getCardState();
    void setCardState(State cardState);
}
