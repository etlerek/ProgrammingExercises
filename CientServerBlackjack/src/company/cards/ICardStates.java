package company.cards;

public interface ICardStates {
    enum State{
        HIDDEN,
        SHOWN,
        LOVERED;
    }

    State getCardState();
    void setCardState(State cardState);
}
