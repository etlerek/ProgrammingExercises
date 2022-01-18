package company.cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck implements ICardDeck{

    private static ArrayList<Card> deckOfCards = new ArrayList<>();

    public Deck(){
        makeDeckOfCards();
    }

    @Override
    public void addCard(Card card) {
        deckOfCards.add(card);
    }


    public static void removeCard() {
        deckOfCards.remove(0);
    }

    public static Card getCard(){
        Card cardToGet = deckOfCards.get(0);
        removeCard();
        return cardToGet;
    }

    public static Card getCard(int choice){
        Card cardToGet = deckOfCards.get(0);
        cardToGet.setCardState(ICardStates.State.HIDDEN);
        removeCard();
        return cardToGet;
    }

    public static void retrieveAll() {
        deckOfCards.clear();
        makeDeckOfCards();
    }

    public static void makeDeckOfCards(){
        for(Integer j = 2; j < 11; j++) {
            deckOfCards.add(new Card(j.toString(), j, "spades"));
            deckOfCards.add(new Card(j.toString(), j, "hearts"));
            deckOfCards.add(new Card(j.toString(), j, "diamonds"));
            deckOfCards.add(new Card(j.toString(), j, "clubs"));
        }
        deckOfCards.add(new Card("Jack", 10, "spades"));
        deckOfCards.add(new Card("Jack", 10, "hearts"));
        deckOfCards.add(new Card("Jack", 10, "diamonds"));
        deckOfCards.add(new Card("Jack", 10, "clubs"));
        deckOfCards.add(new Card("Queen", 10, "spades"));
        deckOfCards.add(new Card("Queen", 10, "hearts"));
        deckOfCards.add(new Card("Queen", 10, "diamonds"));
        deckOfCards.add(new Card("Queen", 10, "clubs"));
        deckOfCards.add(new Card("King", 10, "spades"));
        deckOfCards.add(new Card("King", 10, "hearts"));
        deckOfCards.add(new Card("King", 10, "diamonds"));
        deckOfCards.add(new Card("King", 10, "clubs"));
        deckOfCards.add(new Card("Ace", 11, "spades"));
        deckOfCards.add(new Card("Ace", 11, "hearts"));
        deckOfCards.add(new Card("Ace", 11, "diamonds"));
        deckOfCards.add(new Card("Ace", 11, "clubs"));
        Collections.shuffle(deckOfCards);
    }
}
