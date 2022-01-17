package Game.BattleGround;

import UnitAttacks.*;
import Units.CharacterUnit;
import Units.Player;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BattleGround {

    private final BlockingQueue<Runnable> fights = new LinkedBlockingQueue<>();

    public BattleGround() {
        Thread worker = new Thread(() -> {
            while (true) {
                try {
                    Runnable task = fights.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        worker.start();
    }

    public void fight(CharacterUnit player, CharacterUnit monster, FightCallback callback) {
        System.out.printf("%s (%d/%d HP) напал на %s (%d/%d HP)%n", player.getName(), player.getHealth(), player.getMaxHealth(),
                                                                    monster.getName(), monster.getHealth(), monster.getMaxHealth());
        try {
            fights.put(() -> {
                boolean isOver = false;
                int turn = 1;
                while (!isOver) {
                    System.out.printf("============ХОД %d============%n", turn);
                    isOver = turn++ % 2 != 0 ? attack(player, monster, callback) : attack(monster, player, callback);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean attack(CharacterUnit attacker, CharacterUnit defender, FightCallback callback) {
        Damage damage = attacker.attack();
        defender.setHealth(defender.getHealth() - damage.getAmount());
        if (damage.getType() != Damage.DamageType.MISS) {
            System.out.printf("%s наносит %s %d урона (%s).%n", attacker.getName(), defender.getName(), damage.getAmount(), damage.getType());
            System.out.printf("У %s осталось %d HP из %d HP%n", defender.getName(), defender.getHealth(), defender.getMaxHealth());
            if (defender.getHealth() <= 0) {
                if (defender instanceof Player) {
                    System.out.println("ВЫ ПОГОИБЛИ!");
                    callback.lose();
                } else {
                    System.out.printf("%s ПОГИБ.%nВЫ ПОЛУЧИЛИ %d XP И %d ЗОЛОТА.%n", defender.getName(), defender.getExp(), defender.getGold());
                    attacker.addExp(defender.getExp());
                    attacker.addGold(defender.getGold());
                    System.out.println(attacker);
                    callback.win();
                }
                return true;
            }
        } else {
            System.out.printf("%s ПРОМАХНУЛСЯ!%n", attacker.getName());
        }
        return false;
    }
}
