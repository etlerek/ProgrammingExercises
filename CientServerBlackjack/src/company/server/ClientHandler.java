package company.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;

    public ClientHandler(Socket client, ArrayList<ClientHandler> clients) throws Exception{
        this.client = client;
        this.clients = clients;
        this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        this.out = new PrintWriter(this.client.getOutputStream(), true);
    }

    // Odsyłanie klientowi czego sobie zażyczył
    @Override
    public void run() {
        try {
            while (true) {

                String clientRequest = in.readLine();
                String outMessage;

                if (clientRequest.equals("1")) {
                    System.out.println("test");
                    printToEveryone("Get: CARD");
                }
                if (clientRequest.equals("2")) {
                    printToEveryone("Passed");
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
}
