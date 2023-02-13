package battle;

import java.util.Random;
import attack.Attack;
import character.Character;

public class Battle {

    private final Character player1;
    private final Character player2;
    private Character attacker;
    private Character defender;


    public Battle(Character player1, Character player2) {
        this.player1 = player1;
        this.player2 = player2;
    }



    //stampa punteggio dei due personaggi
   private void printPoints() {
       System.out.printf("Life points %s: %d  ---  Life points %s: %d\n",
               player1.getName(),player1.getCurrentLifePoints(),
               player2.getName(),player2.getCurrentLifePoints());
   }


   //simula una battaglia stampando du System.out i passaggi della lotta
   // richiede come parametro i millisecondi da inserire in Thread.sleep(millisecondi)
   // restituisce il vincitore della battaglia
    public Character startToBattle(int delay) throws InterruptedException {
        firstPlayer();
        while (true){
            printPoints();
            Thread.sleep(delay);

            Attack attack = attacker.getRandomAttack();
            System.out.println(attacker.getName() + ": attack with " + attack.getName());
            Thread.sleep(delay);

            int damage = successfulAttack(attack);
            if (damage != -1) {
                System.out.println(attacker.getName() + " hit " + defender.getName() + " damage inflicted \uD83D\uDCA5 --> " + damage);
                defender.decrementLifePoint(damage);
            } else {
                System.out.println(defender.getName() + " dodged the attack \u270B");
            }
            if (checkWinner()) {
                printPoints();
                reset();
                System.out.println("The winner is " + attacker.getName());
                return attacker;
            } else {
                System.out.println();
                switchTurn();
            }
        }


    }

    // assegna in maniera random un personaggio alla variabile attacker che rappresenta
    // il giocatore che deve attaccare
    private void firstPlayer() {
        Random random = new Random();
        if (random.nextBoolean()) {
            attacker = player1;
            defender = player2;
        } else {
            attacker = player2;
            defender = player1;
        }
    }

    // ritorna il danno inflitto al difensore oppure -1 se l'attacco non è andato a segno

    // la probabilità di andare a segno è calcolata come:
    // (probabilità attacco) * (1 - probabilità parata) --> es: 0.90 * (1 - 0.30) = 0.63 --> più probabilità di parare è alta
    // più la probabilità di andare a segno diminuisce;
    public int successfulAttack(Attack attack) {
       int chanceSuccessful = (int) ((attack.getDamageChance() * (1 - defender.getDodgeChance())*100));
        //System.out.println((attack.getDamageChance() * (1 - defender.getDodgeChance())*100) + " , " + chanceSuccessful);
        //System.out.println("chanceSuccessful: " + chanceSuccessful);
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        //System.out.println("randomNumber: " + randomNumber);
        if (randomNumber <= chanceSuccessful) {
            return damageCalculator(attack.getDamage(),defender.getPhysicalEndurance());
        }
        return -1;
    }

    // ritorna true se l'attaccante ha sconfitto il difensore
    private boolean checkWinner() {
        if (defender.getCurrentLifePoints() <= 0) {
            return true;
        }
        return false;
    }

    //ritorna il danno inflitto al difensore

    //il danno è calcolato come:
    // (danno attacco) * (resistenza difensore) --> es: (30) * (0.3) = 9
    private int damageCalculator(int attackDamage, double defenderEndurance) {
        //System.out.println("damage: " + attackDamage * defenderEndurance);
        return (int) (attackDamage * defenderEndurance);
    }

    //resetta la vita di entrambi i personaggi
    private void reset() {
        player2.resetLife();
        player1.resetLife();
    }


    public void switchTurn() {
        Character tmp = attacker;
        attacker = defender;
        defender = tmp;
    }

}
