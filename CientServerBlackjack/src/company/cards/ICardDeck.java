package company.cards;

public interface ICardDeck {
    void shuffle();
    void makeDeckOfCards();
    void addCard(Card card);
    void removeCard();
    void retrieveAll();
}
