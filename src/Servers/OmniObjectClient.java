package Servers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class OmniObjectClient {

    static Socket socket;
    static ObjectInputStream in;
    static ObjectOutputStream out;

    public static void main(String[] args) throws Exception {
        System.out.println("Connecting...");
        socket = new Socket("localhost", 7777);
        System.out.println("Connected");

        in = new ObjectInputStream(socket.getInputStream());


//        sendData(new TestObject(57, "favorite"));
//        System.out.println(receiveData());
//        continuallySendData();
//        continuallyRecieveData();
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

}
