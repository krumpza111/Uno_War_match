import java.util.Random;

// A class representing a generic deck
public class Deck {
    private Card[] deck = new Card[108]; // An array of cards in the deck
    private int totalCards = 108; // Number of cards in the deck #DEFINE

    /**
     * Deck constructor
     * Creates an Uno deck
     */
    public Deck() {
        int index = 0;
        // Initialize normal cards
        for (int suit = 1; suit <= 4; suit++) {
            this.deck[index] = new Card(0, suit); // initializing zeros
            for (int rank = 1; rank < 13; rank++) {
                this.deck[index] = new Card(rank, suit);
                this.deck[index + 1] = new Card(rank, suit);
                index += 2;
            }
        }
        // initialize Wild cards (4 each)
        // wild +4
        for (int i = 0; i < 4; i++) {
            this.deck[index] = new Card(0, 5); // Wild cards
            index++;
        }
        // wild
        for (int i = 0; i < 4; i++) {
            this.deck[index] = new Card(1, 5); // +4 cards
            index++;
        }
        this.shuffle(); // shuffle cards
    }

    /**
     * Deck constructor that takes in a set of cards and builds a deck from it
     * @param cards -- Array of cards we want to construct the deck using
     */
    public Deck(Card[] cards) {
        // NULL checking
        int nullCount = 0; // Number of nulls in the array
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
                nullCount++;
            }
        }

        // sets the appropriate length of the deck excluding NULL values
        int arrSize = cards.length - nullCount;
        this.deck = new Card[arrSize];

        // loads non-null cards from the cards array into our deck
        for (int i = 0; i < arrSize; i++) {
            if (cards[i] != null) {
                this.deck[i] = cards[i];
            }
        }
        this.totalCards = arrSize; //updates the total cards
        this.shuffle(); // shuffle cards
    }

    /**
     * method that mixes the cards in the deck randomly to have distinct games
     */
    public void shuffle() {
        Random rand = new Random();
        for (int i = this.totalCards - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = this.deck[i];
            this.deck[i] = this.deck[j];
            this.deck[j] = temp;
        }
    }

    /**
     * method that draws a card from the deck
     * @return -- the card at the last index in the deck or NULL if the deck is empty
     */
    public Card draw() {
        if (this.isEmpty()) {
            return null;
        } else {
            this.totalCards--; // decrease the number of cards in the deck
            Card picked = this.deck[this.totalCards];
            while (picked == null) {
                totalCards--;
                picked = this.deck[this.totalCards];
                //System.out.println("Drawn card is " + picked);
            }
            return picked;
        }
    }

    /**
     * method that returns the number of cards in the deck
     * @return -- the current deck's cards remaining
     */
    public int cardsRemaining() {
        return this.totalCards;
    }

    /**
     * returns if the deck out of cards
     * @return -- True if the deck has no cards remaining or False if there are still cards remaining
     */
    public boolean isEmpty() {
        if (this.cardsRemaining() <= 0) { // zero or less cards remaining
            return true;
        }
        for (int i = 0; i < this.totalCards; i++) {
            if (this.deck[i] != null) { // if any cards in the deck are not null then this is false
                return false;
            }
        }
        return true;
    }

    /**
     * @return -- a string representing the deck in the form {SUIT RANK} for each card
     */
    @Override
    public String toString() {
        String builder = "";
        for (int i = 0; i < totalCards; i++) {
            builder = builder + this.deck[i] + " ";
        }
        return builder;
    }
}
