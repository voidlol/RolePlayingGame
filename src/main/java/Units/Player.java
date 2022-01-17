package Units;

public class Player extends CharacterUnit {

    private int potionsAmount = 0;

    public Player(String name) {
        super(name, 200, 0, 22, 19, 0, 1);
    }

    public int getPotionsAmount() {
        return potionsAmount;
    }

    public void addPotion() {
        potionsAmount++;
    }

    public void drinkPotion() {
        if (potionsAmount > 0) {
            if (super.getHealth() < super.getMaxHealth()) {
                potionsAmount--;
                super.setHealth(super.getHealth() + 75);
                System.out.println("Вы выпили зелье и восстановили 75 ед. здоровья.");
            } else System.out.println("У вас максимальное количество здоровья.");
        } else {
            System.out.println("У вас нет зелий. Вы можете купить их у торговца.");
        }
    }
}
