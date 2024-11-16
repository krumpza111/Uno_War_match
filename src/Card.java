// Class representing a generic card
public class Card {
    private int rank;
    private int suit;

    /**
     * Card constructor
     * @param rank -- An integer representing the rank of the card in relation to the other cards
     * @param suit -- An integer representing the suit or color of the card
     */
    public Card(int rank, int suit) {
        if (rank >= 1 && rank <= 12 && suit > 0 && suit <= 5) {
            this.rank = rank;
            this.suit = suit;
        } else {
            System.out.println("Error incorrect card config");
            System.out.println("Card rank: " + rank + " Card suit: " + suit);
            this.suit = -1;
            this.rank = -1;
        }
    }

    /**
     * method to get the card's rank
     * @return -- current card rank
     */
    public int getRankNum() {
        return this.rank;
    }

    /**
     * method which translates the card's rank number to a string
     * @return -- String representing the current card rank
     */
    public String getRankName() {
        String name = "";
        if (this.rank == -1) {
            System.out.println("This card is invalid");
        } else if (this.suit == 5) {
            // Wild card ranks
            if (this.rank == 2) {
                name = "Wild Card";
                return name;
            } else if (this.rank == 1) {
                name = "+4";
                return name;
            }
        }
        // Normal Ranked cards
        String[] rankNames = new String[]{"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "+2", "Skip", "Reverse"};
        for (int i = 1; i <= rankNames.length; i++) {
            if (this.rank == i) {
                name = rankNames[i - 1];
            }
        }
        return name;
    }

    /**
     * method for getting current card's suit
     * @return -- current card suit
     */
    public int getSuit() {
        return this.suit;
    }

    /**
     * method for translating the suit number to a string
     * @return -- String representing the current card's suit number
     */
    public String getSuitName() {
        String cardName = "";
        if (this.suit == 1) {
            cardName = "Red";
        } else if (this.suit == 2) {
            cardName = "Green";
        } else if (this.suit == 3) {
            cardName = "Yellow";
        } else if (this.suit == 4) {
            cardName = "Blue";
        } else if (this.suit == 5) {
            cardName = "Wild";
        } else {
            System.out.println("This card is invalid");
        }
        return cardName;
    }

    /**
     * method for setting a cards suit
     * used in cases of wild cards when a new suit needs to be set for the topcard
     * @param suit -- An integer representing the suit or color of the card
     */
    public void setSuit (int suit) {
        this.suit = suit;
    }

    /**
     * @return -- A string representing a card in the form SUIT RANK
     * ex 1) YELLOW 2
     * ex 2) RED REVERSE
     * ex 3) WILD +4
     */
    @Override
    public String toString() {
        String var = this.getRankName();
        return this.getSuitName() + " " + var;
    }

    /**
     * method for equality between two cards following the rules of Uno:
     * Two cards are equal if... 1) They are the same card
     * 2) The two cards have the same number
     * 3) The two cards have teh same suit
     * 4) The other card is a WILD card
     * @param other -- Another card object
     * @return -- True if the two cards equal, False if not
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other == null) {
            return false;
        } else if (!(other instanceof Card)) {
            return false;
        } else {
            Card c = (Card) other;
            return this.rank == c.rank || this.suit == c.suit || c.suit == 5;
        }
    }
}
