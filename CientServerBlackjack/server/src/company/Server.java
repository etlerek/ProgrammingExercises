package company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        Socket socket = serverSocket.accept();

        System.out.println("Client connected");

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        while(true) {

            String clientRequest = bufferedReader.readLine();

            if (clientRequest.equals("1")){
                printWriter.println("Get: CARD");
            }
            if (clientRequest.equals("2")){
                printWriter.println("Passed");
            }

            printWriter.flush();
        }
    }
}
