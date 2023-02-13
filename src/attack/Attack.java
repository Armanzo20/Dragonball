package attack;

import java.util.Random;

public class Attack {

    private final String name;
    private final int damage;
    private final double damageChance;

    public Attack(String name, int damage, double damageChance) {
        this.name = name;
        this.damage = damage;
        this.damageChance = damageChance;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public double getDamageChance() {
        return damageChance;
    }
}
