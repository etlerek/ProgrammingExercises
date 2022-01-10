package company.server;

import company.cards.Card;
import company.cards.Deck;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) throws IOException {
        ArrayList<ClientHandler> clients = new ArrayList<>();
        ExecutorService pool = Executors.newFixedThreadPool(4);
        ServerSocket serverSocket = new ServerSocket(5555);

        Deck cardDeck = new Deck();
        cardDeck.showCards();

        while(true) {
            System.out.println("Waiting for client");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            try {
                ClientHandler client = new ClientHandler(socket, clients);
                clients.add(client);
                pool.execute(client);

            } catch (Exception e) {
                System.out.println("CREATING THREAT ERROR" + e.getMessage());
            }

        }
    }
}
