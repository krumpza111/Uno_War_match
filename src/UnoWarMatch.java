import java.util.Random;

// Class for running an UnoWarMatch between two AI players
public class UnoWarMatch {
    private AI ai1;
    private AI ai2;

    /**
     * Constructor for an UnoWarMatch
     * @param ai1 -- First AI player (AI Class)
     * @param ai2 -- Second AI player (AI Class)
     */
    public UnoWarMatch(AI ai1, AI ai2) {
        this.ai1 = ai1;
        this.ai2 = ai2;
    }

    /**
     * Function responsible for running a simulated game of uno between two AI players
     * @param nTrials -- Number of full games to play
     * @return -- A double winrate representing the rate that player 1 beats player 2
     */
    public double playGame(int nTrials) {
        int wins1 = 0;
        int wins2 = 0;
        while (wins1 != nTrials && wins2 != nTrials) {
            // Initializes conditions to start a Uno game
            Deck myDeck;
            myDeck = new Deck();
            Hand hand1 = new Hand(myDeck, 5);
            Hand hand2 = new Hand(myDeck, 5);
            CardPile cardPile = new CardPile(myDeck.draw());

            // System.out.println("Top Card is: " + cardPile.getTopCard());

            boolean ai1Turn = true;
            boolean playAgain = false;

            while (!hand1.isEmpty() && !hand2.isEmpty()) {
                // (FIX) A check to make sure that the hand sizes can't get larger than the total cards in the deck
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

                // Sets the current hand and player based on ai1Turn
                Hand currentHand = ai1Turn ? hand1 : hand2;
                AI currentAI = ai1Turn ? ai1 : ai2;
                //System.out.println("Current Player: " + currentAI);

                // Sets the target of attack for special cards
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

                Card currCard = currentAI.getPlay(currentHand, cardPile); // Card that the AI chooses
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
                        // Sets the suit/color of the wild card randomly
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
                        // alternate turns again so next turn will be same
                        playAgain = true;
                    } else if (currCard.getRankNum() == 12) {
                        // Reverse Card
                        // alternate turns again so next turn will be same
                        playAgain = true;
                    }

                    cardPile.play(currCard); // plays the card to the card pile

                    //System.out.println("Current play: " + currCard);
                } else {
                    // Else branch is there are no valid cards in the players hand. Player has to draw
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
                // Alternating turns logic
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
