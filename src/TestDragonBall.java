import attack.Attack;
import battle.Battle;
import character.Character;

public class TestDragonBall {
    public static void main(String[] args) throws InterruptedException {
        Attack attack1 = new Attack("A", 30, 0.8);
        Attack attack2 = new Attack("B", 15, 0.9);
        Attack attack3 = new Attack("C", 40, 0.7);
        Attack attack4 = new Attack("D", 10, 0.6);
        Attack attack5 = new Attack("E", 50, 0.2);
        Attack attack6 = new Attack("F", 30, 0.9);
        Attack attack7 = new Attack("H", 40, 0.4);
        Attack[] specialAttacks2 = {attack5, attack6, attack7, attack2};
        Attack[] specialAttacks1 = {attack1, attack2, attack3, attack4};

        Character character1 = new Character(Character.Race.HUMAN, "Mario",
                50, 0.3, 0.2, specialAttacks1, attack7);
        Character character2 = new Character(Character.Race.ALIEN, "Ciro",
                50, 0.3, 0.2, specialAttacks2, attack1);

        Character winner;

        //test battle
        Battle battle = new Battle(character1, character2);
        winner = battle.startToBattle(500);
        System.out.println(winner);

        //test reset life point
        System.out.println(character1.getLifePoints() == character1.getCurrentLifePoints());
        System.out.println(character2.getLifePoints() == character2.getCurrentLifePoints());



    }
}