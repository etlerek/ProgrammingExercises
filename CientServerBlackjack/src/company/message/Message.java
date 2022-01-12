package company.message;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Message {

    public enum MessageType{
        GIVE_CLIENT_ID(0),
        PLAYER_MOVEMENT(1),
        TEXT(2);

        private final int value;

        MessageType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static void sendMessage(PrintWriter out, MessageType type, String text){
        out.print(type.getValue()+ " " +text);
    }

    public static String readMessage(BufferedReader in, int id) throws IOException, InterruptedException {
        if(!in.ready()) {
            Thread.sleep(1000);
        }
        String[] recieved = in.readLine().split(" ", 2);
        int receivedId = Integer.parseInt(recieved[0]);
        if(receivedId == MessageType.TEXT.getValue()){
            System.out.print(recieved[1]);

        }
        else if(receivedId == id)
            return recieved[1];

        return null;
    }
}
