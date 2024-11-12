public class Hand {
    private Deck deck;
    private int size;
    private Card [] hand;

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

    public int getSize() {
        return this.size;
    }

    public Card get(int i) {
        if (i >= 0 && i <= this.size) {
            return this.hand[i];
        } else {
            System.out.println("Invalid hand index");
            return null;
        }
    }

    public void addCard(Card card) {
        Card[] newHand = new Card[this.size + 1];
        for (int i = 0; i < this.size; i++) {
            newHand[i] = this.hand[i];
        }
        newHand[this.size] = card;
        this.hand = newHand;
        this.size++;
    }

    public boolean remove(Card card) {
        for (int i = 0; i < this.size; i++) {
            if (this.hand[i] != null && this.hand[i].equals(card)) {
                // Shift elements to the left
                for (int j = i; j < this.size - 1; j++) {
                    this.hand[j] = this.hand[ j + 1];
                }
                this.hand[this.size - 1] = null;
                this.size--;
                Card[] newHand = new Card[this.size];
                for (int k = 0; k < this.size; k++) {
                    newHand[k] = this.hand[k];
                }
                this.hand = newHand;
                return true;
            }
        }
        return false;
    }

    public boolean remove(int index) {
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

    public boolean isEmpty () {
        return this.size == 0;
    }

    public String toString () {
        String builder = "";
        for (int i = 0; i < this.size; i++) {
            builder = builder + this.get(i) + " ";
        }
        return builder;
    }
}
