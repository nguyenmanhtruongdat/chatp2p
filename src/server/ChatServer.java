package server;

import java.io.*;
import java.net.*;

public class ChatServer {
    private int portNumber;
    ChatHelper ChatHelper = new ChatHelper();

    public ChatServer(int portNumber) {
        this.portNumber = portNumber;
    }


    public void start() {
        try {
            ServerSocket sSocket = new ServerSocket(portNumber);
            System.out.println("The chat server will now start on: " + portNumber);
            System.out.println("This server logs all chat conversations in the file ChatApp_logFile");

            while (true) {
                Socket bindingPort = sSocket.accept();
                System.out.println("New user is connecting...");

                UserInstance newUser = new UserInstance(bindingPort, ChatHelper);
                ChatHelper.UserThreadAdd(newUser);
                newUser.start();
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}