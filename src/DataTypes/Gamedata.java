package DataTypes;

import Cards.Deck;
import Game.Player;
import Servers.Server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Gamedata extends Data {

    private ArrayList<Player> players;
    public static Server server;
    public static Deck deck;
    public static Scanner scanner;
    private static int number_of_cards[] = new int[5];
    private static int number_of_total_cards,
            cards_per_player, universal_i = 0;

    public Gamedata(String message, Socket recipient, boolean isGameData, boolean isReturned, boolean isUpdate) {
        super(message, recipient, isGameData, isReturned, isUpdate);
    }
}
