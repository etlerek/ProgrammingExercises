package company.server;

import company.cards.Card;

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

        while(true) {
            System.out.println("Waiting for client");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            try {
                ClientHandler client = new ClientHandler(socket, clients);
                clients.add(client);
                pool.execute(client);

            } catch (Exception e) {
                System.out.println("CREATING THREAT " + e.getMessage());
            }

        }

//        //Out String wychodzący, in String przychodzący
//        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
//        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//
//        //losowanie dwóch pierwszych kart krupiera
//        Card card1 = new Card("Ace", 11, "Spades");
//        out.println(card1.printCard());
//        Card card2 = new Card("Ace", 11, "Hearts");
//        out.println(card2.printCard());
    }
}
