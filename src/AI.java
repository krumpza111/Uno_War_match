public class AI {
    public AI () {

    }

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

    public String toString() {
        return "Random Card AI";
    }
}
