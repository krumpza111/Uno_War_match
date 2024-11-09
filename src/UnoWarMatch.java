public class UnoWarMatch {
    private AI ai1;
    private AI ai2;

    public UnoWarMatch(AI ai1, AI ai2) {
        this.ai1 = ai1;
        this.ai2 = ai2;
    }

    public double playGame(int nTrials) {
        int wins1 = 0;
        int wins2 = 0;
        while (wins1 != nTrials && wins2 != nTrials) {
            Deck myDeck = new Deck();
            Hand hand1 = new Hand(myDeck, 5);
            Hand hand2 = new Hand(myDeck, 5);
            CardPile cardPile = new CardPile(myDeck.draw());
            System.out.println("Top Card is: " + cardPile.getTopCard());
            boolean ai1Turn = true;

            while (!hand1.isEmpty() && !hand2.isEmpty()) {
                Hand currentHand = ai1Turn ? hand1 : hand2;
                AI currentAI = ai1Turn ? ai1 : ai2;

                Card currCard = currentAI.getPlay(currentHand, cardPile);
                if (cardPile.canPlay(currCard)) {
                    cardPile.play(currCard);
                    currentHand.remove(currCard);
                } else { // Else branch is there are no valid cards in the players hand
                    // If the deck is empty then reshuffle the card pile and form a new deck from that
                    if (myDeck.isEmpty()) {
                        cardPile.reshuffle();
                        myDeck = new Deck(cardPile);
                    }
                    currentHand.addCard();
                }

                // Check if the current player has won the game
                if (currentHand.isEmpty()) {
                    if (ai1Turn) {
                        wins1++;
                    } else {
                        wins2++;
                    }
                    break; // Ends current game
                }
                // Alternate turns
                ai1Turn = !ai1Turn;
            }
        }
        return (double) wins1 / (double) wins2;
    }
}
