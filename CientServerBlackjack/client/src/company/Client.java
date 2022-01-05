package company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 5555);
        Scanner scanner = new Scanner(System.in);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader buff = new BufferedReader(in);

        while(true) {
            System.out.print("Type what you want to do:\n" +
                    "1-hit\t 2-pass\t 0-quit");
            int choice = scanner.nextInt();
            if(choice == 1 || choice == 2 || choice == 0){
                printWriter.println(choice);
                printWriter.flush();

            String serverInfo = buff.readLine();
            System.out.println(serverInfo);

            }
            else{
                System.out.println("Wrong request");
            }
        }
    }
}
