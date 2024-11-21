// A class representing the BIGGEST card AI player
public class BiggestCardAI extends AI{
    // Default constructor
    public BiggestCardAI () {

    }

    /**
     * The method by which the AI decides on a card to play
     * For the BIGGEST AI: cards are chosen if they can be played...
     *  if there are multiple options of a Card to play the AI will choose the card with the highest rank
     *  This will include Reverse -> skip -> +2 -> Nine
     * @return -- A card that the AI has chosen, or NULL if no card can be played
     */
    public Card getPlay(GameState state) {
        Hand hand = state.getCurrHand();
        CardPile cardPile = state.getCardPile();
        Card biggest = null;

        for (int i = 0; i < hand.getSize(); i++) {
            if (hand.get(i) != null) {
                if (biggest == null && cardPile.canPlay(hand.get(i))) {
                    biggest = hand.get(i);
                } else if (cardPile.canPlay(hand.get(i)) && hand.get(i).getRankNum() > biggest.getRankNum()) {
                    biggest = hand.get(i);
                }
            }
        }

        if (biggest == null) {
            // No card found
            return null;
        } else {
            hand.remove(biggest);
            return biggest;
        }
    }

    /**
     * @return -- String telling which AI the player is
     */
    public String toString() {
        return "Biggest Card AI";
    }
}
