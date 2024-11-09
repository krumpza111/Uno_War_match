public class BiggestCardAI extends AI{
    public BiggestCardAI () {

    }

    public Card getPlay(Hand hand, CardPile cardPile) {
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
            System.out.println("Error no card found");
            return null;
        } else {
            hand.remove(biggest);
            return biggest;
        }
    }

    public String toString() {
        return "Biggest Card AI";
    }
}
