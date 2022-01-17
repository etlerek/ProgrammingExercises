package company.server;

import company.cards.Card;
import company.cards.Deck;
import company.cards.Hand;
import company.cards.ICardStates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable{
    private ClientState state;
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;
    private List<Card> cardsInHand = new ArrayList<>();
    private static int nextId = 0;
    private int score = 0;
    private int clientId;
    private Card card = null;
    private int control = 4;
    private static final String CLEAR = new String(new char[80]).replace("\0", "\n");

    public ClientHandler(Socket client, ArrayList<ClientHandler> clients) throws Exception{
        this.client = client;
        this.clients = clients;
        this.clientId = nextId;
        nextId++;
        state = ClientState.PASSIVE;
        this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        this.out = new PrintWriter(this.client.getOutputStream(), true);
    }

    // Odsyłanie klientowi czego sobie zażyczył
    @Override
    public void run() {
        startOfRound();
        try {
            while (true) {
                if(in.ready()){
                    if(state == ClientState.PASSIVE) {
                        out.println("wait for your move");
                        in.readLine();
                    }
                    else {
                        String clientRequest = in.readLine();
                        if (clientRequest.equals("1")) {
                            card = Deck.getCard();
                            if (card != null)
                            {
                                score += card.getValue();
                                cardsInHand.add(card);
                                while(score > 21 && control > 0) {
                                    for (Card playerCard : cardsInHand){
                                        if (playerCard.getName().equals("Ace") && playerCard.getCardState().equals(ICardStates.State.SHOWN)) {
                                            score -= 10;
                                            playerCard.setCardState(ICardStates.State.LOVERED);
                                            break;
                                        }
                                    }
                                control -= 1;
                                }
                                if (control > 0) {
                                    showHand("Player " + clientId +" get: " + card.printCard() + "| score: " + score);
                                }

                                if (control == 0){
                                    showHand("Player " + clientId +" get: " + card.printCard() + " and LOST | score: " + score);
                                    control = 4;
                                    Server.clientPassed(clientId);
                                    state = ClientState.PASSIVE;
                                    out.println("\nYOU HAVE LOST!!! :(\n");
                                }
                                if(score == 21){
                                    showHand("Player " + clientId +" got 21!!!");
                                    out.println("\nWOOOOW CONGRATULATIONS\n");
                                    control = 4;
                                    Server.clientPassed(clientId);
                                    state = ClientState.PASSIVE;
                                }
                            }

                        }
                        if (clientRequest.equals("2")) {
                            showHand("Player " + clientId +" passed");
                            control = 4;
                            Server.clientPassed(clientId);
                            state = ClientState.PASSIVE;
                            out.println("\nYOU HAVE PASSED\n");
                        }
                    }
                }
            }
        }
        catch (IOException e){
            System.out.println("FROM CLIENT HANDLER " + e.getMessage());
        }
        finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.close();
        }
    }

    //Wyswietlanie do wszystkich klientów
    private void printToEveryone(String outMessage){
        for(ClientHandler client: clients)
            client.out.println(outMessage);
    }

    public void active(){
        System.out.println("TURA GRACZA NR" + clientId);
        state = ClientState.ACTIVE;
    }

    public boolean isActive(){
        return state == ClientState.ACTIVE;
    }

    public void startOfRound(){
        card = Deck.getCard();
        cardsInHand.add(card);
        score += card.getValue();
        card = Deck.getCard();
        cardsInHand.add(card);
        score += card.getValue();
        showHand("");
    }

    public void showHand(String message){
        printToEveryone(CLEAR + message);
        for(ClientHandler aClinet: clients) {
            printToEveryone("-----------------------------------------\nPLAYER " + aClinet.getClientId() + "\t| SCORE: " + aClinet.score);
            for (Card playerCards : aClinet.cardsInHand)
                printToEveryone(playerCards.printCard());
        }

        System.out.println(state);
        if(state != ClientState.PASSIVE) {
            out.println(("\n----------------------- \n" +
                    "Type what you want to do:\n" +
                    "1-hit\t 2-pass\t 0-quit\n" +
                    "----------------------- "));
        }
    }

    public int getClientId() {
        return clientId;
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }
}
