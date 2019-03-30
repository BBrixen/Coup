package Servers;

import DataTypes.Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class OmniObjectServer {

    static ServerSocket serverSocket;
    static ArrayList<Socket> sockets = new ArrayList<>();

    public OmniObjectServer() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init() throws Exception {
        System.out.println("Starting Server");
        serverSocket = new ServerSocket(7777);
        System.out.println("Server Started");

        while (sockets.size() < 4) {
            clientJoins();
        }
    }

    public static void clientJoins() throws IOException{
        Socket socket;
        ObjectOutputStream out;


        socket = serverSocket.accept();
        System.out.println("Client Connected, address: " + socket.getInetAddress());
        sockets.add(socket);


        //recieving client data
        //new thread for each client so that all of them can send data at the same time
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //getting the data from the client
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                        Object data = (Object) in.readObject();
                        System.out.println(data);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Client disconnected");
                        System.exit(12);
                    }
                }
            }
        });
        thread.start();
    }

    public static void sendData(Data data) throws IOException {
        Socket client = data.getRecipient();
        ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
        out.writeObject(data);
        out.flush();
    }
}
