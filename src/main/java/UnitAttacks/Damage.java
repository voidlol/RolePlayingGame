package UnitAttacks;

public class Damage {

    public enum DamageType {
        CRITICAL("CRITICAL HIT"), NORMAL("NORMAL HIT"), MISS("YOU MISSED");

        private final String name;
        DamageType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    private final int amount;
    private final DamageType type;

    public Damage(int amount, DamageType type) {
        this.amount = amount;
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public DamageType getType() {
        return type;
    }
}
