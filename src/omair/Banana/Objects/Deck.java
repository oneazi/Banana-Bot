// this class implements a deck of cards using Card class objects
package omair.Banana.Objects;

public class Deck {
    private Card[] cards;

    public Deck()
    {
        this.cards = new Card[52];
        int index = 0;
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 0; rank <= 12; rank++) {
                this.cards[index] = new Card(rank, suit);
                index++;
            }
        }
    }
}
