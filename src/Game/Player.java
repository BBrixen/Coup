package Game;

import Cards.Card;
import Cards.Deck;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private int coins;
    private ArrayList<Card> cards;
    private boolean dead;
    private String name;

    public Player(String name) {
        this.coins = 2;
        cards = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCoins() {
        return coins;
    }
    public void setCoins(int coins) {
        this.coins = coins;
    }
    public ArrayList<Card> getCards() {
        return cards;
    }
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
    public boolean isDead() {
        return dead;
    }
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void dropCard(Deck main_deck) {
        String message = "Drop a card";
        for (int i = 0; i < cards.size(); i++) {
            message +="\n" + (i+1) + cards.get(i);
        }
        System.out.println(message);
        int num = Integer.parseInt(new Scanner(System.in).nextLine()) -1;
        if (num >= cards.size()) {
            num = Main.ran(0, cards.size()-1);
        }
        System.out.println(main_deck);
        cards.get(num).setDead(true);
        main_deck.getDeadCards().add(cards.get(num));
        System.out.println(main_deck);
        cards.remove(num);
    }

    public void replaceCard(String cardname, Deck main_deck) {
        Card card = null;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equalsIgnoreCase(cardname)) {
                card = cards.get(i);
                break;
            }
        }
        if (card == null) return; //this is an error, the person did not have the card
        main_deck.shuffle();
        main_deck.deal(this);
        main_deck.getCards_in_deck().add(card);
        cards.remove(card);
    }

    public String turn() {
        if (coins >= 10) return "COUP";
        String choice = "";
        System.out.println("\n\n" + this);
        System.out.println("it is your turn"
                    + "\n1. Income"
                    + "\n2. Forgeign Aid"
                    + "\n3. Exchange"
                    + "\n4. Tax"
                    + "\n5. Assassinate"
                    + "\n6. Steal"
                    + "\n7. Coup");
        try {
            int input = Integer.parseInt(Main.scanner.nextLine());
            switch (input) {
                    case 1:
                        return "INCOME";
                    case 2:
                        return "FOREIGN AID";
                    case 3:
                        return "EXCHANGE";
                    case 4:
                        return "TAX";
                    case 5:
                        if(coins >= 3) {
                            return "ASSASSINATE";
                        } else {
                            System.out.println("You do not have enough coins to assassinate");
                            return turn();
                        }
                    case 6:
                        return "STEAL";
                    case 7:
                        if (coins >= 7) {
                            return "COUP";
                        } else {
                            System.out.println("You do not have enough coins to coup");
                            return turn();
                        }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "INCOME";
    }

    @Override
    public String toString() {
        String message = "Name: " + name +"\ncoins: " + coins;
        for (Card card : cards) {
            message += "\n" + card.toString();
        }
        return message;
    }
}
