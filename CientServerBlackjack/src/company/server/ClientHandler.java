package company.server;

import company.cards.Card;
import company.cards.Hand;
import company.client.Client;
import company.message.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private boolean connected = false;

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;
    private Hand hand = null;
    private static int nextId = 0;
    private int clientId;
    private ClientHandler nextHandler = null;

    public ClientHandler(Socket client, ArrayList<ClientHandler> clients) throws Exception{
        this.client = client;
        this.clients = clients;
        this.clientId = nextId;
        nextId++;
        hand = new Hand();
        this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        this.out = new PrintWriter(this.client.getOutputStream(), true);
    }

    // Odsyłanie klientowi czego sobie zażyczył
    @Override
    public void run() {

        Message.sendMessage(out, Message.MessageType.GIVE_CLIENT_ID, String.valueOf(clientId));
        Message.sendMessage(out, Message.MessageType.TEXT, "test " + clientId);
        setConnected(true);
        if(clientId == 0){
            switchTurn();
        }

        try {
            while (true) {

                String clientRequest = Message.readMessage(in, Message.MessageType.PLAYER_MOVEMENT.getValue());
                String[] tmp = clientRequest.split(" ", 2);
                if(Integer.parseInt(tmp[0]) == clientId){
                    Message.sendMessage(out, Message.MessageType.TEXT, "Player " + clientId +":");
                    if (tmp[1].equals("1")) {
                        Message.sendMessage(out, Message.MessageType.TEXT, "GetCard");
                    }
                    if (tmp[1].equals("2")) {
                        Message.sendMessage(out, Message.MessageType.TEXT, "Passed");
                        if(null != nextHandler){
                            nextHandler.switchTurn();
                        }
                    }
                }
            }
        }
        catch (IOException | InterruptedException e){
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

    public void switchTurn(){
        System.out.println("Client Handler " + clientId+ " wysłał pozwolenie na gre" );
        Message.sendMessage(out, Message.MessageType.PLAYER_MOVEMENT, String.valueOf(clientId));
    }

    public boolean isConnected() {
        //System.out.println("ClientHandler " + clientId + " getConnected " + connected);
        return connected;
    }

    public void setConnected(boolean connected) {
        System.out.println("ClientHandler " + clientId + " setConnected " + connected);
        this.connected = connected;
    }

    public ClientHandler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(ClientHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
