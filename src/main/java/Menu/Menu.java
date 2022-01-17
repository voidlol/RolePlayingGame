package Menu;

import Game.Items;
import Game.Logic.MainGame;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Menu {

    public enum MenuType {
        MAIN, SUBMENU, MERCHANT;
    }

    private final Map<Integer, BaseMenuItem> menu = new HashMap<>();
    private final MainGame game;

    public Menu(MainGame game) {
        this.game = game;
    }

    public void select(int key) {
        this.menu.get(key).execute(game);
    }

    public int getRange() {
        return this.menu.size();
    }

    public void showMenu(Consumer<String> out, MenuType type) {
        switch (type) {
            case MAIN: fillMainMenu(); break;
            case SUBMENU: fillSubMenu(); break;
            case MERCHANT: fillMerchantMenu(); break;
        }
        out.accept("==============МЕНЮ==============");
        menu.values().forEach(i -> out.accept(i.toString()));
        out.accept("================================");
    }

    private void fillMainMenu() {
        this.menu.clear();
        this.menu.put(1, new LookForAdventureItem(1, "Искать приключения"));
        this.menu.put(2, new VisitMerchantItem(2, "Пойти к торговцу"));
        this.menu.put(3, new DrinkPotionItem(3, String.format("Выпить зелье (%d)", game.getPlayer().getPotionsAmount())));
        this.menu.put(4, new ExitItem(4, "Выход из игры"));
    }

    private void fillSubMenu() {
        this.menu.clear();
        this.menu.put(1, new ContinueItem(1, "Продолжить приключения"));
        this.menu.put(2, new ReturnToMenuItem(2, "Вернуться в главное меню"));
    }

    private void fillMerchantMenu() {
        this.menu.clear();
        this.menu.put(1, new MerchantBuyItem(1, String.format("Купить зелье (%d монет)", Items.POTION.getPrice())));
        this.menu.put(2, new ReturnToMenuItem(2, "Вернуться в главное меню"));
    }


    private static class MerchantBuyItem extends BaseMenuItem {
        public MerchantBuyItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(MainGame game) {
            game.getMerchant().sell(Items.POTION);
            game.inMenu(MenuType.MERCHANT);
        }
    }

    private static class DrinkPotionItem extends BaseMenuItem {
        public DrinkPotionItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(MainGame game) {
            game.getPlayer().drinkPotion();
            game.inMenu(MenuType.MAIN);
        }
    }

    private static class ContinueItem extends BaseMenuItem {
        public ContinueItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(MainGame game) {
            game.startBattle();
        }
    }

    private static class ReturnToMenuItem extends BaseMenuItem {
        public ReturnToMenuItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(MainGame game) {
            game.inMenu(MenuType.MAIN);
        }
    }

    private static class LookForAdventureItem extends BaseMenuItem {
        public LookForAdventureItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(MainGame game) {
            game.startBattle();
        }
    }

    private static class VisitMerchantItem extends BaseMenuItem {
        public VisitMerchantItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(MainGame game) {
            if (game.getMerchant() != null) {
                game.inMenu(MenuType.MERCHANT);
            } else {
                System.out.println("Торговца еще нет.");
            }
        }
    }

    private static class ExitItem extends BaseMenuItem {
        public ExitItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(MainGame game) {
            System.exit(0);
        }
    }


}
