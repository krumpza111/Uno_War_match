import java.util.Random;

public class CardPile {
    private Card topCard;
    private int tracker = 1; // tracks placement in deck in case we need to shuffle
    private Card[] discardedCards = new Card[55];

    public CardPile (Card topCard) {
        this.topCard = topCard;
    }

    public boolean canPlay(Card card) {
        if (card == null) {
            return false;
        } else {
            return this.topCard.equals(card);
        }
    }

    public void play(Card card) {
        if (!this.canPlay(card)) {
            System.out.println("Error: Cannot play card");
        } else {
            this.topCard = card;
            discardedCards[tracker - 1] = card;
            this.tracker++;
        }
    }

    public int getNumCards() {
        return this.tracker;
    }

    public Card getTopCard() {
        return this.topCard;
    }

    public void reshuffle() {
        for (int i = this.discardedCards.length - 1; i >= 0; i--) {
            Random rand = new Random();
            int j = rand.nextInt();
            Card temp = this.discardedCards[i];
            this.discardedCards[i] = this.discardedCards[j];
            this.discardedCards[j] = temp;
        }
        this.tracker = 1;
    }

    public Card[] getDiscardedCards () {
        return this.discardedCards;
    }
}
