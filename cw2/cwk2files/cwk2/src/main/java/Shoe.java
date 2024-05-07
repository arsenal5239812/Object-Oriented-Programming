// TODO: Implement the Shoe class in this file
import java.util.Collections;
import java.util.stream.IntStream;

public class Shoe extends CardCollection {

    public Shoe(int numberOfDecks) {
        super();
        if (numberOfDecks != 6 && numberOfDecks != 8) {
            throw new CardException("Number of decks must be either 6 or 8");
        }
        initializeDecks(numberOfDecks);
    }

    private void initializeDecks(int numberOfDecks) {
        IntStream.range(0, numberOfDecks).forEach(i -> {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    this.cards.add(new BaccaratCard(rank, suit));
                }
            }
        });
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public Card deal() {
        if (this.isEmpty()) {
            throw new CardException("Cannot deal from an empty shoe");
        }
        return this.cards.remove(0);
    }
}