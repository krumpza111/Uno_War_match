public class AttackAI extends AI{
    public AttackAI() {

    }

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

    public String toString() {
        return "Attack AI";
    }
}
