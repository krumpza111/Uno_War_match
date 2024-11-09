public class AttackAI extends AI{
    public AttackAI() {

    }

    public Card getPlay(Hand hand, CardPile cardPile) {
        for (int i = 0; i < hand.getSize(); i++) {
            if (hand.get(i) != null) {
                if (cardPile.canPlay(hand.get(i))) {
                    if (hand.get(i).getRankNum() == 10 || (hand.get(i).getSuitName() == "Wild" && hand.get(i).getRankNum() == 1)) {
                        return hand.get(i);
                    }
                }
            }
        }
        // No attack card found
        return hand.get(0);
    }

    public String toString() {
        return "Attack AI";
    }
}
