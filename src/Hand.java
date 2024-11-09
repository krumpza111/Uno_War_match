public class Hand {
    private Deck deck;
    private int size;
    private Card [] hand;

    public Hand(Deck deck, int size) {
        this.deck = deck;
        this.size = size;
        if (size == 0) {
            this.hand = null;
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

    public void addCard() {
        for (int i = 0; i < this.size; i++) {
            if (this.hand[i] == null) {
                this.hand[i] = deck.draw();
            }
        }
    }

    public boolean remove(Card card) {
        for (int i = 0; i < this.size; i++) {
            if (this.hand[i] == null) {
                return false;
            } else if (this.hand[i].equals(card)) {
                this.hand[i] = null;
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty () {
        boolean allNull = true;
        for (int i = 0; i < this.size; i++) {
            if (this.hand[i] != null) {
                allNull = false;
            }
        }
        return allNull;
    }
}
