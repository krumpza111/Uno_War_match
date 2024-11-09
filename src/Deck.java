import java.util.Random;

public class Deck {
    private Card[] deck = new Card[55];
    private int totalCards = 54;

    public Deck() {
        int index = 0;
        // Initialize normal cards
        for (int suit = 1; suit <= 4; suit++) {
            for (int rank = 1; rank <= 12; rank++) {
                this.deck[index] = new Card(rank, suit);
                index++;
            }
        }
        // initialize Wild cards
        for (int i = 0; i < 4; i++) {
            this.deck[index] = new Card(2, 5); // Wild cards
        }
        for (int i = 0; i< 2; i++) {
            this.deck[index] = new Card(1, 5); // +4 cards
        }
        this.shuffle();
    }

    public Deck(CardPile cardPile) {
        for (int i = 0; i < cardPile.getNumCards(); i++) {
            this.deck[i] = cardPile.getDiscardedCards()[i];
        }
        this.totalCards = cardPile.getNumCards();
    }

    public void shuffle() {
        System.out.println(this.deck.length);
        for (int i = this.deck.length - 1; i >= 1; i--) {
            Random rand = new Random();
            int j = rand.nextInt(i);
            Card temp = this.deck[i];
            this.deck[i] = this.deck[j];
            this.deck[j] = temp;
        }
    }

    public Card draw() {
        int temp = this.totalCards;
        if (this.isEmpty()) {
            this.shuffle();
            this.totalCards--;
            return this.deck[temp];
        } else {
            this.totalCards--;
            return this.deck[temp];
        }
    }

    public int cardsRemaining() {
        return this.totalCards;
    }

    public boolean isEmpty() {
        return this.cardsRemaining() == 0;
    }

    @Override
    public String toString() {
        String builder = "";
        for (int i = 0; i < totalCards; i++) {
            builder = builder + this.deck[i];
        }
        return builder;
    }
}
