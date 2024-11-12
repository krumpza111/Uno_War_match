import java.util.Random;

public class CardPile {
    private Card topCard;
    private int tracker = 0; // tracks index placement in deck
    private Card[] discardedCards = new Card[55];

    public CardPile () {

    }

    public CardPile (Card topCard) {
        this.topCard = topCard;
    }

    public boolean canPlay(Card card) {
        return this.topCard.equals(card);
    }

    public void play(Card card) {
        if (this.canPlay(card)) {

            if (tracker >= 54) {
                System.out.println("Error tracker exceeds possible discard pile length");
                return;
            }
            discardedCards[tracker] = this.topCard;
            this.tracker++;
            this.topCard = card;
        }
    }

    public int getNumCards() {
        return this.tracker;
    }

    public Card getTopCard() {
        return this.topCard;
    }

    public void setTopCard(Card card) {
        this.topCard = card;
    }

    public void reshuffle() {
        Random rand = new Random();
        for (int i = this.tracker; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = this.discardedCards[i];
            this.discardedCards[i] = this.discardedCards[j];
            this.discardedCards[j] = temp;
        }
    }

    public Card[] getDiscardedCards () {
        Card [] activeDiscarded = new Card[tracker];
        int count = 0;
        for (int i = 0; i < tracker; i++) {
            if (discardedCards[i] != null) { //only add if not null
                activeDiscarded[count] = discardedCards[i];
                count++;
            }
        }
        this.tracker = 0;
        this.discardedCards = new Card[55];
        return activeDiscarded;
    }
}
