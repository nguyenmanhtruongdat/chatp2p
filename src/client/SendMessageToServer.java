package client;

import java.io.*;
import java.net.*;

public class SendMessageToServer extends Thread {
    private PrintWriter pWriter;
    private Socket socket;
    private ChatClient client;

    public SendMessageToServer(Socket ip, ChatClient client) {
        this.socket = ip;
        this.client = client;

        try {
            OutputStream output = ip.getOutputStream();
            pWriter = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }


    public void run() {

        Console console = System.console();
        //Prompt user to set name
        String setUserName = console.readLine("\nEnter your name: ");
        client.setUserName(setUserName);
        pWriter.println(setUserName);

        String message;

        while(true) {
            message = console.readLine("[" + client.getUserName() + "]: ");
            pWriter.println(message);
            if (message.equals("bye")) {
                try {
                    System.out.println(client.getUserName()+" has left the chat!");
                    socket.close();
                } catch (IOException ex) {
                    System.out.println("Your message may have not made it to our server. Please try again");
                }
            }
        }
    }
}
