package Game;

import Servers.Client;

import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Client client = new Client();
        System.out.println("Choose a name");
        client.sendData(scanner.nextLine());
        System.out.println("added to game");
        String out = "", in = "";
        for (;;) {
            in = client.receiveData();
            System.out.println(in);
        }
    }
}
