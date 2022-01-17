package Units;

import Game.Items;
import Game.Logic.MainGame;

public class Merchant implements Seller {

    private final MainGame game;

    public Merchant(MainGame game) {
        this.game = game;
    }

    @Override
    public void sell(Items items) {
        Player player = game.getPlayer();
        if (player.getGold() >= items.getPrice()) {
            player.addPotion();
            player.setGold(player.getGold() - items.getPrice());
            System.out.printf("Вы купили зелье. У вас осталось %d золота.%n", player.getGold());
        } else {
            System.out.println("У вас не достаточно денег.");
        }
    }
}
