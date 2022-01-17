package Game.Logic;

import Game.BattleGround.BattleGround;
import Game.BattleGround.FightCallback;
import InputOutput.ConsoleInput;
import InputOutput.Input;
import Menu.Menu;
import Units.*;

public class MainGame {

    private final Input input = new ConsoleInput();
    private final BattleGround bg = new BattleGround();
    private Player player = null;

    private final Menu menu = new Menu(this);
    private final Merchant merchant = new Merchant(this);

    public static void main(String[] args) {
        new MainGame().init();
    }

    public void startBattle() {
        bg.fight(player, createMonster(), new FightCallback() {
            @Override
            public void win() {
                inMenu(Menu.MenuType.SUBMENU);
            }

            @Override
            public void lose() {
                String inputString = "";
                do {
                    inputString = input.getString("Хотите начать сначала? (y\\n)");
                } while (!(inputString.equals("n") || inputString.equals("y")));
                if (inputString.equals("y")) {
                    init();
                } else {
                    System.exit(1);
                }
            }
        });
    }

    public void init() {
        player = new Player(input.getString("Введите ваше имя: "));
        inMenu(Menu.MenuType.MAIN);
    }

    public void inMenu(Menu.MenuType type) {
        menu.showMenu(System.out::println, type);
        menu.select(input.getMenuItem("Выберите пункт меню: ", menu.getRange()));
    }

    private CharacterUnit createMonster() {
        int randomValue = (int) (Math.random() * 100);
        if (randomValue > 50) {
            return new Skeleton("Skeleton", 125, 15, 33, 15, 21, 1);
        } else
            return new Goblin("Goblin", 200, 35, 9, 36, 35, 1);
    }

    public Player getPlayer() {
        return player;
    }

    public Merchant getMerchant() {
        return this.merchant;
    }
}
