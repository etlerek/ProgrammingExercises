package company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);

        System.out.println("Waiting for client");

        Socket client = serverSocket.accept();
        System.out.println("Client connected");

        //Out String wychodzący, in String przychodzący
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        //losowanie dwóch pierwszych kart krupiera
        Card card1 = new Card("Ace", 11, "Spades");
        out.println(card1.printCard());
        Card card2 = new Card("Ace", 11, "Hearts");
        out.println(card2.printCard());

        // Odsyłanie klientowi czego sobie zażyczył
        while(true) {

            String clientRequest = in.readLine();

            if (clientRequest.equals("1")){
                out.println("Get: CARD");
            }
            if (clientRequest.equals("2")){
                out.println("Passed");
            }
        }
    }
}
