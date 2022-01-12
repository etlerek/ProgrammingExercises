package company.client;

import company.message.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static PlayerState state = PlayerState.WAITING;
    private static int Id = -1;

    public static void main(String[] args) throws Exception {

        int control = 1;
        String tmp;

        Socket socket = new Socket("localhost", 5555);
        ServerHandler server = new ServerHandler(socket);

        new Thread(server).start();

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));

        while(Id < 0){

            tmp = Message.readMessage(in, Message.MessageType.GIVE_CLIENT_ID.getValue());
            if(tmp != null){
                Id = Integer.parseInt(tmp);
                System.out.println("ID klienta to: "+Id);
            }

        }

        while(true) {

            switch (getPlayerState()) {
                case WAITING -> {
                    if(control == 1){
                        System.out.println("Oczekiwanie");
                        control = 0;
                    }

                    tmp = Message.readMessage(in, Message.MessageType.PLAYER_MOVEMENT.getValue());
                    if(tmp != null && Integer.parseInt(tmp) == Id) {
                        setPlayerState(PlayerState.PLAYING);
                        System.out.println("Klient "+Id+" dostał pozwolenie gry");
                    }
                }
                case PLAYING -> {
                    System.out.println("Type what you want to do:\n" +
                            "1-hit\t 2-pass\t 0-quit");
                    String choice = keyboardIn.readLine();
                    if(choice.equals("1")){
                        Message.sendMessage(out, Message.MessageType.PLAYER_MOVEMENT, String.valueOf(Id) + " " + String.valueOf(choice));
                    }
                    if(choice.equals("2")){
                        Message.sendMessage(out, Message.MessageType.PLAYER_MOVEMENT, String.valueOf(Id) + " " + String.valueOf(choice));
                        control = 1;
                        setPlayerState(PlayerState.WAITING);
                    }

                    else if(choice.equals("0"))
                        break;

                    else{
                        System.out.println("Wrong request");
                    }
                }
            }

        }

//        socket.close();
//        System.exit(0);
    }

    public static PlayerState getPlayerState() {
        return state;
    }

    public static void setPlayerState(PlayerState newState) {
        state = newState;
    }
}
