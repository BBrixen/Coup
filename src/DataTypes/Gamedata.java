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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public static Server getServer() {
        return server;
    }

    public static void setServer(Server server) {
        Gamedata.server = server;
    }

    public static Deck getDeck() {
        return deck;
    }

    public static void setDeck(Deck deck) {
        Gamedata.deck = deck;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        Gamedata.scanner = scanner;
    }

    public static int[] getNumber_of_cards() {
        return number_of_cards;
    }

    public static void setNumber_of_cards(int[] number_of_cards) {
        Gamedata.number_of_cards = number_of_cards;
    }

    public static int getNumber_of_total_cards() {
        return number_of_total_cards;
    }

    public static void setNumber_of_total_cards(int number_of_total_cards) {
        Gamedata.number_of_total_cards = number_of_total_cards;
    }

    public static int getCards_per_player() {
        return cards_per_player;
    }

    public static void setCards_per_player(int cards_per_player) {
        Gamedata.cards_per_player = cards_per_player;
    }

    public static int getUniversal_i() {
        return universal_i;
    }

    public static void setUniversal_i(int universal_i) {
        Gamedata.universal_i = universal_i;
    }
}