package Cards;
import Game.ActionHandler;
import Game.Main;
import Game.Player;
import java.io.IOException;

public class Duke extends Card {

    public Duke() { super("DUKE", "TAX", "FOREIGN AID"); }

    public static void tax(Player player, int intensity) throws IOException{
        //intensity tells how many dukes they have
        if (ActionHandler.allowTimeForBS_OnAction(player, "TAX", intensity)) {
            player.setCoins(player.getCoins() + intensity+2);
        }
    }

    public static void foreignAid(Player player) throws IOException {
        for (Player p : Main.players) {
            if (p==player) continue;
            System.out.println("Does " + p.getName() + " want to block with duke?");
            if (Main.scanner.nextLine().equalsIgnoreCase("yes")) {
                //they block, now its time to call BS.
                if (ActionHandler.allowTimeForBS_OnBlock(p, "FOREIGN AID", 1)) {
                    //the action was successfully blocked, nothing happens
                    System.out.println(player.getName() + "'s foreign aid was blocked");
                    return;
                } else {
                    //the block was unsuccessful, player gets 2 coins
                    player.setCoins(player.getCoins() + 2);
                    return;
                }
            }
        }
        player.setCoins(player.getCoins() + 2);
    }
}