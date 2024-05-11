// TODO: Implement the Shoe class in this file
import java.util.Collections;
import java.util.stream.IntStream;

// Declare the Shoe class
// which extends the CardCollection class.
public class Shoe extends CardCollection {

    // Constructor that takes the
    // number of decks as a parameter.
    public Shoe(int numberOfDecks) {
        // Call the constructor of the superclass.
        super();
        if (numberOfDecks != 6 && numberOfDecks != 8) {
            throw new CardException("Number of decks must be either 6 or 8");
        }
        // Initialize the decks.
        initializeDecks(numberOfDecks);
    }

    // Private method to initialize the decks.
    private void initializeDecks(int numberOfDecks) {
        // Create the specified number of decks.
        IntStream.range(0, numberOfDecks).forEach(i -> {
            // Iterate over all suits.
            for (Card.Suit suit : Card.Suit.values()) {
                // Iterate over all ranks.
                for (Card.Rank rank : Card.Rank.values()) {
                    // Create and add a new Baccarat card.
                    this.cards.add(new BaccaratCard(rank, suit));
                }
            }
        });
    }

    // Public method for shuffling the cards.
    public void shuffle() {
        // Shuffle the cards.
        Collections.shuffle(this.cards);
    }

    // Public method for dealing cards.
    public Card deal() {
        // If the shoe is empty, throw an exception.
        if (this.isEmpty()) {
            throw new CardException("Cannot deal from an empty shoe");
        }
        // Remove and return the first card from the deck.
        return this.cards.remove(0);
    }
}
