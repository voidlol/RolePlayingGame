package Units;

import UnitAttacks.*;

public abstract class CharacterUnit implements Fighter {

    private final String name;
    private int health;
    private int gold;
    private int agility;
    private int strength;
    private int exp;
    private int level;
    private int maxHealth;
    private final int[] EXP_PER_LEVEL = {50, 85, 130, 190, 410};
    private final int MAX_LEVEL = 6;

    public CharacterUnit(String name, int health, int gold, int agility, int strength, int exp, int level) {
        this.name = name;
        this.health = health;
        this.gold = gold;
        this.agility = agility;
        this.strength = strength;
        this.exp = exp;
        this.level = level;
        this.maxHealth = health;
    }

    @Override
    public Damage attack() {
        Damage.DamageType type = (this.agility * 3) > getRandom() ? (getRandom() <= 30 ? Damage.DamageType.CRITICAL : Damage.DamageType.NORMAL) : Damage.DamageType.MISS;
        int amount = 0;
        switch (type) {
            case NORMAL: amount = this.getStrength(); break;
            case CRITICAL: amount = this.getStrength() * 2; break;
        }
        return new Damage(amount, type);
    }

    private int getRandom() {
        return (int) (Math.random() * 100);
    }

    public void addExp(int exp) {
        this.exp += exp;
        if (this.level == MAX_LEVEL && this.exp > EXP_PER_LEVEL[this.level - 2]) {
            this.exp = EXP_PER_LEVEL[this.level - 2];
        } else {
            if (this.exp >= EXP_PER_LEVEL[this.level - 1]) {
                levelUp();
            }
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public int getLevel() {
        return level;
    }

    private void levelUp() {
        this.level++;
        this.agility += 7;
        this.strength += 3;
        this.maxHealth += 40;
        this.health = this.maxHealth;
        System.out.printf("YOU REACHED %d LEVEL!%n" +
                "YOUR AGILITY HAS INCREASED BY 7 (%d).%n" +
                "YOUR STRENGTH HAS INCREASED BY 3 (%d).%n" +
                "YOUR HEALTH HAS INCREASED BY 40 (%d).%n", this.getLevel(), this.getAgility(), this.getStrength(), this.getMaxHealth());
        this.addExp(0);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getGold() {
        return gold;
    }

    public int getAgility() {
        return agility;
    }

    public int getStrength() {
        return strength;
    }

    public int getExp() {
        return exp;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setHealth(int health) {
        if (health > this.maxHealth) this.health = this.maxHealth;
        else this.health = health < 0 ? 0 : health;
    }

    @Override
    public String toString() {
        return String.format("%s. LEVEL %d, %d/%d HP, %d/%d XP, %d GOLD",
                this.name, this.level, this.health, this.maxHealth,
                this.exp, EXP_PER_LEVEL[(this.level == MAX_LEVEL ? this.level - 2 : this.level - 1)], this.gold);
    }
}
