// A class representing an RANDOM AI player
public class AI {
    // Default constructor
    public AI () {

    }

    /**
     * The method by which the AI decides on a card to play
     * For the RANDOM AI: The first card from the left that can be played is chosen
     * @param hand -- The hand of the AI player
     * @param cardPile -- The card pile being used for the Uno match being played
     * @return -- A card that the AI has chosen, or NULL if no card can be played
     */
    public Card getPlay (Hand hand, CardPile cardPile) {
        for (int i = 0; i < hand.getSize(); i++) {
            Card currentCard = hand.get(i);
            if (currentCard != null && cardPile.canPlay(currentCard)) {
                hand.remove(currentCard);
                return currentCard;
            }
        }
        return null;
    }

    /**
     * @return -- String telling which AI the player is
     */
    public String toString() {
        return "Random Card AI";
    }
}
