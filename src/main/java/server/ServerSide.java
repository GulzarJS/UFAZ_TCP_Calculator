package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Class for creating socket to connect with Clients
 * Runnable, because we are using thread, because we want that server can receive
 * a lot of client and create more than one socket
 * */

public class ServerSide implements Runnable{

    // Declaring server and socket
    private ServerSocket serverSocket;
    private Socket socket;

    /* Constructor of class
     *It will take serverSocket as input, because our goal is creating server in Application part and
     * using it while creation of thread with initializing ServerSide object
     */
    public ServerSide(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    // initializing run method of thread
    @Override
    public void run() {
        try{
            // server will be open always
            while (true){

                // server waits for client
                socket = serverSocket.accept();

                // DataInputStream and DataOutputStream for getting and sending message
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                // getting message
                String msg = dataInputStream.readUTF();

                // OperationImp object because of using methods of this class for doing operations
                OperationImp operationImp = new OperationImp();

                // Doing operation and getting answer
                String answer = operationImp.calculation(msg);


                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                // Sending answer to client
                dataOutputStream.writeUTF(answer);

                System.out.println("Operation is completed ");

                // Closing socket after finishing operations
                socket.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



