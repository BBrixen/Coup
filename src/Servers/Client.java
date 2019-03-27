package Servers;
import java.io.EOFException;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private DataInputStream in;
    static DataOutputStream out;public Socket getSocket() {
        return socket;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public DataInputStream getIn() {
        return in;
    }
    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public void sendData(String data) throws IOException{
        //this creates a new output stream for each new time data is being sent
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(data);
        out.flush();
    }
    public String receiveData() throws IOException, ClassNotFoundException{
        try {
            return (String) in.readUTF();
        } catch (EOFException e) {
            e.printStackTrace();
            System.exit(12);
        }
        return null;
    }

    public Client() throws Exception{
        System.out.println("Connecting...");
        socket = new Socket("localhost", 7777);
        System.out.println("Connected");
        in = new DataInputStream(socket.getInputStream());
    }
}