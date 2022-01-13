package company.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private ClientState state;
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;
    private static int nextId = 0;
    private int clientId;

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
        try {
            while (true) {
                if(in.ready()){
                    if(state == ClientState.PASSIVE) {
                        out.println("jestes pasywny");
                        in.readLine();
                    }
                    else {
                        String clientRequest = in.readLine();
                        out.println(("Type what you want to do:\n" +
                                "1-hit\t 2-pass\t 0-quit"));
                        printToEveryone("Player " + clientId + ":");
                        if (clientRequest.equals("1")) {
                            printToEveryone("Get: CARD");
                        }
                        if (clientRequest.equals("2")) {
                            printToEveryone("Player: " + clientId + "Passed");
                            Server.clientPassed(clientId);
                            state = ClientState.PASSIVE;
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
        out.println(("Type what you want to do:\n" +
                "1-hit\t 2-pass\t 0-quit"));
    }

    public boolean isActive(){
        return state == ClientState.ACTIVE;
    }
}
