// a class used to numerically represent individual cards
package omair.Banana.Objects;

public class Card {
    private int rank;
    private int suit;

    public Card()
    {
        this.rank = 0;
        this.suit = 0;
    }
    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return this.rank;
    }

    public int getSuit()
    {
        return this.suit;
    }


    public String toString()
    {
        String[] ranks = {"A", "2", "3", "4", "5", "6",
                "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"♣", "♦", "♥", "♠"};
        String s = ranks[this.rank] + suits[this.suit];
        return s;
    }
}
