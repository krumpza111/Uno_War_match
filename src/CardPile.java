import java.util.Random;

// Class representing a generic pile of cards
public class CardPile {
    private Card topCard;
    private int tracker = 0; // tracks index placement in deck
    private Card[] discardedCards = new Card[108]; // cards that exist underneath the top card, considered discarded

    /**
     * Default card pile constructor, will just set the card pile to an empty array
     */
    public CardPile () {
        this.discardedCards = new Card[0];
    }

    /**
     * Card pile constructor
     * @param topCard -- Card to start/continue the deck and is on top of the card "stack"
     */
    public CardPile (Card topCard) {
        this.topCard = topCard;
    }

    /**
     * Card pile constructor
     * @param topCard -- Card to start/continue the deck and is on top of the card "stack"
     * @param numCards -- The number of cards that exist in the deck / MAX number of cards that can be in the card pile
     */
    public CardPile (Card topCard, int numCards) {
        this.topCard = topCard;
        this.discardedCards = new Card[numCards];
    }

    /**
     * returns if the given card can be played following the rules of Uno
     * this is determined by equality with the top card (i.e the card the last player played)
     * @param card -- Card to check
     * @return -- True if the card equals the top card, False if the card does not equal the top card
     */
    public boolean canPlay(Card card) {
        return this.topCard.equals(card);
    }

    /**
     * Playing the given card onto the card pile
     * @param card -- the card being played
     */
    public void play(Card card) {
        // Checks if we can play the card
        if (this.canPlay(card)) {
            if (tracker >= discardedCards.length) { // Invalid index
                System.out.println("Error tracker exceeds possible discard pile length");
                return;
            }

            // updates card pile parameters to reflect the change
            discardedCards[tracker] = this.topCard; // adds card to the discarded cards
            this.tracker++;
            this.topCard = card; // the given card is now on the top of the pile or "stack"
        }
    }

    /**
     * method that returns the number of cards in the card pile
     * @return -- the number of cards in the card pile
     */
    public int getNumCards() {
        return this.tracker;
    }

    /**
     * method that returns the top card
     * @return -- a Card top card
     */
    public Card getTopCard() {
        return this.topCard;
    }

    /**
     * method that sets the top card of the card pile
     * @param card -- Card we want as top card
     */
    public void setTopCard(Card card) {
        this.topCard = card;
    }

    /**
     * method that randomly mixes the discard pile up
     */
    public void reshuffle() {
        Random rand = new Random();
        for (int i = this.tracker - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = this.discardedCards[i];
            this.discardedCards[i] = this.discardedCards[j];
            this.discardedCards[j] = temp;
        }
    }

    /**
     * method for returning the cards in the discarded cards array
     * @return -- a Card array of the discarded cards
     */
    public Card[] getDiscardedCards () {
        Card [] activeDiscarded = new Card[tracker]; // temporary array

        int count = 0;
        // copies the cards into the temporary array
        for (int i = 0; i < tracker - 1; i++) {
            if (discardedCards[i] != null) { //only add if not null
                activeDiscarded[count] = discardedCards[i];
                count++;
            }
        }

        // updates the card pile's parameters
        this.tracker = 0;
        this.discardedCards = new Card[108];
        return activeDiscarded;
    }
}
