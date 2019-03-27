package Cards;
import Game.Main;
import Game.Player;
import java.util.ArrayList;
public class Deck {
    private ArrayList<Card> cards_in_deck, deadCards, temp_external_cards;
    //temp external cards are used when someone calls BS on someone else,
    //and you cannot immeidately put the cards in the deck,
    //so you put them into the temp deck and when ready put them into the normal deck

    public Deck(int[] number_of_cards) {
        cards_in_deck = new ArrayList<>();
        deadCards = new ArrayList<>();
        temp_external_cards = new ArrayList<>();
        for (int i = 0; i < number_of_cards[0]; i++) {
            cards_in_deck.add(new Ambassador());
        }
        for (int i = 0; i < number_of_cards[1]; i++) {
            cards_in_deck.add(new Assassin());
        }
        for (int i = 0; i < number_of_cards[2]; i++) {
            cards_in_deck.add(new Captain());
        }
        for (int i = 0; i < number_of_cards[3]; i++) {
            cards_in_deck.add(new Contessa());
        }
        for (int i = 0; i < number_of_cards[4]; i++) {
            cards_in_deck.add(new Duke());
        }
        shuffle();
    }

    public ArrayList<Card> getCards_in_deck() {
        return cards_in_deck;
    }
    public void setCards_in_deck(ArrayList<Card> cards_in_deck) {
        this.cards_in_deck = cards_in_deck;
    }
    public ArrayList<Card> getDeadCards() {
        return deadCards;
    }
    public void setDeadCards(ArrayList<Card> deadCards) {
        this.deadCards = deadCards;
    }

    public void shuffle() {
        ArrayList<Card> newCards = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            int one = Main.ran(0, cards_in_deck.size() -1);
            int two = one;
            while (two == one) {
                two = Main.ran(0, cards_in_deck.size() -1);
            }
            Card card_one = cards_in_deck.get(one);
            Card card_two = cards_in_deck.get(two);
            cards_in_deck.set(one,card_two);
            cards_in_deck.set(two, card_one);
        }
    }

    public void deal(Player player) {
        //only deals one card to the player
        //if 2 cards are needed to be given to a player because of calling BS, first shuffle the

        player.getCards().add(cards_in_deck.get(0));
        cards_in_deck.remove(0);
    }

    @Override
    public String toString() {
        String list = "Live cards:\n";
        for (Card card : cards_in_deck) {
            list += card.toString() + "\n";
        }
        list += "Dead cards:\n";
        for (Card card : deadCards) {
            list += card.toString() + "\n";
        }
        return list;
    }

    public static String actionToName(String action) {
        switch (action) {
            case "TAX":
                return "DUKE";
            case "EXCHANGE":
                return "AMBASSADOR";
            case "ASSASSINATE":
                return "ASSASSIN";
            case "STEAL":
                return "CAPTAIN";
        }
        return "CONTESSA";
    }
    public static String nameToAction(String name) {
        switch (name) {
            case "DUKE":
                return "TAX";
            case "AMBASSADOR":
                return "EXCHANGE";
            case "ASSASSIN":
                return "ASSASSINATE";
            case "CAPTAIN":
                return "STEAL";
        }
        return "NO ACTION";
    }
    public static String blockToName(String block) {
        switch (block) {
            case "ASSASSINATE":
                return "CONTESSA";
            case "STEAL (CAPTAIN)":
                return "CAPTAIN";
            case "STEAL (AMBASSADOR)":
                return "AMBASSADOR";
            case "FOREIGN AID":
                return "DUKE";
        }
        return "NO NAME";
    }
}
