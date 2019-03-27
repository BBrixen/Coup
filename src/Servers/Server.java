package Servers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private ArrayList<Socket> sockets = new ArrayList<>();
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public ArrayList<Socket> getSockets() {
        return sockets;
    }
    public void setSockets(ArrayList<Socket> sockets) {
        this.sockets = sockets;
    }

    public Server() throws Exception{
        System.out.println("Starting Server");
        serverSocket = new ServerSocket(7777);
        System.out.println("Server Started");
        String message = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Ready for next client?");
            if (scanner.nextLine().equalsIgnoreCase("ter")) {
                break;
            }
            System.out.println("Waiting for client to connect");
            clientJoins();
        }
        System.out.println("All clients connected");
    }

    public void clientJoins() throws IOException{
        Socket socket = serverSocket.accept();
        System.out.println("Client Connected, address: " + socket.getInetAddress());
        sockets.add(socket);
        //recieveData(socket) in this case is taking the player name
        System.out.println("Created new player");
    }

    public String recieveData(Socket socket) {
        try {
            return (new DataInputStream(socket.getInputStream())).readUTF();
        } catch (IOException  e) {
            System.out.println("Client disconnected, address: " + socket.getInetAddress());
            return null;
        }
    }

    public void sendData(Socket socket, String data) throws IOException{
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(data);
        out.flush();
    }
}