/**
 * @author -- Kyle Rumpza
 * @version -- 1.1
 */

/*
This the main class for the UnoWarMatch project this initializes the AI players and runs 2-player matches with N playthroughs
n -- Number of playthroughs/simulations to run
 */
public class Tournament {
    public Tournament () {

    }

    public static void main(String [] args) {
        AI rand = new AI();
        AI b = new BiggestCardAI();
        AI a = new AttackAI();
        AI minimax = new MiniMaxAI();

        int n = 10;
        System.out.println("Playouts of AI in Uno");
        System.out.println("Random Card AI vs. Random Card AI winrate: " + (new UnoWarMatch(rand, rand)).playGame(n));
        System.out.println("Random Card AI vs. Biggest Card Ai windrate " + (new UnoWarMatch(rand, b)).playGame(n));
        System.out.println("Random Card AI vs. Attack Card AI winrate " + (new UnoWarMatch(rand, a)).playGame(n));
    }
}
