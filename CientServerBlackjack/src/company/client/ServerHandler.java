package company.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable{

    private Socket server;
    private BufferedReader in;
    private PrintWriter out;

    public ServerHandler(Socket server) throws IOException {
        this.server = server;
        this.in = new BufferedReader(new InputStreamReader(this.server.getInputStream()));
        this.out = new PrintWriter(this.server.getOutputStream(), true);
    }

    @Override
    public void run() {
        try{
            while(true) {
                String serverInfo = in.readLine();
                System.out.println(serverInfo);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally{
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
