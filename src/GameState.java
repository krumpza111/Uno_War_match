public class GameState {
    Hand currHand;
    Hand opponentHand;
    CardPile cardPile;

    public GameState(Hand currHand, CardPile cardPile) {
        this.currHand = currHand;
        this.cardPile = cardPile;
    }

    public void simulateOpponent (int handSize) {
        this.opponentHand = new Hand(new Deck(), handSize);
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

}
