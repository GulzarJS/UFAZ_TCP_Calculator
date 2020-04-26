package client;

import java.io.*;
import java.net.Socket;

/*
 * This class is for sending and getting message with created socket
 * This class is for doing some client side works
 */

public class ClientSide {

    // DataInputStream and DataOutputStream for getting and sending message
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    // String for holding answer
    private String answer;

    // Default constructor for creating this class's object
    public ClientSide() {
    }


    // Method for sending values and operations to server and getting answer from server
    public String getAnswer(String msg) throws IOException {

        // initializing socket
        Socket socket =new Socket("localhost",9090);

        // sending values and operation => val1 + operation + val2
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(msg);

        // getting answer from server => answer
        dataInputStream = new DataInputStream(socket.getInputStream());
        answer = dataInputStream.readUTF();

        // closing socket
        socket.close();
        return answer;

    }



}

