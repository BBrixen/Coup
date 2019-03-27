package Game;
import Cards.Card;
import Cards.Deck;
import java.io.IOException;
public class ActionHandler {

    public static boolean allowTimeForBS_OnAction(Player player, String action, int intensity) throws IOException {
        //returns a boolean to tell if the action, which was challenged, went through
        //an action goes through if the player actually had the card and wasnt lying
        for (Player p : Main.players) {
            if (player==p) continue;
            System.out.println(p.getName() + ": " + player.getName() + " preformed " + action + " with "+ intensity +" " + Deck.actionToName(action) +"(s), do you want to call BS?");
            if (Main.scanner.nextLine().equalsIgnoreCase("yes")) { //chage to be client based action
                //they called BS
                return BS(player, p, action);
                //this ends the loop of calling BS, and it returns if the action was allowed or not
            }
        }
        return true; //returning true means the action was valid, since nobody called bs
    }
    public static boolean allowTimeForBS_OnBlock(Player blocker, String block, int intensity) throws IOException {
        //returns a boolean to tell if the block, which was challenged, went through
        //an action goes through if the player actually had the card and wasnt lying
        for (Player p : Main.players) {
            if (blocker==p) continue;
            System.out.println(p.getName() + ": " + blocker.getName() + " is preforming block " + block + " with " + intensity + " "+Deck.blockToName(block)+"(s), do you want to call BS?");
            if (Main.scanner.nextLine().equalsIgnoreCase("yes")) {
                return BS_Block(blocker, p, block);
            }
        }
        return true; //the block went through
    }

    public static boolean BS(Player user, Player caller, String action) {
        if (!hasCardWithAction(user, action)) {
            System.out.println(user.getName() + " did not have the card " + Deck.actionToName(action) + " required for " + action + " and therefore lost a card");
            //the user does not have the card needed to preform the action, they were lying and lose a card
            user.dropCard(Main.deck);
            return false; //the action is stopped
        } else {
            //the player was being truthful and gets a new card
            System.out.println(user.getName() + " did have the card " + Deck.actionToName(action) + " required for " + action + " and therefore got a new card");
            System.out.println(caller.getName() + " must lose a card for falsly calling BS");
            caller.dropCard(Main.deck);
            user.replaceCard(Deck.actionToName(action), Main.deck);
            return true; //the action still goes through
        }
    }
    public static boolean BS_Block(Player user, Player caller, String block) {
        if (!hasCardWithBlock(user, block)) {
            System.out.println(user.getName() + " did not have the card " + Deck.blockToName(block) + " required to block " + block + " and therefore lost a card");
            //the user does not have the card needed to preform the action, they were lying and lose a card
            user.dropCard(Main.deck);
            return false; //the block is stopped
        } else {
            //the player was being truthful and gets a new card
            System.out.println(user.getName() + " did have the card " + Deck.blockToName(block) + " required to block " + block + " and therefore got a new card");
            System.out.println(caller.getName() + " must lose a card for falsly calling BS");
            caller.dropCard(Main.deck);
            user.replaceCard(Deck.blockToName(block), Main.deck);
            return true; //the block still goes through
        }
    }

    public static boolean hasCardWithName(Player user, String name) {
        for (Card card : user.getCards()) {
            if (card.getName().equalsIgnoreCase(name) && !card.isDead()) return true;
        }
        return false;
    }
    public static boolean hasCardWithAction(Player user, String action) {
        return hasCardWithName(user, Deck.actionToName(action));
    }
    public static boolean hasCardWithBlock(Player user, String block) {
        return hasCardWithName(user, Deck.blockToName(block));
    }
}
