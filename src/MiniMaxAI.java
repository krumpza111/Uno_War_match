public class MiniMaxAI extends AI {
    private static final int WILD_CARD_BONUS = 50;
    private static final int DRAW_TWO_BONUS = 30;
    private static final int SKIP_REVERSE_BONUS = 20;
    private static final int NUMBER_CARD_BASE = 10;

    public MiniMaxAI() {}

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
