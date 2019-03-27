package Game;
import Cards.*;
import Servers.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    //this is an array with the number of each cards
    //0th is ambassador, 1st is assassin, 2nd is captain,
    //3rd is contessa, 4th is duke
    public static int number_of_cards[] = new int[5];
    public static int number_of_total_cards,
            cards_per_player;
    static Random ran = new Random();
    public static ArrayList<Player> players = new ArrayList<>();
    public static Server server;
    public static Deck deck;
    public static Scanner scanner;
    public static int universal_i = 0;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        number_of_cards[0] = 3;
        number_of_cards[1] = 3;
        number_of_cards[2] = 3;
        number_of_cards[3] = 3;
        number_of_cards[4] = 3;
        playersJoin();
        cards_per_player = 2;
        for (int num : number_of_cards) {
            number_of_total_cards += num;
        }
        int cards_required = cards_per_player*players.size();
        if (cards_required > number_of_total_cards - 2) {
            System.out.println("Not enough cards");
            return;
        }

        deck = new Deck(number_of_cards);
        deck.shuffle();
        System.out.println(deck);
        for (int i = 0; i < cards_per_player; i++) {
            //this deals the number of cards per player one at a time, versus all at once
            for (Player p : players) {
                //dealing one card to each player
                deck.deal(p);
            }
        }
        for (Player p : players) {
            System.out.println(p);
        }

        try {
            playGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void playersJoin() {
        String serverSideInput = "", name = "";
        while (true) {
            System.out.println("Ready for next player?");
            serverSideInput = scanner.nextLine();
            if (serverSideInput.equalsIgnoreCase("ter") || serverSideInput.equalsIgnoreCase("no")) return;
            System.out.println("Enter player name");
            name = scanner.nextLine();
            players.add(new Player(name));
        }
    }

    public static void playGame() throws IOException{
        while (players.size() > 1) { //goes through everyone's turn until there is only 1 player left
            for (universal_i = 0; universal_i < players.size(); universal_i++) {
                Player player = players.get(universal_i);

                String action_code = player.turn();
                System.out.print(player.getName() + " is preforming " + action_code);
                if (action_code.equalsIgnoreCase("INCOME")) {
                    player.setCoins(player.getCoins() + 1);
                } else if (action_code.equalsIgnoreCase("FOREIGN AID")) {
                    Duke.foreignAid(player);
                } else if (action_code.equalsIgnoreCase("EXCHANGE")) {
                    Ambassador.exchange(player, deck, 1);
                } else if (action_code.equalsIgnoreCase("TAX")) {
                    Duke.tax(player, 1);
                } else if (action_code.equalsIgnoreCase("ASSASSINATE")) {
                    Player player_getting_assassinated = choosePlayer(player,"ASSASSINATE", 0);
                    System.out.println(" on " + player_getting_assassinated.getName());
                    Assassin.assassinate(player, player_getting_assassinated);
                } else if (action_code.equalsIgnoreCase("STEAL")) {
                    Player victim = choosePlayer(player, "STEAL", 1);
                    System.out.println(" on " + victim.getName());
                    Captain.steal(player, victim, 1);
                } else if (action_code.equalsIgnoreCase("COUP")) {
                    Player victim = choosePlayer(player, "COUP", 0);
                    System.out.println(" on " + victim.getName());
                    player.setCoins(player.getCoins()-7);
                    coup(victim);
                }
                System.out.println();
                update();
            }
        }
    }

    public static void update() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getCards().size() <= 0) {
                players.get(i).setDead(true);
                players.remove(i);
                if (i < universal_i) universal_i--;
                i--;
            }
        }
        //send new object info to every player
    }

    public static int placement(Player player) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) == player) return i;
        }
        return 0;
    }

    public static Player choosePlayer(Player chooser, String action, int min_coin_req) {
        while (true) {
            for (int i = 0; i < players.size(); i++) {
                System.out.println((i + 1) + ": " + players.get(i).getName());
            }
            System.out.println("Enter the number of the player you want to preform " + action + " on.\nYou cannot choose yourself");
            int x = Integer.parseInt(scanner.nextLine());
            if (players.get(x-1).getCoins() < min_coin_req || players.get(x-1).getName().equalsIgnoreCase(chooser.getName())) continue;
            return players.get(x-1);
        }
    }

    public static void coup(Player victim) {
        //add blocking with dauble contessa
        victim.dropCard(deck);
    }

    public static int ran(int min, int max) {
        return ran.nextInt(max - min + 1) + min;
    }

    public void send(String data, Player player) {

    }

    public String recieve(Player player) {
        return "";
    }
}
