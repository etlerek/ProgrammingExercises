package company.cards;

public class Card implements  ICard, ICardStates{

    private String name;
    private int value;
    private String color;

    private State cardState = State.SHOWN;

    public Card(String name, int value, String color) {
        this.name = name;
        this.value = value;
        this.color = color;
    }

    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }
    public String getColor() {
        return color;
    }

    @Override
    public String printCard() {
        if (cardState == State.HIDDEN)
        {
            return "HIDDEN";
        }
        return name + " of " + color;
    }

    public State getCardState() {
        return cardState;
    }

    public void setCardState(State cardState) {
        this.cardState = cardState;
    }

}
