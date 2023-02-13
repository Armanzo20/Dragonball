package character;


import java.util.Random;
import attack.Attack;

public class Character {

    private final Race race;
    private final String name;
    private final int lifePoints;
    private int currentLifePoints;
    private final double physicalEndurance;
    private final double physicalPower;
    private final double dodgeChance;
    private final Attack[] specialAttacks;
    private final Attack baseAttack;


    public enum Race {
        HUMAN, SAYAN, NAMECCIAN, CYBORG, DEMON, ALIEN
    }

    public Character(Race race, String name, int lifePoints, double physicalEndurance, double dodgeChance, Attack[] specialAttacks, Attack baseAttack) {
        this.race = race;
        this.name = name;
        this.lifePoints = lifePoints;
        this.currentLifePoints = lifePoints;
        this.physicalEndurance = physicalEndurance;
        this.physicalPower = baseAttack.getDamage();
        this.dodgeChance = dodgeChance;
        this.specialAttacks = specialAttacks;
        this.baseAttack = baseAttack;
    }

    //ritorna un attacco randomico
    public Attack getRandomAttack() {
        Random random = new Random();
        var casualIndex = random.nextInt(5);//genera un numero casuale [0,5)
        if (casualIndex == 4) {
            return baseAttack;
        }
        return specialAttacks[casualIndex];
    }

    //esegue una sottrazione algebrica tra puntiVita e vita del personaggio
    public void decrementLifePoint(int damage) {
        setCurrentLifePoints(this.currentLifePoints - damage);
    }

    //reimposta la vita al massimo
    public void resetLife() {
        setCurrentLifePoints(lifePoints);
    }

    private void setCurrentLifePoints(int currentLifePoints) {
        if (currentLifePoints <= 0) {
            currentLifePoints = 0;
        }
        this.currentLifePoints = currentLifePoints;
    }

    public Race getRace() {
        return race;
    }

    public String getName() {
        return name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public int getCurrentLifePoints() {
        return currentLifePoints;
    }

    public double getPhysicalEndurance() {
        return physicalEndurance;
    }


    public double getDodgeChance() {
        return dodgeChance;
    }

    public Attack[] getSpecialAttacks() {
        return specialAttacks;
    }

    public Attack getBaseAttack() {
        return baseAttack;
    }

    @Override
    public String toString() {
        return "Character{" +
                "race=" + race +
                ", name='" + name + '\'' +
                '}';
    }
}
