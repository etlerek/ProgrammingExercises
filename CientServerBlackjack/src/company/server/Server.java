package company.server;

import company.cards.Card;
import company.cards.Deck;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static ArrayList<ClientHandler> clients = new ArrayList<>();
    public static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static boolean askForClient(Scanner scanner){
        System.out.println("Czy poczekac na kolejnego klienta?(t/n)");
        String choice = scanner.nextLine();
        return choice.toLowerCase(Locale.ROOT).equals("t");
    }

    public static void clientPassed(int id) {
        if(clients.size()-1 == id) {
            //TODO Odpalić trzeba bedzie krupiera tutaj
            System.out.println("Odpalić trzeba bedzie krupiera tutaj");
            clients.get(0).active();
        }
        else {
            clients.get(id + 1).active();
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(5555);
        Scanner scanner = new Scanner(System.in);


        Deck cardDeck = new Deck();


        while(askForClient(scanner)) {
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

        if(clients.size() == 0) {
            System.out.println("Nie podłączono żadnego klienta");
            return;
        }

        clients.get(0).active();

    }
}

