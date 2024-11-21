// A class representing the MINIMAX card AI player
public class MiniMaxAI extends AI {
    // Value assignments for heuristic
    private static final int WILD_CARD_BONUS = 50;
    private static final int DRAW_TWO_BONUS = 30;
    private static final int SKIP_REVERSE_BONUS = 20;
    private static final int NUMBER_CARD_BASE = 5;

    // Default constructor
    public MiniMaxAI() {}

    /**
     * The method by which the AI decides on a card to play
     * For the MINIMAX AI: Chooses cards based on a heuristic explained in another method below
     * @param hand -- The hand of the AI player
     * @param cardPile -- The card pile being used for the Uno match being played
     * @return -- A card that the AI has chosen, or NULL if no card can be played
     */
    public Card getPlay(GameState state) {
        CardPile cardPile = state.cardPile;
        int bestScore = Integer.MIN_VALUE;
        Card bestCard = null;
        for (int i = 0; i < state.getCurrHand().getSize(); i++) {
            Card cardDrawn = state.getCurrHand().get(i);
            if (cardPile.canPlay(cardDrawn)) {
                int score = minimax(cardDrawn, state, 2, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
                if (score > bestScore) {
                    bestScore = score;
                    bestCard = cardDrawn;
                }
            }
        }
        return bestCard;
    }

    /**
     * Minimax algorithm:
     * Uses alternating MIN and MAX evaluations to choose the best card based on what cards are in the current hand,
     * @param card -- Card being played
     * @param state -- A game state object that carries the cardPile, and hands of both players (including a simulated opponent hand)
     * @param depth -- How many turns do search before returning a value
     * @param isMaxPlayer -- Which player is the current player
     * @return -- the MAX of all values in the hand if MaxPlayer, MIN of al values in the hand if not MaxPlayer
     */
    private int minimax(Card card, GameState state, int depth, boolean isMaxPlayer, int alpha, int beta) {
        if (depth == 0 || state.getCurrHand().getSize() == 0) {
            return evaluateCard(card, state);
        }
        GameState stateCopy = new GameState(state.getCurrHand(), state.getOpponentHand(), state.getCardPile(), state.getDeck());

        if (isMaxPlayer) {
            int maxEval = Integer.MIN_VALUE;
            Hand tempHand = stateCopy.getCurrHand();
            boolean hasPlayableCard = false;
            for (int i = 0; i < tempHand.getSize(); i++) {
                Card playCard = tempHand.get(i);
                if (stateCopy.getCardPile().canPlay(playCard)) {
                    hasPlayableCard = true;
                    if ( (playCard.getSuit() == 5 && playCard.getRankNum() == 1)) {
                        stateCopy.playPlusFour(!isMaxPlayer);
                    } else if (playCard.getRankNum() == 10) {
                        stateCopy.playPlusTwo(!isMaxPlayer);
                    }
                    stateCopy.getCardPile().setTopCard(playCard);
                    stateCopy.getCurrHand().remove(i);
                    int eval = minimax(playCard, stateCopy, depth - 1, false, alpha, beta);
                    maxEval = Math.max(maxEval, eval);
                    alpha = Math.max(alpha, maxEval);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            if (!hasPlayableCard) {
                stateCopy.draw(!isMaxPlayer);
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            Hand tempHand = stateCopy.getOpponentHand();
            boolean hasPlayableCard = false;
            for (int i = 0; i < tempHand.getSize(); i++) {
                Card playCard = tempHand.get(i);
                if (stateCopy.getCardPile().canPlay(playCard)) {
                    if ( (playCard.getSuit() == 5 && playCard.getRankNum() == 1)) {
                        stateCopy.playPlusFour(!isMaxPlayer);
                    } else if (playCard.getRankNum() == 10) {
                        stateCopy.playPlusTwo(!isMaxPlayer);
                    }
                    stateCopy.getCardPile().setTopCard(playCard);
                    stateCopy.getOpponentHand().remove(i);
                    int eval = minimax(playCard, stateCopy, depth - 1, true, alpha, beta);
                    minEval = Math.min(minEval, eval);
                    beta = Math.min(beta, minEval);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            if (!hasPlayableCard) {
                stateCopy.draw(!isMaxPlayer);
            }
            return minEval;
        }
    }

    /**
     * Heuristic for evaluating the choice of the given card
     * @param card -- Card we are checking for
     * @return -- A score or grade of the card
     */
    private int evaluateCard (Card card, GameState state) {
        if (card == null) {
            return 0;
        }

        int score = 0;

        if (card.getSuitName().equals("Wild")) {
            if (card.getRankNum() == 1) {
                score += WILD_CARD_BONUS;
            } else {
                score += WILD_CARD_BONUS - 10;
            }
        }
        score += switch (card.getRankNum()) {
            case 10 -> DRAW_TWO_BONUS;
            case 11, 12 -> SKIP_REVERSE_BONUS;
            default -> NUMBER_CARD_BASE + card.getRankNum();
        };

        return score;
    }

    @Override
    public String toString () {
        return "MINIMAX AI";
    }
}
