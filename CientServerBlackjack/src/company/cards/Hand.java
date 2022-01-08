package company.cards;

import java.util.ArrayList;
import java.util.List;

public class Hand implements IHand {
    List<Card> cardsInHand = new ArrayList<>();

    @Override
    public void addCard(Card card) {
        cardsInHand.add(card);
    }

    @Override
    public void discardAll() {
        for (Card card: cardsInHand) {
            cardsInHand.remove(card);
        }
    }

    @Override
    public void showCards() {
        for (Card card: cardsInHand) {
            card.printCard();
        }
    }
}
