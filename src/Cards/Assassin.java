package Cards;

import Game.ActionHandler;
import Game.Main;
import Game.Player;

import java.io.IOException;

public class Assassin extends Card{

    public Assassin() {
        super("ASSASSIN", "ASSASSINATE", "");
    }

    public static void assassinate(Player user, Player player_getting_assassinated) throws IOException {
        if (user.getCoins() < 3) return;
        if (ActionHandler.allowTimeForBS_OnAction(user, "ASSASSINATE", 1)) {
            user.setCoins(user.getCoins()-3);
            System.out.println(player_getting_assassinated + " do you want to block with contessa?");
            if (Main.scanner.nextLine().equalsIgnoreCase("yes")) {
                if (ActionHandler.allowTimeForBS_OnBlock(player_getting_assassinated, "ASSASSINATE", 1)) {
                    //nothing happens, the assassination is blocked
                } else {
                    player_getting_assassinated.dropCard(Main.deck);//lose card for assassination
                }
            } else {
                player_getting_assassinated.dropCard(Main.deck);//lose card for assassination
            }
        }
    }
}
