package Cards;
import Game.ActionHandler;
import Game.Main;
import Game.Player;

import java.io.IOException;
import java.util.ArrayList;

public class Ambassador extends Card {

    public Ambassador() {
        super("AMBASSADOR", "EXCHANGE", "STEAL");
    }

    public static void exchange(Player player, Deck deck, int intensity) throws IOException {
        if (ActionHandler.allowTimeForBS_OnAction(player, "EXCHANGE", intensity)) {
            ArrayList<Card> cards_to_choose_from = new ArrayList<>();
            while (player.getCards().size() > 0) { //adds players current cards to the list
                if (!player.getCards().get(0).isDead()) {// only adds live cards
                    cards_to_choose_from.add(player.getCards().get(0));
                }
                player.getCards().remove(0);
            }
            for (int i = 0; i < (intensity + 1); i++) { //adds however many cards are needed to the list
                System.out.println("\n\nCards in deck:" + deck.getCards_in_deck() + "\n\n");
                cards_to_choose_from.add(deck.getCards_in_deck().get(0));
                deck.getCards_in_deck().remove(0);
            }

            while (cards_to_choose_from.size() > (intensity+ 1)) { //players picking their cards one at a time
                System.out.println("Pick a card to keep.\n" + printCardList(cards_to_choose_from));
                int choice = Integer.parseInt(Main.scanner.nextLine()) -1;
                player.getCards().add(cards_to_choose_from.get(choice));
                cards_to_choose_from.remove(choice);
            }

            //this is where the player gets to choose the order the cards go back in the deck
            while (cards_to_choose_from.size() > 1) {
                System.out.println("Now you can choose the order of the cards when they go into the deck");
                System.out.println("Pick the card that will go next\n" + printCardList(cards_to_choose_from));
                int choice = Integer.parseInt(Main.scanner.nextLine());
                deck.getCards_in_deck().add(cards_to_choose_from.get(choice));
                cards_to_choose_from.remove(choice);
            }
            deck.getCards_in_deck().add(cards_to_choose_from.get(0)); //adds the last card to the deck
        }
    }
    public static String printCardList(ArrayList<Card> list) {
        String message = "";
        for (int i = 0; i < list.size(); i++) {
            message+= i + 1 + ": " + list.get(i).getName() + "\n";
        }
        return message;
    }
}
