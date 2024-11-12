import java.util.Random;

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
            Deck myDeck;
            myDeck = new Deck();
            Hand hand1 = new Hand(myDeck, 5);
            Hand hand2 = new Hand(myDeck, 5);
            CardPile cardPile = new CardPile(myDeck.draw());

            //System.out.println("Top Card is: " + cardPile.getTopCard());
            boolean ai1Turn = true;
            boolean playAgain = false;

            while (!hand1.isEmpty() && !hand2.isEmpty()) {
                int totalSize = hand1.getSize() + hand2.getSize();
                if (totalSize > 54) {
                    System.out.println("");
                    if (hand1.getSize() > hand2.getSize()) {
                        wins2++;
                    } else {
                        wins1++;
                    }
                    break;
                }
                Hand currentHand = ai1Turn ? hand1 : hand2;
                AI currentAI = ai1Turn ? ai1 : ai2;
                // Sets the target of attack for special cards
                //System.out.println("Current Player: " + currentAI);
                Hand target;
                if (ai1Turn) {
                    target = hand2;
                } else {
                    target = hand1;
                }

                if (myDeck.isEmpty()) { // added another check for empty deck
                    cardPile.reshuffle();
                    Card [] temp = cardPile.getDiscardedCards();
                    myDeck = new Deck(temp);
                }
                //System.out.println("Current Top Card: " + cardPile.getTopCard());

                Card currCard = currentAI.getPlay(currentHand, cardPile);
                if (cardPile.canPlay(currCard)) {
                    // Logic for special cards
                    if (currCard.getSuitName() == "Wild") {
                        if (currCard.getRankNum() == 1) {
                            // The card is +4
                            for (int i = 0; i < 4; i++) {
                                if (myDeck.isEmpty()) {
                                    cardPile.reshuffle();
                                    Card [] temp = cardPile.getDiscardedCards();
                                    myDeck = new Deck(temp);
                                }
                                target.addCard(myDeck.draw());
                            }
                        }
                        Random rand = new Random();
                        int randSuit = rand.nextInt(1, 5);
                        currCard.setSuit(randSuit);
                    } else if (currCard.getRankNum() == 10) {
                        // +2 Card
                        for (int i = 0; i < 2; i++) {
                            if (myDeck.isEmpty()) {
                                cardPile.reshuffle();
                                Card [] temp = cardPile.getDiscardedCards();
                                myDeck = new Deck(temp);
                            }
                            target.addCard(myDeck.draw());
                        }
                    } else if (currCard.getRankNum() == 11) {
                        // Skip card
                        playAgain = true;
                    } else if (currCard.getRankNum() == 12) {
                        // Reverse Card
                        // alternate turns again so next turn will be same
                        playAgain = true;
                    }

                    cardPile.play(currCard);
                    //System.out.println("Current play: " + currCard);
                } else { // Else branch is there are no valid cards in the players hand
                    // If the deck is empty then reshuffle the card pile and form a new deck from that
                    if (myDeck.isEmpty()) {
                        cardPile.reshuffle();
                        Card [] temp = cardPile.getDiscardedCards();
                        myDeck = new Deck(temp);
                    }
                    currentHand.addCard(myDeck.draw());
                    //System.out.println("Current play: Drew a card, Current hand " + currentHand);
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
                if (!playAgain) {
                    ai1Turn = !ai1Turn;
                } else {
                    playAgain = false;
                }
            }
        }
        return (double) wins1 / (double) wins2;
    }
}
