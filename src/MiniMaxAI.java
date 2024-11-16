// A class representing the MINIMAX card AI player
public class MiniMaxAI extends AI {
    // Value assignments for heuristic
    private static final int WILD_CARD_BONUS = 50;
    private static final int DRAW_TWO_BONUS = 30;
    private static final int SKIP_REVERSE_BONUS = 20;
    private static final int NUMBER_CARD_BASE = 10;

    // Default constructor
    public MiniMaxAI() {}

    /**
     * The method by which the AI decides on a card to play
     * For the MINIMAX AI: Chooses cards based on a heuristic explained in another method below
     * @param hand -- The hand of the AI player
     * @param cardPile -- The card pile being used for the Uno match being played
     * @return -- A card that the AI has chosen, or NULL if no card can be played
     */
    public Card getPlay(Hand hand, CardPile cardPile) {
        int bestScore = Integer.MIN_VALUE;
        Card bestCard = null;
        for (int i = 0; i < hand.getSize(); i++) {
            if (cardPile.canPlay(hand.get(i))) {
                int score = minimax(hand.get(i), hand, 2, true);
            }
        }
        return bestCard;
    }

    /**
     * Minimax algorithm:
     * Uses alternating MIN and MAX evaluations to choose the best card based on what cards are in the current hand,
     * @param card -- Card being played
     * @param hand -- Current players hand
     * @param depth -- How many turns do search before returning a value
     * @param isMaxPlayer -- Which player is the current player
     * @return -- the MAX of all values in the hand if MaxPlayer, MIN of al values in the hand if not MaxPlayer
     */
    private int minimax(Card card, Hand hand, int depth, boolean isMaxPlayer) {
        if (depth == 0 || hand.getSize() == 0) {
            return evaluateCard(card);
        }

        if (isMaxPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (int i = 0; i < hand.getSize(); i++) {
                int eval = minimax(hand.get(i), hand, depth - 1, false);
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int i = 0; i < hand.getSize(); i++) {
                int eval = minimax(hand.get(i), hand, depth - 1, true);
                minEval = Math.min(minEval, eval);
            }
            return minEval;
        }
    }

    /**
     * Heuristic for evaluating the choice of the given card
     * @param card -- Card we are checking for
     * @return -- A score or grade of the card
     */
    private int evaluateCard (Card card) {
        if (card.getSuitName().equals("Wild")) {
            if (card.getRankName().equals("+4")) {
                return WILD_CARD_BONUS;
            }
            return WILD_CARD_BONUS - 15;
        }
        switch (card.getRankName()) {
            case "+2":
                return DRAW_TWO_BONUS;
            case "Skip":
            case "Reverse":
                return SKIP_REVERSE_BONUS;
            default:
                return NUMBER_CARD_BASE + card.getRankNum();
        }
    }
}
