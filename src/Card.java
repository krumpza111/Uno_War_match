public class Card {
    private int rank;
    private int suit;

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

    public int getRankNum() {
        return this.rank;
    }

    public String getRankName() {
        String name = "";
        if (this.rank == -1) {
            System.out.println("This card is invalid");
        } else if (this.suit == 5) {
            if (this.rank == 2) {
                name = "Wild Card";
                return name;
            } else if (this.rank == 1) {
                name = "+4";
                return name;
            }
        }
        String[] rankNames = new String[]{"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "+2", "Skip", "Reverse"};
        for (int i = 1; i <= rankNames.length; i++) {
            if (this.rank == i) {
                name = rankNames[i - 1];
            }
        }
        return name;
    }

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

    public void setSuit (int suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        String var = this.getRankName();
        return this.getSuitName() + " " + var;
    }

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
