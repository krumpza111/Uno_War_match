// A class representing the ATTACK card AI player
public class AttackAI extends AI{
    // Default constructor
    public AttackAI() {

    }

    /**
     * The method by which the AI decides on a card to play
     * For the ATTACK AI: cards are chosen if they can be played...
     *  Prefers playing WILD +4 and +2 cards any chance given
     * @param hand -- The hand of the AI player
     * @param cardPile -- The card pile being used for the Uno match being played
     * @return -- A card that the AI has chosen, or NULL if no card can be played
     */
    public Card getPlay(Hand hand, CardPile cardPile) {
        int bestIndex = -1;
        for (int i = 0; i < hand.getSize(); i++) {
            Card currentCard = hand.get(i);
            if (currentCard != null && cardPile.canPlay(currentCard)) {
                if (hand.get(i).getRankNum() == 10 || (currentCard.getSuitName().equals("Wild") && currentCard.getRankNum() == 1)) {
                    hand.remove(currentCard);
                    return currentCard;
                } else {
                    bestIndex = i;
                }
            }
        }
        // No attack card found
        if (bestIndex >= 0) {
            Card temp = hand.get(bestIndex);
            hand.remove(bestIndex);
            return temp;
        }
        return null;
    }

    /**
     * @return -- String telling which AI the player is
     */
    public String toString() {
        return "Attack AI";
    }
}
