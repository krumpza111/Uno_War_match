// Class representing a generic hand of cards
public class Hand {
    private Deck deck;
    private int size;
    private Card [] hand; // Array of current cards in this hand

    /**
     * Hand constructor
     * @param deck -- A Deck object from which we can draw cards into our hand from
     * @param size -- An integer representing the number of cards in this hand
     * If size is set to zero the hand will have am empty array
     * Otherwise the hand will draw (int) size cards into the hand from the deck
     */
    public Hand(Deck deck, int size) {
        this.deck = deck;
        this.size = size;
        if (size == 0) {
            this.hand = new Card[0];
        } else {
            this.hand = new Card[size];
            for (int i = 0; i < size; i++) {
                this.hand[i] = deck.draw();
            }
        }
    }

    /**
     * method for getting the number of cards in the hand
     * @return -- Number of cards in this hand
     */
    public int getSize() {
        return this.size;
    }

    /**
     * method for showing the card in the current hand at a given index
     * @param i -- index of the card in the hand array we want
     * @return -- the card at index i in the hand or NULL if the card doesn't exist
     */
    public Card get(int i) {
        if (i >= 0 && i <= this.size) {
            return this.hand[i];
        } else {
            System.out.println("Invalid hand index");
            return null;
        }
    }

    /**
     * method for adding a card to the current hand
     * @param card - Card we want to add
     */
    public void addCard(Card card) {
        Card[] newHand = new Card[this.size + 1]; //temporary array to place elements into
        // copy all cards to the array
        for (int i = 0; i < this.size; i++) {
            newHand[i] = this.hand[i];
        }
        newHand[this.size] = card; // set the new card
        // Update hands parameters
        this.hand = newHand;
        this.size++;
    }

    /**
     * method for removing a card from the hand
     * this happens when any card is played
     * @param card -- Card we want to remove
     * @return -- True is the card was successfully removed, False is the card was not removed or not found
     */
    public boolean remove(Card card) {
        // searches through all cards in the hand
        for (int i = 0; i < this.size; i++) {
            // find a card that matches
            if (this.hand[i] != null && this.hand[i].equals(card)) {
                // Shift elements to the left
                for (int j = i; j < this.size - 1; j++) {
                    this.hand[j] = this.hand[ j + 1];
                }
                this.hand[this.size - 1] = null;
                this.size--; // updates the size

                // Creates a temporary array and copies all cards except for the last from the hand
                Card[] newHand = new Card[this.size];
                for (int k = 0; k < this.size; k++) {
                    newHand[k] = this.hand[k];
                }
                this.hand = newHand; // updates hand
                return true;
            }
        }
        // no card was found
        return false;
    }

    /**
     * method for removing a card from the hand
     * this happens when any card is played
     * @param index -- index of the card in the hand we want to remove
     * @return -- True is the card was successfully removed, False is the card was not removed or not found
     */
    public boolean remove(int index) {
        // Index checking
        if (index < 0 || index >= this.size) {
            System.out.println("Error: Index out of bounds.");
            return false;
        }
        // Shift elements to the left starting from the specified index
        for (int j = index; j < this.size - 1; j++) {
            this.hand[j] = this.hand[j + 1];
        }
        // Clear the last element and reduce size
        this.hand[this.size - 1] = null;
        this.size--;
        return true;
    }

    /**
     * tells if the current hand is empty
     * @return -- True if there are no cards in the hand, False if there are still cards present
     */
    public boolean isEmpty () {
        return this.size == 0;
    }

    /**
     * @return -- A string representing the hand in the form {SUIT RANK} for each card in the deck
     * may also return nothing if the hand is empty
     */
    @Override
    public String toString () {
        String builder = "";
        for (int i = 0; i < this.size; i++) {
            builder = builder + this.get(i) + " ";
        }
        return builder;
    }
}
