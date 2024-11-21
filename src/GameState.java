import java.util.Random;

public class GameState {
    Hand currHand;
    Hand opponentHand;
    CardPile cardPile;
    Deck deck;


    public GameState(Hand currHand, Hand oppHand, CardPile cardPile, Deck deck) {
        this.currHand = currHand;
        this.cardPile = cardPile;
        this.deck = deck;
        this.opponentHand = oppHand;
    }

    public void simulateOpponent (int handSize) {
        Card [] fullDeck = new Deck().getDeck();
        Card [] discardedCards = cardPile.getDiscardedCards();
        Card topCard = cardPile.getTopCard();

        Card [] validCards = new Card[fullDeck.length];
        int validIndex = 0;

        for (int i = 0; i < fullDeck.length; i++) {
            boolean isDiscarded = false;
            for (int j = 0; j < discardedCards.length; j++) {
                if (fullDeck[i] != null && fullDeck[i] == discardedCards[j]) {
                    isDiscarded = true;
                    break;
                }
            }

            if (!isDiscarded && fullDeck[i] != null && fullDeck[i] != topCard) {
                validCards[validIndex++] = fullDeck[i];
            }
        }

        Card[] opponentHand = new Card[handSize];
        for (int i = 0; i < handSize; i++) {
            opponentHand[i] = validCards[i];
        }
        Deck opponentDeck = new Deck(opponentHand);
        this.opponentHand = new Hand(opponentDeck, handSize);
    }

    public Hand getCurrHand () {
        return this.currHand;
    }

    public Hand getOpponentHand () {
        return this.opponentHand;
    }

    public CardPile getCardPile () {
        return this.cardPile;
    }

    public Deck getDeck () {
        return this.deck;
    }

    public void playPlusCard(int numCards, boolean max) {
        Hand target = max ? this.opponentHand : this.currHand;
        for (int i = 0; i < numCards; i++) {
            if (this.deck.isEmpty()) {
                this.cardPile.reshuffle();
                Card[] temp = this.cardPile.getDiscardedCards();
                this.deck = new Deck(temp);
            }
            target.addCard(this.deck.draw());
        }
    }

    public void playPlusTwo (boolean max) {
        playPlusCard(2, max);
    }

    public void playPlusFour (boolean max) {
        playPlusCard(4, max);
    }

    public void draw (boolean max) {
        playPlusCard(1, max);
    }

}
