package company.client;

import company.cards.Hand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 5555);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));

        //dwie karty krupiera przychodza jako string
        String card1 = in.readLine();
        System.out.println(card1);
        String card2 = in.readLine();
        System.out.println(card2);
        Hand dealerHand = new Hand();

        while(true) {

            System.out.print("Type what you want to do:\n" +
                    "1-hit\t 2-pass\t 0-quit");
            String choice = keyboardIn.readLine();
            if(choice.equals("1") || choice.equals("2")){
                out.println(choice);

            String serverInfo = in.readLine();
            System.out.println(serverInfo);

            }

            else if(choice.equals("0"))
                break;

            else{
                System.out.println("Wrong request");
            }
        }

        socket.close();
        System.exit(0);
    }
}
