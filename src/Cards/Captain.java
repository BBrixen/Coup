package Cards;

import Game.ActionHandler;
import Game.Main;
import Game.Player;

import java.io.IOException;

public class Captain extends Card{

    public Captain() {
        super("CAPTAIN", "STEAL", "STEAL");
    }

    public static void steal(Player theif, Player victim, int intensity) throws IOException {
        if (ActionHandler.allowTimeForBS_OnAction(theif, "STEAL", intensity)) {
            System.out.println(victim.getName() + " what do you want to do?"
            + "\n1. Dont Block"
            + "\n2. Block with captain"
            + "\n3. Block with ambassador");
            int input = Integer.parseInt(Main.scanner.nextLine());
            int block_intensity = 0;
            if (input == 2 || input == 3) {
                System.out.println("How many cards of that type do you have to block with");
                block_intensity = Integer.parseInt(Main.scanner.nextLine());
                String card_type = "AMBASSADOR";
                if (input == 2) {
                    card_type = "CAPTAIN";
                }
                if (!ActionHandler.allowTimeForBS_OnBlock(victim, "STEAL (" + card_type + ")", block_intensity)) {
                    block_intensity = 0;
                }
            }
            if (block_intensity < intensity) {
                int coins = intensity - block_intensity + 1;
                if (coins > victim.getCoins()) {
                    coins = victim.getCoins();
                }
                victim.setCoins(victim.getCoins() - coins);
                theif.setCoins(theif.getCoins() + coins);
            }
        }
    }
}
