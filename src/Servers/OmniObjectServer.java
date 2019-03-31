package Servers;

import DataTypes.Data;
import DataTypes.Gamedata;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class OmniObjectServer {

    public static ServerSocket serverSocket;
    public static ArrayList<Socket> sockets = new ArrayList<>();
    public static Gamedata current_gamedata = null;

    public OmniObjectServer(Gamedata gamedata) {
        try {
            init(gamedata);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init(Gamedata data) throws Exception {
        System.out.println("Starting Server");
        serverSocket = new ServerSocket(7777);
        System.out.println("Server Started");

        current_gamedata = data;

        Scanner scanner = new Scanner(System.in);
        while (sockets.size() < 4) {

            System.out.println("Ready for next client?");
            if (scanner.nextLine().equalsIgnoreCase("ter")) {
                break;
            }
            System.out.println("Waiting for client to connect");

            Socket newClient = clientJoins();

            data.setReturned(false);
            data.setRecipient(newClient);
            data.setUpdate(true);
            data.setGameData(true);
            sendData(data);
        }
    }

    public static Socket clientJoins() throws IOException{
        Socket socket;
        ObjectOutputStream out;


        socket = serverSocket.accept();
        System.out.println("Client Connected, address: " + socket.getInetAddress());
        sockets.add(socket);

        return socket;
    }

    public static void sendData(Data data) throws IOException {
        Socket client = data.getRecipient();
        ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
        out.writeObject(data);
        out.flush();
        if (data.isReturned()) {
            handleData(recieveData(data.getRecipient()));
        }
    }

    public static Data recieveData(Socket client) throws IOException {
        ObjectInputStream in = new ObjectInputStream(client.getInputStream());
        try {
            return (Data) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void handleData(Data data) {
        if (data.isGameData()) {
            data.setGameData(false);
            data.setUpdate(true);
        }
    }
}
