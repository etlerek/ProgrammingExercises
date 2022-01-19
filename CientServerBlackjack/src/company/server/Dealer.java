package company.server;

import company.cards.Card;
import company.cards.Deck;
import company.cards.ICardStates;

import java.util.ArrayList;

import static company.server.Server.clients;

public class Dealer{
    public static ArrayList<Card> dealersHand = new ArrayList<>();
    private static int score = 0;

    public static void addCard(Card card) {
        dealersHand.add(card);
        if(card.getCardState() != ICardStates.State.HIDDEN)
            score += card.getValue();
    }

    public static void dealersTurn(){
        dealersHand.get(1).setCardState(ICardStates.State.SHOWN);
        score += dealersHand.get(1).getValue();
        for(ClientHandler client: clients) {
            client.showHand("Dealer show second card: " + dealersHand.get(1).printCard());
        }
        while(score<17) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dealersHand.add(Deck.getCard());
            score += dealersHand.get(dealersHand.size() - 1).getValue();
            if (score > 21) {
                for(Card aCard: dealersHand){
                    if(aCard.getName().equals("Ace") && aCard.getCardState().equals(ICardStates.State.SHOWN)){
                        score -= 10;
                        aCard.setCardState(ICardStates.State.LOVERED);
                        break;
                    }
                }
            }
            for (ClientHandler client : clients) {
                client.showHand("Dealer get: " + dealersHand.get(dealersHand.size() - 1).printCard());
            }
        }
        if(score > 21){
            for(ClientHandler client: clients) {
                if(client.getScore() <= 21)
                    client.showWhoWin("YOU HAVE WON!!!");
                if(client.getScore() > 21)
                    client.showWhoWin("YOU HAVE LOST");
            }
        }

        if(score <= 21) {
            for (ClientHandler client : clients) {
                if (client.getScore() > score && client.getScore() <= 21)
                    client.showWhoWin("\n\nYOU HAVE WON!!!");
                else if (client.getScore() == score)
                    client.showWhoWin("\n\nYOU HAVE TIED");
                else
                    client.showWhoWin("\n\nYOU HAVE LOST");
            }
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getScore() {
        return score;
    }
    public static void resetScore(){
        score = 0;
    }
}
