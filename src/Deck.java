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
            index++;
        }
        for (int i = 0; i< 2; i++) {
            this.deck[index] = new Card(1, 5); // +4 cards
            index++;
        }
        this.shuffle();
    }

    public Deck(Card[] cards) {
        int nullCount = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
                nullCount++;
            }
        }
        if (nullCount == 0) {
            //System.out.println("no null values seeen");
        }
        this.deck = new Card[cards.length - nullCount];
        for (int i = 0; i < (cards.length - nullCount); i++) {
            if (cards[i] != null) {
                this.deck[i] = cards[i];
            }
        }
        this.totalCards = this.deck.length;
        this.shuffle();
    }

    public void shuffle() {
        Random rand = new Random();
        for (int i = this.totalCards - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = this.deck[i];
            this.deck[i] = this.deck[j];
            this.deck[j] = temp;
        }
    }

    public Card draw() {
        if (this.isEmpty()) {
            return null;
        } else {
            this.totalCards--;
            return this.deck[this.totalCards];
        }
    }

    public int cardsRemaining() {
        return this.totalCards;
    }

    public boolean isEmpty() {
        return this.cardsRemaining() <= 0;
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
