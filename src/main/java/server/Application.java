package server;

import java.io.IOException;
import java.net.ServerSocket;

/*
 * Class for creating server and thread for receiving requests with created socket
 * */

public class Application {
    public static void main(String[] args) throws IOException {
        // initializing server
        ServerSocket serverSocket = new ServerSocket(9090);

        // thread for creating socket and receiving requests
        Thread thread = new Thread(new ServerSide(serverSocket));
        thread.start();

        System.out.println("Server is ready to service client");
    }

}
