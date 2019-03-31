package Servers;

import DataTypes.Data;
import Game.ActionHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class OmniObjectClient {

    static Socket socket;
    static ObjectInputStream in;
    static ObjectOutputStream out;

    public OmniObjectClient() {
        try {
            init();
            continuallyRecieve(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init() throws IOException {
        System.out.println("Connecting...");
        socket = new Socket("localhost", 7777);
        System.out.println("Connected");

        in = new ObjectInputStream(socket.getInputStream());
    }

    public static void sendData(Object data) throws IOException{
        //this creates a new output stream for each new time data is being sent
        out = new ObjectOutputStream(socket.getOutputStream());

        //this sends the data
        out.writeObject(data);
        out.flush();
    }

    public static Object receiveData() throws IOException, ClassNotFoundException{
        return in.readObject();
    }

    public void continuallyRecieve(OmniObjectClient client) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //getting the data from the client
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                        Data data = (Data) in.readObject();
                        if(data.isGameData()) {
                            out = new ObjectOutputStream(socket.getOutputStream());
                            out.writeObject(ActionHandler.parseData((DataTypes.Gamedata) data, client));
                            out.flush();
                        } else {
                            //GUIDataParser
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Client disconnected");
                        System.exit(12);
                    }
                }
            }
        });
        thread.start();
    }

    public static Socket getSocket() {
        return socket;
    }

    public static void setSocket(Socket socket) {
        OmniObjectClient.socket = socket;
    }

    public static ObjectInputStream getIn() {
        return in;
    }

    public static void setIn(ObjectInputStream in) {
        OmniObjectClient.in = in;
    }

    public static ObjectOutputStream getOut() {
        return out;
    }

    public static void setOut(ObjectOutputStream out) {
        OmniObjectClient.out = out;
    }
}
