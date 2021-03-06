package company.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 5555);
        ServerHandler server = new ServerHandler(socket);

        new Thread(server).start();

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            if(in.ready()){
            }

            String choice = keyboardIn.readLine();
            if(choice.equals("1") || choice.equals("2")){
                out.println(choice);
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
